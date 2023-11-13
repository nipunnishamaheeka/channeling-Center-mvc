package lk.ijse.channelingCenter.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import lk.ijse.channelingCenter.dto.DoctorDto;
import lk.ijse.channelingCenter.dto.PatientDto;
import lk.ijse.channelingCenter.model.DoctorModel;
import lk.ijse.channelingCenter.model.PatientModel;

import java.sql.SQLException;

public class AddPatientFromController {
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

    @FXML
    private TextField txtblood;

    public void idSearchOnAction(ActionEvent actionEvent) {
        String id = txtId.getText();

        try {
            PatientDto dto = PatientModel.searchPatient(id);
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
        txtId.setText(dto.getPatient_id());
        txtName.setText(dto.getPatient_name());
        txtNumber.setText(dto.getMobile_number());
        txtAddress.setText(dto.getAddress());
        txtType.setText(dto.getSex());
        txtEmail.setText(dto.getEmail());
        txtblood.setText(dto.getBlood());
    }

    public void btnClerOnAction(ActionEvent actionEvent) {
        clearFields();
    }

    private void clearFields() {
        txtId.setText("");
        txtName.setText("");
        txtNumber.setText("");
        txtAddress.setText("");
        txtType.setText("");
        txtEmail.setText("");
        txtblood.setText("");
    }

    public void btnSaveOnAction(ActionEvent actionEvent) {
    String id = txtId.getText();
    String name = txtName.getText();
    String number = txtNumber.getText();
    String address = txtAddress.getText();
    String sex = txtType.getText();
    String email = txtEmail.getText();
    String blood = txtblood.getText();


        var itemDto = new PatientDto(id,name,number,address,sex,email,blood);

        try {
            boolean isSaved = PatientModel.savePatient(itemDto);
            if (isSaved) {
                new Alert(Alert.AlertType.CONFIRMATION, "Patient saved!").show();
                clearFields();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }


    }

    public void btnUpdateOnAction(ActionEvent actionEvent) {
        String Patient_id = txtId.getText();
        String Patient_name = txtName.getText();
        String Mobile_number = txtNumber.getText();
        String Address = txtAddress.getText();
        String Sex = txtType.getText();
        String Email = txtEmail.getText();
        String Blood = txtblood.getText();
//        var model = new ItemModel();
        try {
            boolean isUpdated = PatientModel.updatePatient(new PatientDto(Patient_id,Patient_name,Mobile_number,Address,Sex,Email,Blood));
            if (isUpdated) {
                new Alert(Alert.AlertType.CONFIRMATION, "Patient updated").show();
                clearFields();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }

    }

    public void btnDeleteOnAction(ActionEvent actionEvent) {
        String id = txtId.getText();

        try {
            boolean isDeleted = PatientModel.deletePatient(id);

            if(isDeleted) {
                new Alert(Alert.AlertType.CONFIRMATION, "Patient deleted!").show();
                clearFields();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }
}
