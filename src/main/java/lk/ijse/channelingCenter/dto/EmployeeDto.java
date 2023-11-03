package lk.ijse.channelingCenter.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class EmployeeDto {
    private String emp_id;
    private String emp_name;
    private String emp_address;
    private String email;
    private String qualification;
}
