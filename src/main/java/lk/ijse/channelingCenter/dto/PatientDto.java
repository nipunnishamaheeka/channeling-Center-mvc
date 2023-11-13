package lk.ijse.channelingCenter.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class PatientDto {
    private String patient_id;
    private String patient_name;
    private String mobile_number;
    private String address;
    private String sex;
    private String email;
    private String blood;

    public PatientDto(String patientId, String patientName, String address, String sex, String email, String blood) {
    }
}
