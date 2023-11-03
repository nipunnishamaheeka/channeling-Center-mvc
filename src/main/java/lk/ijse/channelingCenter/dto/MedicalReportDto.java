package lk.ijse.channelingCenter.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class MedicalReportDto {
    private String doc_id;
    private String patient_id;
    private String patient_name;
    private String date;
}
