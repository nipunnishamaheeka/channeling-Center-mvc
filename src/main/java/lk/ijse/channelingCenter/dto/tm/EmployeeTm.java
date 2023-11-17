package lk.ijse.channelingCenter.dto.tm;

import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@AllArgsConstructor
@NoArgsConstructor
@Data
public class EmployeeTm {
    private String emp_id;
    private String emp_name;
    private String emp_address;
    private String mobile_number;
    private String qualification;
    private String job_role;
    private String salary;
    private Button deleteButton;
    private Button updateButton;

    public EmployeeTm(String emp_id, String emp_name, String emp_address, String email, String qualification, String job_role, String salary) {
        this.emp_id = emp_id;
        this.emp_name = emp_name;
        this.emp_address = emp_address;
        this.mobile_number = email;
        this.qualification = qualification;
        this.job_role = job_role;
        this.salary = salary;
    }
}
