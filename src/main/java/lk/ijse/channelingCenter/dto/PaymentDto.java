package lk.ijse.channelingCenter.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class PaymentDto {
    private String payment_id;
    private String payment_email;
    private String payment_date;
    private String payment_time;
    private String amount;
    private String payment_method;
    private String patient_id;
    private String appoinment_id;
}
