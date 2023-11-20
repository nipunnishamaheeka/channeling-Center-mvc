package lk.ijse.channelingCenter.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.channelingCenter.dto.LabReportDto;
import lk.ijse.channelingCenter.dto.LoginDto;
import lk.ijse.channelingCenter.dto.PatientDto;
import lk.ijse.channelingCenter.model.LabReportModel;
import lk.ijse.channelingCenter.model.LoginModel;
import lk.ijse.channelingCenter.model.PatientModel;

import java.io.IOException;
import java.sql.SQLException;

public class LabReportsFromController {
    public AnchorPane labReportsPane;
    public Label lblReportId;
    public Label lblPatientName;
    public Label lblDoctorName;
    public Label lblGender;
    public Label lblAge;
    public TableView tblReport;
    public TableColumn colReportId;
    public TableColumn colPatientId;
    public TableColumn colDoctorName;
    public TableColumn colAge;
    public TableColumn colGender;

    public void initialize() {
        setLabReportsID();
    }

    public void btnSaveOnAction(ActionEvent actionEvent) {
        String id = lblReportId.getText();
        String PName = lblPatientName.getText();
        String DName = lblDoctorName.getText();
        String Gender = lblGender.getText();

        LabReportModel labReportModel = new LabReportModel();
        try {
            boolean isSaved = labReportModel.saveLabReport(new LabReportDto(id, PName, DName, Gender));
            if (isSaved) {
                new Alert(Alert.AlertType.CONFIRMATION, "User Added Successfully").show();
                return;
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    private void setLabReportsID() {
        try {
            lblReportId.setText(new LabReportModel().autoGenarateLabReportId());
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }


    public void btnUpdateOnAction(ActionEvent actionEvent) {
    }

    public void btnClearOnAction(ActionEvent actionEvent) {
    }
}
