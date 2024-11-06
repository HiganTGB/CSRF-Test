package appointmenthospital.authservice.model.dto;

import lombok.Data;

@Data
public class TransferRequest {
    private String receiver;
    private Long amount;
}
