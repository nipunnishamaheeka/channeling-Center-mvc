package lk.ijse.channelingCenter.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor

public class DoctorDto {
    private String id;
    private String name;
    private String address;
    private String email;
    private String number;
    private String type;
    private double drFee;

}
