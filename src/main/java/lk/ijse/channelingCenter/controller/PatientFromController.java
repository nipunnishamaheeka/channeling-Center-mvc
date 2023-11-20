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

public class PatientFromController{
    public TextField txtAge;
    public Label lblPatientId;
    public ComboBox cmbBlood;
    @FXML
    private TextField txtAddress;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtId;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtNumber;

    @FXML
    private TextField txtType;
    public AnchorPane patientPane;

    @FXML
    private TableView<PatientTm> tblPatient;

    @FXML
    private TableColumn<?,?> colAge;

    @FXML
    private TableColumn<?, ?> colDelete;

    @FXML
    private TableColumn<?, ?> colUpdate;

    @FXML
    private TableColumn<?,?> colBloodGroup;

    @FXML
    private TableColumn<?,?> colEmail;

    @FXML
    private TableColumn<?,?> colNumber;

    @FXML
    private TableColumn<?,?> colPatientID;

    @FXML
    private TableColumn<?,?> colPatientName;

    @FXML
    private TableColumn<?,?> colSex;

    PatientModel patientModel = new PatientModel();
private void setCellValueFactory(){
    colPatientID.setCellValueFactory(new PropertyValueFactory<>("patient_id"));
    colPatientName.setCellValueFactory(new PropertyValueFactory<>("patient_name"));
    colNumber.setCellValueFactory(new PropertyValueFactory<>("mobile_number"));
    colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
    colBloodGroup.setCellValueFactory(new PropertyValueFactory<>("blood"));
    colSex.setCellValueFactory(new PropertyValueFactory<>("sex"));
    colAge.setCellValueFactory(new PropertyValueFactory<>("age"));
    colDelete.setCellValueFactory(new PropertyValueFactory<>("deleteButton"));
    colUpdate.setCellValueFactory(new PropertyValueFactory<>("updateButton"));

}

