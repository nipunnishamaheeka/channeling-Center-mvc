package lk.ijse.channelingCenter.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class MedicineDto {
    private String medi_code;
    private String medicine_name;
    private String description;
    private String qty;
    private String unit_price;



}
