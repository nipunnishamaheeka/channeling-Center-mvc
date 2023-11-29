package lk.ijse.channelingCenter.controller;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Cursor;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import lk.ijse.channelingCenter.dto.DoctorDto;
import lk.ijse.channelingCenter.dto.tm.DoctorTm;
import lk.ijse.channelingCenter.model.DoctorModel;
import lk.ijse.channelingCenter.model.PatientModel;

import javax.swing.tree.AbstractLayoutCache;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;


public class DoctorFromController {
    public AnchorPane doctorPane;
    public Label lblDoctorId;
    public TextField txtFee;
    public TableColumn tblDrFee;
    public ComboBox cmbDoctorType;
    @FXML
    private TableColumn<?, ?> colUpdate;
    @FXML
    private TableColumn<?, ?> colDelete;
    @FXML
    private TableColumn<?, ?> tblAddress;

    @FXML
    private TableColumn<?, ?> tblEmail;

    @FXML
    private TableColumn<?, ?> tblId;

    @FXML
    private TableColumn<?, ?> tblName;

    @FXML
    private TableColumn<?, ?> tblNumber;

    @FXML
    private TableColumn<?, ?> tblType;

    @FXML
    private TableView<DoctorTm> tblDoctor;


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
    DoctorModel doctorModel = new DoctorModel();

    public void initialize() throws SQLException {
        setCellValueFactory();
        loadAllItems();
        setDoctorID();
        loadDoctorTypes();
    }
    private void setCellValueFactory() {
        tblId.setCellValueFactory(new PropertyValueFactory<>("id"));
        tblName.setCellValueFactory(new PropertyValueFactory<>("doctor_name"));
        tblAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        tblEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        tblNumber.setCellValueFactory(new PropertyValueFactory<>("number"));
        tblType.setCellValueFactory(new PropertyValueFactory<>("type"));
        tblDrFee.setCellValueFactory(new PropertyValueFactory<>("drFee"));
        colDelete.setCellValueFactory(new PropertyValueFactory<>("deleteButton"));
        //colUpdate.setCellValueFactory(new PropertyValueFactory<>("updateButton"));

    }

