package lk.ijse.channelingCenter.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class LabReportDto {
    private String patient_id;
    private String l_report;
    private String date;
    private String time;
}
