package lk.ijse.channelingCenter.controller;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.channelingCenter.dto.PatientDto;
import lk.ijse.channelingCenter.dto.tm.PatientTm;
import lk.ijse.channelingCenter.model.PatientModel;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PatientFromController {
    public TextField txtAge;
    public Label lblPatientId;
    public ComboBox<String> cmbBlood;
    public TableColumn colAddress;
    public TextField txtID;
    public TextField txtPatientId;
    public ComboBox<String> cmbGender;
    @FXML
    private TextField txtAddress;
    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtNumber;
    private TextField txtId;
    @FXML
    private TextField txtType;
    public AnchorPane patientPane;

    @FXML
    private TableView<PatientTm> tblPatient;

    @FXML
    private TableColumn<?, ?> colAge;

    @FXML
    private TableColumn<?, ?> colDelete;

    @FXML
    private TableColumn<?, ?> colUpdate;

    @FXML
    private TableColumn<?, ?> colBloodGroup;

    @FXML
    private TableColumn<?, ?> colEmail;

    @FXML
    private TableColumn<?, ?> colNumber;

    @FXML
    private TableColumn<?, ?> colPatientID;

    @FXML
    private TableColumn<?, ?> colPatientName;
    private TableCell<?, ?> colPatientId;

    @FXML
    private TableColumn<?, ?> colSex;

    PatientModel patientModel = new PatientModel();

    private void setCellValueFactory() {
        colPatientID.setCellValueFactory(new PropertyValueFactory<>("patient_id"));
        colPatientName.setCellValueFactory(new PropertyValueFactory<>("patient_name"));
        colNumber.setCellValueFactory(new PropertyValueFactory<>("mobile_number"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colSex.setCellValueFactory(new PropertyValueFactory<>("sex"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colAge.setCellValueFactory(new PropertyValueFactory<>("age"));
        colBloodGroup.setCellValueFactory(new PropertyValueFactory<>("blood"));
        colDelete.setCellValueFactory(new PropertyValueFactory<>("deleteButton"));
        colUpdate.setCellValueFactory(new PropertyValueFactory<>("updateButton"));

    }

    public void initialize() {
        setCellValueFactory();
        setFontAwesomeIcons();
        loadAllPatients();
        loadPatientsBloodGroup();
        loadGenderGroup();
        setPatientID();
    }

    private void setFontAwesomeIcons() {
        tblPatient.getItems().forEach(item -> {
            Button deleteButton = item.getDeleteButton();
            Button updateButton = item.getUpdateButton();

            FontAwesomeIconView deleteIcon = new FontAwesomeIconView(FontAwesomeIcon.TRASH);
            FontAwesomeIconView updateIcon = new FontAwesomeIconView(FontAwesomeIcon.PENCIL);

            deleteButton.setGraphic(deleteIcon);
            updateButton.setGraphic(updateIcon);
        });
    }

    public void loadAllPatients() {
        ObservableList<PatientTm> obList = FXCollections.observableArrayList();

        try {
            List<PatientDto> dtoList = new PatientModel().getAllPatient();

            for (PatientDto dto : dtoList) {
                Button deleteButton = new Button();
                Button updateButton = new Button();

                deleteButton.setCursor(Cursor.HAND);
                updateButton.setCursor(Cursor.HAND);

                deleteButton.setOnAction((e) -> {
                    ButtonType yes = new ButtonType("Yes", ButtonBar.ButtonData.OK_DONE);
                    ButtonType no = new ButtonType("No", ButtonBar.ButtonData.CANCEL_CLOSE);

                    Optional<ButtonType> result = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure to delete this Patient?", yes, no).showAndWait();
                    if (result.orElse(no) == yes) {
                        int selectedIndex = tblPatient.getSelectionModel().getSelectedIndex();
                        String code = (String) colPatientID.getCellData(selectedIndex);
                        deletePatient(code);
                        obList.remove(selectedIndex);
                        tblPatient.refresh();
                    }
                });
                updateButton.setOnAction((e) -> {
                    int selectedIndex = tblPatient.getSelectionModel().getSelectedIndex();
                    String code = (String) colPatientID.getCellData(selectedIndex);
                    System.out.println(code);
                    try {
                        // patientPane.getChildren().clear();
                        patientPane.getChildren().add(FXMLLoader.load(getClass().getResource("/view/addAppoinmentPatinetDetials.fxml")));
                    } catch (Exception e1) {
                    }
                });
                obList.add(
                        new PatientTm(
                                dto.getPatient_id(),
                                dto.getPatient_name(),
                                dto.getMobile_number(),
                                dto.getAddress(),
                                dto.getSex(),
                                dto.getEmail(),
                                dto.getAge(),
                                dto.getBlood(),

                                deleteButton,
                                updateButton
                        )
                );
            }

            tblPatient.setItems(obList);
            setFontAwesomeIcons();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void deletePatient(String code) {
        try {
            boolean b = patientModel.deletePatient(code);
            if (b) {
                new Alert(Alert.AlertType.CONFIRMATION, "Deleted").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    public void btnClerOnAction(ActionEvent actionEvent) {
        clearFields();
    }

    private void clearFields() {
        lblPatientId.setText("");
        txtName.setText("");
        txtNumber.setText("");
        txtAddress.setText("");
        cmbGender.setItems(null);
        txtEmail.setText("");
        txtAge.setText("");
        cmbBlood.setItems(null);

    }


    private boolean validatePatinet() {
        String patinetIdText = lblPatientId.getText();
        Pattern compile = Pattern.compile("[P][0-9]{3,}");
        Matcher matcher = compile.matcher(patinetIdText);
        boolean matches = matcher.matches();

        if (!matches) {
            lblPatientId.setStyle("-fx-border-color: red");
            //new Alert(Alert.AlertType.ERROR, "Invalid Patient ID").show();
            return false;
        }
        String nameText = txtName.getText();
        boolean isNameValid = Pattern.compile("[A-Za-z]{3,}").matcher(nameText).matches();
        if (!isNameValid) {
            txtName.setStyle("-fx-border-color: red");
            //new Alert(Alert.AlertType.ERROR, "Invalid Patient Name").show();
            return false;
        }
        String numberText = txtNumber.getText();
        boolean isNumberValid = Pattern.compile("[(07)]\\d{9}|[+]\\d{11}").matcher(numberText).matches();
        if (!isNumberValid) {
            txtNumber.setStyle("-fx-border-color: red");
            //new Alert(Alert.AlertType.ERROR, "Invalid Patient Number").show();
            return false;
        }
        String ageText = txtAge.getText();
        boolean isAgeValid = Pattern.compile("\\d{2}").matcher(ageText).matches();
        if (!isAgeValid) {
            txtAge.setStyle("-fx-border-color: red");
            //new Alert(Alert.AlertType.ERROR, "Invalid Patient Age").show();
            return false;
        }
        //^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$
        String emailText = txtEmail.getText();
        boolean isEmailValid = Pattern.compile("[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Z|a-z]{2,}").matcher(emailText).matches();
        if (!isEmailValid) {
            txtEmail.setStyle("-fx-border-color: red");
            //new Alert(Alert.AlertType.ERROR, "Invalid Patient Email").show();
            return false;
        }
        return true;
    }

    public void btnSaveOnAction(ActionEvent actionEvent) {
        boolean isPatientValid = validatePatinet();

        if (isPatientValid) {
            try {
                String id = lblPatientId.getText();
                String name = txtName.getText();
                String number = txtNumber.getText();
                String address = txtAddress.getText();
                String sex = cmbGender.getValue();
                String email = txtEmail.getText();
                String age = txtAge.getText();
                String blood = cmbBlood.getValue(); // Get the selected blood group

                PatientDto itemDto = new PatientDto(id, name, number, address, sex, email, age, blood);

                PatientModel patientModel = new PatientModel();
                boolean isSaved = patientModel.savePatient(itemDto);

                if (isSaved) {
                    new Alert(Alert.AlertType.CONFIRMATION, "Patient saved!").show();
                    clearFields();
                    loadAllPatients();
                } else {
                    new Alert(Alert.AlertType.ERROR, "Patient not saved!").show();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } else {
            // new Alert(Alert.AlertType.ERROR, "Invalid Patient Details").show();
            lblPatientId.requestFocus();
        }
    }

    public void numberSearchOnAction(ActionEvent actionEvent) {
        String number = txtNumber.getText();

        try {
            PatientDto dto = patientModel.searchNumber(number);
            if (dto != null) {
                setFields(dto);

            } else {
                new Alert(Alert.AlertType.INFORMATION, "Patient not found!").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }

    }

    private void setFields(PatientDto dto) {
        lblPatientId.setText(dto.getPatient_id());
        txtName.setText(dto.getPatient_name());
        txtNumber.setText(dto.getMobile_number());
        txtAddress.setText(dto.getAddress());
        //cmbGender.setItems(dto.getSex());
        txtEmail.setText(dto.getEmail());
        //cmbBlood.setItems(dto.getBlood());
        txtAge.setText(dto.getAge());
    }

    private void loadPatientsBloodGroup() {
        ObservableList<String> obList = FXCollections.observableArrayList();

        // Add "Blood Group" options
        obList.addAll("A_POSITIVE (A+)", "A_NEGATIVE (A-)", "B_POSITIVE (B+)", "B_NEGATIVE (B-)", "AB_POSITIVE (AB+)", "AB_NEGATIVE (AB-)", "O_POSITIVE (O+)", "O_NEGATIVE (O-)");
        cmbBlood.setItems(obList);
    }

    private void loadGenderGroup() {
        ObservableList<String> obList = FXCollections.observableArrayList();
        // Add "Male" and "Female" options
        obList.addAll("Male", "Female");
        cmbGender.setItems(obList);
    }

    //    public void btnUpdateOnAction(ActionEvent actionEvent) {
//        PatientTm selectedPatient = tblPatient.getSelectionModel().getSelectedItem();
//
//        if (selectedPatient != null) {
//            String patientId = selectedPatient.getPatient_id();
//            String patientName = selectedPatient.getPatient_name();
//            String mobileNumber = selectedPatient.getMobile_number();
//            String address = selectedPatient.getAddress();
//            String sex = selectedPatient.getSex();
//            String email = selectedPatient.getEmail();
//            String age = selectedPatient.getAge();
//            String blood = selectedPatient.getBlood();
//
//            PatientDto updatedPatient = new PatientDto(patientId, patientName, mobileNumber, address, sex, email, age, blood);
//
//            try {
//                boolean isUpdated = patientModel.updatePatient(updatedPatient);
//
//                if (isUpdated) {
//                    new Alert(Alert.AlertType.CONFIRMATION, "Patient updated").show();
//                    clearFields();
//                    loadAllPatients();  // Refresh the TableView
//                }
//            } catch (SQLException e) {
//                new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
//            }
//        }
    //}
    public void btnUpdateOnAction(ActionEvent actionEvent) {
        //boolean isPatientValid = validatePatinet();
        if (validatePatinet()) {
            String Patient_id = lblPatientId.getText();
            String Patient_name = txtName.getText();
            String Mobile_number = txtNumber.getText();
            String Address = txtAddress.getText();
            String Gender = cmbGender.getItems().toString();
            String Email = txtEmail.getText();
            String Blood = cmbBlood.getItems().toString();
            String Age = txtAge.getText();

            try {
                boolean isUpdated = patientModel.updatePatient(new PatientDto(Patient_id, Patient_name, Mobile_number, Address, Gender, Email, Blood, Age));
                if (isUpdated) {
                    new Alert(Alert.AlertType.CONFIRMATION, "Patient updated").show();
                    clearFields();
                    loadAllPatients();
                }
            } catch (SQLException e) {
                new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
            }

        }
    }

//    public void btnDeleteOnAction(ActionEvent actionEvent) {
//        String id = txtId.getText();
//
//        try {
//            boolean isDeleted = patientModel.deletePatient(id);
//
//            if (isDeleted) {
//                new Alert(Alert.AlertType.CONFIRMATION, "Patient deleted!").show();
//                clearFields();
//            }
//        } catch (SQLException e) {
//            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
//        }
//    }

    private void setPatientID() {
        try {
            lblPatientId.setText(new PatientModel().autoGenaratePatientId());
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void cmbBloodGroupOnAction(ActionEvent actionEvent) {

    }

}
