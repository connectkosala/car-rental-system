package lk.ijse.spring.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class UserDTO {
    private String nic;
    private String email;
    private String user_Name;
    private String password;
    private String drivingNo;
    private String address;
    private String contact;
}
