package lk.ijse.channelingCenter.dto.tm;

import javafx.scene.control.Button;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class DoctorTm {
    private String id;
    private String doctor_name;
    private String address;
    private String email;
    private String number;
    private String type;
    private Button deleteButton;
    private Button updateButton;
}
