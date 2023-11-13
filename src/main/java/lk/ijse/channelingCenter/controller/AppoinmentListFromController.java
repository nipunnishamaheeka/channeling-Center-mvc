package lk.ijse.channelingCenter.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import lk.ijse.channelingCenter.dto.AppoinmentDto;
import lk.ijse.channelingCenter.model.AppoinmentListModel;

public class AppoinmentListFromController {
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

    public void setData(AppoinmentDto appoinmentDto) {

        patientName.setText(appoinmentDto.getPatient_name());
        age.setText(appoinmentDto.getAge());
        appoinmentId.setText(appoinmentDto.getAppoinment_id());
        date.setText(appoinmentDto.getDate());
        drName.setText(appoinmentDto.getDr_name());
        feeStatus.setText(appoinmentDto.getFee_status());
        time.setText(appoinmentDto.getTime());

    }
}