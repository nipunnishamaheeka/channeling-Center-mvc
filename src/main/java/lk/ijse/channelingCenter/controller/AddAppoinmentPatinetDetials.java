package lk.ijse.channelingCenter.controller;

import javafx.event.ActionEvent;
import javafx.scene.control.*;
import lk.ijse.channelingCenter.dto.EmployeeDto;
import lk.ijse.channelingCenter.dto.PatientDto;
import lk.ijse.channelingCenter.model.EmployeeModel;
import lk.ijse.channelingCenter.model.PatientModel;

import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AddAppoinmentPatinetDetials {
    private final PatientModel patientModel = new PatientModel();
    public Label lblPatientId;
    public TextField txtAge;
    public TextField txtMail;
    public TextField txtName;

    public static String name = null;
    public TextField txtEmail;
    public TextField txtAddress;
    public ComboBox cmbBlood;
    public TextField txtType;
    public Button btnOk;
    public TextField txtNumber;

    public void initialize() throws SQLException {

        setPatientID();
        // loadPatientsBloodGroup();
    }



    public void btnOkOnAction(ActionEvent actionEvent) {
        boolean isPatientValid = validatePatinet();

        if (isPatientValid) {
            String id = lblPatientId.getText();
            String name = txtName.getText();
            String number = txtNumber.getText();
            String address = txtAddress.getText();
            String sex = txtType.getText();
            String email = txtEmail.getText();
            String age = txtAge.getText();
            String blood = (String) cmbBlood.getValue();

            PatientDto itemDto = new PatientDto(id, name, number, address, sex, email, age,blood);

            try {
                PatientModel patientModel = new PatientModel();
                boolean isSaved = patientModel.savePatient(itemDto);

                if (isSaved) {
                    new Alert(Alert.AlertType.CONFIRMATION, "Patient saved!").show();
                    clearFields();
//                   AddAppoinmentFromController addAppoinmentFromController = new AddAppoinmentFromController();
//                     addAppoinmentFromController.initialize();

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

    private void clearFields() {
        lblPatientId.setText("");
        txtName.setText("");
        txtNumber.setText("");
        txtAddress.setText("");
        txtType.setText("");
        txtEmail.setText("");
        cmbBlood.setItems(null);
        txtAge.setText("");
    }
    private void setFields(PatientDto dto) {
        lblPatientId.setText(dto.getPatient_id());
        txtName.setText(dto.getPatient_name());
        txtNumber.setText(dto.getMobile_number());
        txtAddress.setText(dto.getAddress());
        txtType.setText(dto.getSex());
        txtEmail.setText(dto.getEmail());
        // cmbBlood.setItems(dto.getBlood());
        txtAge.setText(dto.getAge());
    }
    private void setPatientID() {
        try {
            lblPatientId.setText(new PatientModel().autoGenaratePatientId());
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    private boolean validatePatinet() {
//        String patinetIdText = lblPatientId.getText();
//        Pattern compile = Pattern.compile("[P][0-9]{3,}");
//        Matcher matcher = compile.matcher(patinetIdText);
//        boolean matches = matcher.matches();
//
//        if (!matches) {
//            new Alert(Alert.AlertType.ERROR, "Invalid Patient ID").show();
//            return false;
//        }
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
    }

    public void cmbBloodGroupOnAction(ActionEvent actionEvent) {
    }
}
