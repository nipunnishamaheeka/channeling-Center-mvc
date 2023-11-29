package lk.ijse.channelingCenter.dto.tm;

import javafx.scene.control.Button;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CompleteOrdersTm {
    String appoinment_id;
    String patientName;
    String doctorName;
    Button btn;
}
