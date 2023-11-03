package lk.ijse.channelingCenter.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@AllArgsConstructor
public class AppoinmentDto {

    private String patient_id;
    private String appoinment_id;
    private String appoinment_date;
    private String appoinment_time;
    private String emp_id;
}