    private void loadDoctorTypes() {
        ObservableList<String> obList = FXCollections.observableArrayList();

        // Add "Dr Types" options
        obList.addAll("General Practitioner", "Cardiologist", "Dermatologist", "Endocrinologist", "Gastroenterologist", "Orthopedic Surgeon", "Neurologist", "Obstetrician/Gynecologist (OB/GYN)","Ophthalmologist", "Pediatrician","Psychiatrist","Urologist","Allergist/Immunologist","Anesthesiologist","Colon and Rectal Surgeon","Emergency Medicine Specialist","Hematologist","Infectious Disease Specialist","Medical Geneticist","Nephrologist","Otolaryngologist","Pathologist","Plastic Surgeon","Radiologist","Rheumatologist","Thoracic Surgeon","Urologist","Vascular Surgeon");
        cmbDoctorType.setItems(obList);
    }
    public void btnSaveOnAction(ActionEvent actionEvent) {
        boolean isDoctorValid = validateDoctor();
        if (isDoctorValid) {
            String id = lblDoctorId.getText();
            String name = txtName.getText();
            String address = txtAddress.getText();
            String email = txtEmail.getText();
            String number = txtNumber.getText();
            String type = String.valueOf(cmbDoctorType.getValue());
            double fee = Double.parseDouble(txtFee.getText());

            DoctorDto itemDto = new DoctorDto(id, name, address, email, number, type,fee);

            try {
                DoctorModel doctorModel = new DoctorModel();
                boolean isSaved = doctorModel.saveDoctor(itemDto);

                if (isSaved) {
                    new Alert(Alert.AlertType.CONFIRMATION, "Doctor Saved!", ButtonType.OK).show();
                    clearFields();
                    loadAllItems();
                } else {
                    new Alert(Alert.AlertType.ERROR, "Doctor Not Saved!", ButtonType.OK).show();
                }

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }else {
            new Alert(Alert.AlertType.ERROR, "Invalid Doctor Details", ButtonType.OK).show();
        }
    }
    public void btnUpdateOnAction(ActionEvent actionEvent) {
        boolean isDoctorValid = validateDoctor();
        String id = lblDoctorId.getText();
        String name = txtName.getText();
        String address = txtAddress.getText();
        String email = txtEmail.getText();
        String number = txtNumber.getText();
        String type = String.valueOf(cmbDoctorType.getValue());
        double fee = Double.parseDouble(txtFee.getText());

        try {
            boolean isUpdated = doctorModel.updateDoctor(new DoctorDto(id, name, address, email, number, type, fee));
            if (isUpdated) {
                new Alert(Alert.AlertType.CONFIRMATION, "Doctor updated!").show();
                clearFields();
                loadAllItems();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }
    private void setFields(DoctorDto dto) {
        lblDoctorId.setText(dto.getId());
        txtName.setText(dto.getName());
        txtAddress.setText(dto.getAddress());
        txtEmail.setText(dto.getEmail());
        txtNumber.setText(dto.getNumber());
        cmbDoctorType.setValue(dto.getType());
        txtFee.setText(String.valueOf(dto.getDrFee()));
    }

    private void clearFields() {
        lblDoctorId.setText("");
        txtName.clear();
        txtAddress.clear();
        txtEmail.clear();
        txtNumber.clear();
        cmbDoctorType.setValue(null);
        txtFee.clear();
    }

    private void setFontAwesomeIcons() {

        tblDoctor.getItems().forEach(item -> {
            Button deleteButton = item.getDeleteButton();

            FontAwesomeIconView deleteIcon = new FontAwesomeIconView(FontAwesomeIcon.TRASH);

            deleteButton.setGraphic(deleteIcon);

        });
    }

    private void loadAllItems() throws SQLException {

        ObservableList<DoctorTm> obList = FXCollections.observableArrayList();

        try {
            List<DoctorDto> dtoList = doctorModel.getAllDoctor();
            for (DoctorDto dto : dtoList) {
                Button deleteButton = new Button();
                Button updateButton = new Button();

                deleteButton.setCursor(Cursor.HAND);
                updateButton.setCursor(Cursor.HAND);

                deleteButton.setOnAction((e) -> {
                    ButtonType yes = new ButtonType("Yes", ButtonBar.ButtonData.OK_DONE);
                    ButtonType no = new ButtonType("No", ButtonBar.ButtonData.CANCEL_CLOSE);

                    Optional<ButtonType> result = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure to delete this doctor?", yes, no).showAndWait();
                    if (result.orElse(no) == yes) {
                        int selectedIndex = tblDoctor.getSelectionModel().getSelectedIndex();
                        System.out.println("badu hari");
                        String code = (String) tblId.getCellData(selectedIndex);
                        System.out.println(code);
                        try {
                            doctorModel.deleteDoctor(code);
                        } catch (SQLException ex) {
                            System.out.println(ex.getMessage());
                        }
                        obList.remove(selectedIndex);
                        tblDoctor.refresh();
                    }
                });
//                updateButton.setOnAction((e) -> {
//                    int selectedIndex = tblDoctor.getSelectionModel().getSelectedIndex();
//                    String code = (String) tblId.getCellData(selectedIndex);
//                    System.out.println(code);
//                    try {
//                        doctorPane.getChildren().clear();
//                        //doctorPane.getChildren().add(FXMLLoader.load(this.getClass().getResource("/View/doctorDetails.fxml")));
//                    } catch (Exception exception) {
//                        exception.printStackTrace();
//                    }
//                });
                obList.add(
                        new DoctorTm(
                                dto.getId(),
                                dto.getName(),
                                dto.getAddress(),
                                dto.getEmail(),
                                dto.getNumber(),
                                dto.getType(),
                                String.valueOf(dto.getDrFee()),
                                deleteButton
                        )
                );
            }
            tblDoctor.setItems(obList);
            setFontAwesomeIcons();
            setDoctorID();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
    public void btnClerOnAction(ActionEvent actionEvent) {
        clearFields();
    }

    public void btnRefershOnAction(MouseEvent mouseEvent) throws SQLException {
        setDoctorID();
        loadAllItems();
    }

    private void setDoctorID() {
        try {
            lblDoctorId.setText(new DoctorModel().autoGenarateDoctorId());
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    private boolean validateDoctor() {
        String nameText = txtName.getText();
        boolean isNameValid = nameText.matches("[A-Za-z][A-Za-z. ]{3,}");
        if (!isNameValid) {
            new Alert((Alert.AlertType.ERROR), "Invalid Name").show();
            return false;
        }
        String emailText = txtEmail.getText();
        boolean isEmailValid = Pattern.compile("[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Z|a-z]{2,}").matcher(emailText).matches();
        if (!isEmailValid) {
            new Alert(Alert.AlertType.ERROR, "Invalid Doctor Email").show();
            return false;
        }
        String numberText = txtNumber.getText();
        boolean isNumberValid = Pattern.compile("[07]\\d{9}").matcher(numberText).matches();
        if (!isNumberValid) {
            new Alert(Alert.AlertType.ERROR, "Invalid Doctor Number").show();
            return false;
        }
        return true;

    }

    public void mobileNumberSearchOnAction(ActionEvent actionEvent) {
        String number = txtNumber.getText();
        try {
            DoctorDto dto = doctorModel.searchDoctorByNumber(number);
            setFields(dto);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
