package lk.ijse.channelingCenter.dto.tm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class PatientTm {
    private String patient_id;
    private String patient_name;
    private String mobile_number;
    private String address;
    private String sex;
    private String email;
    private String age;
    private String blood;
}
