package lk.ijse.channelingCenter.dto.tm;

import javafx.scene.control.Button;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class MedicineTm {
    private String medi_code;
    private String medicine_name;
    private String description;
    private String qty;
    private String unit_price;
//    private Button deleteButton;
//    private Button updateButton;

}
