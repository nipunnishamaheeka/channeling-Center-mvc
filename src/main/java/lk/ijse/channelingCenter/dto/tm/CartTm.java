package lk.ijse.channelingCenter.dto.tm;

import com.jfoenix.controls.JFXButton;
import javafx.scene.control.Button;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor
@Data
public class CartTm {
    private String M_Code;
    private String Dis;
    private double Qty;
    private double U_price;
    private double tot;
    private JFXButton btn;

    /*public CartTm(String code, String text, String qty, String text1, javafx.scene.control.Button btn) {
        this.M_Code = code;
        this.Dis = text;
        this.Qty = qty;
        this.U_price = text1;
        this.btn = btn;
    }*/
}
