package lk.ijse.channelingCenter.controller;

import com.jfoenix.controls.JFXComboBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import lk.ijse.channelingCenter.dto.*;
import lk.ijse.channelingCenter.model.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

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
    public JFXComboBox <String>cmboPationId;
    public JFXComboBox cmbDoctor;

    public void initialize() {
        setLabReportsID();
        loadPatientsIds();
        // loadDoctorIds();
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

    private void loadPatientsIds() {

        ObservableList<String> obList = FXCollections.observableArrayList();
        try {
            List<PatientDto> cusList = new PatientModel().getAllPatient();

            for (PatientDto dto : cusList) {
                obList.add(dto.getPatient_id());
            }
            cmboPationId.setItems(obList);
            //cmbPatientId.setItems(obList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

//    private void loadDoctorIds() {
//
//        ObservableList<String> obList = FXCollections.observableArrayList();
//        try {
//            List<DoctorDto> cusList = new DoctorModel().getAllDoctor();
//
//            for (DoctorDto dto : cusList) {
//                obList.add(dto.getId());
//            }
//            cmboPationId.setItems(obList);
//            //cmbPatientId.setItems(obList);
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//    }
    @FXML
    void cmbPatientIdOnAction(ActionEvent event) {
        try {
            String name = new PatientModel().getPatientName(cmboPationId.getValue());
            lblPatientName.setText(name);

            String gender = new PatientModel().getPatientGender(cmboPationId.getValue());
            lblGender.setText(gender);

            String age = new PatientModel().getPatientAge(cmboPationId.getValue());
            lblAge.setText(age);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public void btnUpdateOnAction(ActionEvent actionEvent) {
    }

    public void btnClearOnAction(ActionEvent actionEvent) {
    }

    public void cmbDoctorIdOnAction(ActionEvent actionEvent) {
    }
}
