package lk.ijse.channelingCenter.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor

public class DoctorDto {
    private String doc_id;
    private String appoinment_id;
    private String salary_id;
    private String qualification;
    private String doc_name;
}
