package io.mosip.payment.controller.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Data
@Setter
@Getter
public class BaseRequestDto<T> {
    private String id;
    private String metadata;
    private String version;
    private LocalDateTime requestTime;
    private T request;
}
