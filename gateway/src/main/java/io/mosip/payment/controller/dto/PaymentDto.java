package io.mosip.payment.controller.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class PaymentDto {
    private String transactionId;
    private String status;
}
