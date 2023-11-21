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
    private String stock;
    private String supplier_id;
    private String location;
    private Button deleteButton;
    private Button updateButton;
}
