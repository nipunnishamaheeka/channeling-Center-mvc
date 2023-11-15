package lk.ijse.channelingCenter.controller;

import com.jfoenix.controls.JFXComboBox;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
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
    private JFXComboBox<?> cmbPatientId;

    @FXML
    private JFXComboBox<?> cmbPatientId1;

    @FXML
    private Label lblOrderDate;

    @FXML
    private Label lblOrderId;

    @FXML
    private Label lblPatientName;

    @FXML
    private TextField txtAge;




    @FXML
    void btnClerOnAction(ActionEvent event) {

    }
    @FXML
    void btnSaveOnAction(ActionEvent event) {

    }

    @FXML
    void cmbPatientOnAction(ActionEvent event) {

    }
}