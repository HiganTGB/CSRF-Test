package appointmenthospital.authservice.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TransferRequest {
    private String receiver;
    private Long amount;


}
