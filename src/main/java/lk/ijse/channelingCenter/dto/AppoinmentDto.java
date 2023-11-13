package lk.ijse.channelingCenter.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@Data
@AllArgsConstructor
@ToString
public class AppoinmentDto {

    private String age;
    private String appoinment_id;
    private String date;
    private String dr_name;
    private String fee_status;
    private String patient_name;
    private String time;
}
