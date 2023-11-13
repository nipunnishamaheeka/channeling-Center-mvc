package lk.ijse.channelingCenter.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import lk.ijse.channelingCenter.dto.AppoinmentDto;
import lk.ijse.channelingCenter.dto.PatientDto;
import lk.ijse.channelingCenter.model.AppoinmentModel;
import lk.ijse.channelingCenter.model.PatientModel;

import java.sql.SQLException;


public class AddAppoinmentFromController {
    @FXML
    private AnchorPane addPatient;
    @FXML
    private TextField txtAge;

    @FXML
    private TextField txtDate;

    @FXML
    private TextField txtDrName;

    @FXML
    private TextField txtFee;

    @FXML
    private TextField txtId;

    @FXML
    private TextField txtPName;

    @FXML
    private TextField txtTime;


    @FXML
    void btnClerOnAction(ActionEvent event) {
        clearFields();
    }

    private void clearFields() {
        txtId.clear();
        txtPName.clear();
        txtAge.clear();
        txtDrName.clear();
        txtTime.clear();
        txtDate.clear();
        txtFee.clear();
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        String id = txtId.getText();

        try {
            boolean isDeleted = AppoinmentModel.deleteAppoinment(id);

            if(isDeleted) {
                new Alert(Alert.AlertType.CONFIRMATION, "Appoinment deleted!").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }

    }

    @FXML
    void btnSaveOnAction(ActionEvent event) {
        String id = txtId.getText();
        String pname = txtPName.getText();
        String age = txtAge.getText();
        String drname = txtDrName.getText();
        String time = txtTime.getText();
        String date = txtDate.getText();
        String feestatus = txtFee.getText();



        var AppoinmentDto = new AppoinmentDto(id, pname, age, drname, time, date, feestatus);

        try {
            boolean isSaved = AppoinmentModel.saveAppoinment(AppoinmentDto);
            if (isSaved) {
                new Alert(Alert.AlertType.CONFIRMATION, "Appoinment saved!").show();
                clearFields();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }


    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        String id = txtId.getText();
        String pname = txtPName.getText();
        String age = txtAge.getText();
        String drname = txtDrName.getText();
        String time = txtTime.getText();
        String date = txtDate.getText();
        String feestatus = txtFee.getText();

//        var model = new ItemModel();
        try {
            boolean isUpdated = AppoinmentModel.updateAppoinment(new AppoinmentDto(id, pname, age, drname, time, date, feestatus));
            if (isUpdated) {
                new Alert(Alert.AlertType.CONFIRMATION, "Appoinment updated").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }

    }

    public void idSearchOnAction(ActionEvent actionEvent) {
        String id = txtId.getText();

        try {
            AppoinmentDto dto = AppoinmentModel.searchAppoinment(id);
            if (dto != null) {
                setFields(dto);
            } else {
                new Alert(Alert.AlertType.INFORMATION, "item not found!").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    private void setFields(AppoinmentDto dto) {
        txtId.setText(dto.getAppoinment_id());
        txtPName.setText(dto.getId());
        txtAge.setText(dto.getAge());
        txtDrName.setText(dto.getPatient_id());
        txtTime.setText(dto.getTime());
        txtDate.setText(dto.getDate());
        txtFee.setText(dto.getFee_status());


    }
}
