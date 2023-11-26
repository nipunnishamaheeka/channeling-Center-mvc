package lk.ijse.channelingCenter.dto.tm;

import javafx.scene.control.Button;
import lombok.*;

import java.awt.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode

public class AppoinmentTm {
    private String appoinment_id;
    private String date;
    private String patient_id;
    private String age;
    private String id;
    private String doctor_name;
    private String patientName;
    private String status;
    private Button deleteButton;

//    private javafx.scene.control.Button deleteButton;
//    private javafx.scene.control.Button updateButton;


}
