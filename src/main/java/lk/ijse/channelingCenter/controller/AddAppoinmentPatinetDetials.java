package lk.ijse.channelingCenter.controller;

import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import lk.ijse.channelingCenter.dto.EmployeeDto;
import lk.ijse.channelingCenter.dto.PatientDto;
import lk.ijse.channelingCenter.model.EmployeeModel;
import lk.ijse.channelingCenter.model.PatientModel;

import java.sql.SQLException;

public class AddAppoinmentPatinetDetials {
    private final PatientModel patientModel = new PatientModel();
    public Label lblPatientId;
    public TextField txtAge;
    public TextField txtMail;
    public TextField txtName;

    public static String name = null;
    public void initialize() throws SQLException {

        setPatientID();
        setCellValueFactory();
       // loadPatientsBloodGroup();
    }

    private void setCellValueFactory() {


    }

   public void btnOkOnAction(ActionEvent actionEvent) {
// name = txtName.getText();
// age = txtAge.getText();
// mail = txtMail.getText();

    }

    private void clearFields() {
        txtName.clear();
        txtAge.clear();
        txtMail.clear();
    }

    private void setPatientID() {
        try {
            lblPatientId.setText(new PatientModel().autoGenaratePatientId());
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
