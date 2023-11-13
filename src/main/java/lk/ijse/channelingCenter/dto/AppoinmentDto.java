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

    private String patient_id;
    private String appoinment_id;
    private String age;
    private String id;
    private String time;
    private String date;
    private String fee_status;
}
