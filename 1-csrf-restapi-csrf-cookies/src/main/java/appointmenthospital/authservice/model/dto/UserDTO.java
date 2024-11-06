package appointmenthospital.authservice.model.dto;

import appointmenthospital.authservice.model.entity.User;
import lombok.Data;

import java.sql.Timestamp;

@Data
public class UserDTO {
    private Long id;
    private String fullName;
    private boolean isEnabled;
    private long money;
    private Timestamp createAt;
    private Timestamp updateAt;
}
