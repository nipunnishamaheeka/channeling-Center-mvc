package lk.ijse.channelingCenter.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import lk.ijse.channelingCenter.model.AppoinmentListModel;

import java.net.URL;
import java.util.ResourceBundle;

public class AppoinmentListFromController implements Initializable {
    @FXML
    private Label age;

    @FXML
    private Label appoinmentId;

    @FXML
    private Label date;

    @FXML
    private Label drName;

    @FXML
    private Label feeStatus;

    @FXML
    private Label patientName;

    @FXML
    private Label time;

    public void setDate(AppoinmentListModel appoinmentListModel) {

        patientName.setText(appoinmentListModel.getPatient_name());
        age.setText(appoinmentListModel.getPatient_age());
        appoinmentId.setText(appoinmentListModel.getAppoinment_id());
        date.setText(appoinmentListModel.getDate());
        drName.setText(appoinmentListModel.getDoctor());
        feeStatus.setText(appoinmentListModel.getFee_status());
        time.setText(appoinmentListModel.getTime());

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
