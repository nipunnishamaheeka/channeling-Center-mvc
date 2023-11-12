package lk.ijse.channelingCenter.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AppoinmentListModel {
    private String appoinment_id;
    private String time;
    private String date;
    private String patient_name;

    private String patient_age;

    private String doctor;
    private String fee_status;
}
