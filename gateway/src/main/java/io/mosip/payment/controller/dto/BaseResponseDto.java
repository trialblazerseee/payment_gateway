package io.mosip.payment.controller.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Data
@Setter
@Getter
public class BaseResponseDto<T> {
    private String id;
    private String version;
    private LocalDateTime responseTime;
    private T response;
}