    public void initialize() {
    setCellValueFactory();
        loadAllPatients();
        loadPatientsBloodGroup();
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


    public  void loadAllPatients() {
        ObservableList<PatientTm> obList = FXCollections.observableArrayList();

        try {
            List<PatientDto> dtoList = new PatientModel().getAllPatient();

            for(PatientDto dto : dtoList) {
                Button deleteButton = new Button();
                Button updateButton = new Button();

                deleteButton.setCursor(Cursor.HAND);
                updateButton.setCursor(Cursor.HAND);

                deleteButton.setOnAction((e)->{
                    ButtonType yes = new ButtonType("Yes", ButtonBar.ButtonData.OK_DONE);
                    ButtonType no = new ButtonType("No", ButtonBar.ButtonData.CANCEL_CLOSE);

                    Optional<ButtonType> result = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure to delete this Patient?", yes, no).showAndWait();
                    if (result.orElse(no) == yes){
                        int selectedIndex = tblPatient.getSelectionModel().getSelectedIndex();
                        String code = (String) colPatientID.getCellData(selectedIndex);
                        deletePatient(code);
                        obList.remove(selectedIndex);
                        tblPatient.refresh();
                    }
                });
                updateButton.setOnAction((e)->{
                    int selectedIndex = tblPatient.getSelectionModel().getSelectedIndex();
                    String code = (String) colPatientID.getCellData(selectedIndex);
                    System.out.println(code);
                    try{
                        patientPane.getChildren().clear();
                        //patientPane.getChildren().add(FXMLLoader.load(getClass().getResource("/view/updatePatientFrom.fxml")));
                    }catch (Exception e1){
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
            if (b){
                new Alert(Alert.AlertType.CONFIRMATION,"Deleted").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }

    }
    public void btnClerOnAction(ActionEvent actionEvent) {
        clearFields();
    }

    private void clearFields() {
        //lblPatientId.setText("");
        txtName.setText("");
        txtNumber.setText("");
        txtAddress.setText("");
        txtType.setText("");
        txtEmail.setText("");
        //cmbBlood.setItems(null);
        txtAge.setText("");
    }
    private boolean validatePatinet() {
        String patinetIdText = lblPatientId.getText();
        Pattern compile = Pattern.compile("[P][0-9]{3,}");
        Matcher matcher = compile.matcher(patinetIdText);
        boolean matches = matcher.matches();

        if (!matches) {
            new Alert(Alert.AlertType.ERROR, "Invalid Patient ID").show();
            return false;
        }
        String nameText = txtName.getText();
        boolean isNameValid = Pattern.compile("[A-Za-z]{3,}").matcher(nameText).matches();
        if (!isNameValid) {
            new Alert(Alert.AlertType.ERROR, "Invalid Patient Name").show();
            return false;
        }
        String gender_title = txtType.getText();
        Pattern compile1 = Pattern.compile("Male|Female");
        Matcher matcher1 = compile1.matcher(gender_title);
        boolean isTitelValid = matcher1.matches();

        if (!isTitelValid) {
            new Alert(Alert.AlertType.ERROR, "Invalid Gender").show();
            return false;
        }
        String numberText = txtNumber.getText();
        boolean isNumberValid = Pattern.compile("[(07)]\\d{9}|[+]\\d{11}").matcher(numberText).matches();
        if (!isNumberValid) {
            new Alert(Alert.AlertType.ERROR, "Invalid Patient Number").show();
            return false;
        }
        String ageText = txtAge.getText();
        boolean isAgeValid = Pattern.compile("\\d{2}").matcher(ageText).matches();
        if (!isAgeValid) {
            new Alert(Alert.AlertType.ERROR, "Invalid Patient Age").show();
            return false;
        }
        String emailText = txtEmail.getText();
        boolean isEmailValid = Pattern.compile("[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Z|a-z]{2,}").matcher(emailText).matches();
        if (!isEmailValid) {
            new Alert(Alert.AlertType.ERROR, "Invalid Patient Email").show();
            return false;
        }
        return true;
    }
    public void nameSearchOnAction(ActionEvent actionEvent) {
        String id = txtId.getText();

        try {
            PatientDto dto = patientModel.searchPatient(id);
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
        txtType.setText(dto.getSex());
        txtEmail.setText(dto.getEmail());
        //cmbBlood.setItems(dto.getBlood());
        txtAge.setText(dto.getAge());
    }

   /* public void btnSaveOnAction(ActionEvent actionEvent){
        String id = lblPatientId.getText();
        String name = txtName.getText();
        String number = txtNumber.getText();
        String address = txtAddress.getText();
        String sex = txtType.getText();
        String email = txtEmail.getText();
        String age = txtAge.getText();
        String blood = cmbBlood.getItems().toString();

        try{
            String id = lblPatientId.getText();
            String id = new PatientModel().
        }
    }*/
    public void btnSaveOnAction(ActionEvent actionEvent) {
        boolean isPatientValid = validatePatinet();

        if (isPatientValid) {
            String id = lblPatientId.getText();
            String name = txtName.getText();
            String number = txtNumber.getText();
            String address = txtAddress.getText();
            String sex = txtType.getText();
            String email = txtEmail.getText();
            String age = txtAge.getText();
            String blood = cmbBlood.getItems().toString();

            PatientDto itemDto = new PatientDto(id, name, number, address, sex, email, age, blood);

            try {
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
            new Alert(Alert.AlertType.ERROR, "Invalid Patient Details").show();
            lblPatientId.requestFocus();
        }
    }
    private void loadPatientsBloodGroup() {
        ObservableList<String> obList = FXCollections.observableArrayList();

        // Add "Male" and "Female" options
        obList.addAll("A_POSITIVE (A+)","A_NEGATIVE (A-)","B_POSITIVE (B+)", "B_NEGATIVE (B-)", "AB_POSITIVE (AB+)", "AB_NEGATIVE (AB-)", "O_POSITIVE (O+)", "O_NEGATIVE (O-)");
        cmbBlood.setItems(obList);
    }
    public void btnUpdateOnAction(ActionEvent actionEvent) {
       // boolean isPatientValid = validatePatinet();
        String Patient_id = txtId.getText();
        String Patient_name = txtName.getText();
        String Mobile_number = txtNumber.getText();
        String Address = txtAddress.getText();
        String Sex = txtType.getText();
        String Email = txtEmail.getText();
        String Blood = cmbBlood.getItems().toString();
        String Age = txtAge.getText();

        try {
            boolean isUpdated = patientModel.updatePatient(new PatientDto(Patient_id, Patient_name, Mobile_number, Address, Sex, Email, Blood, Age));
            if (isUpdated) {
                new Alert(Alert.AlertType.CONFIRMATION, "Patient updated").show();
                clearFields();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
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
