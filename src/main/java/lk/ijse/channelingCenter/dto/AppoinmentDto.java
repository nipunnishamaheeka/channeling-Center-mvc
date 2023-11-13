package lk.ijse.channelingCenter.dto;

import lk.ijse.channelingCenter.dto.tm.AppoinmentTm;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Data
@AllArgsConstructor
@ToString
public class AppoinmentDto {

    private String patient_id;
    private String appoinment_id;
    private String time;
    private String date;
    private String id;
    private String age;
    private String fee_status;

}
