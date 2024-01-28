package io.mosip.payment.controller;

import io.mosip.payment.controller.dto.BaseRequestDto;
import io.mosip.payment.controller.dto.BaseResponseDto;
import io.mosip.payment.controller.dto.PaymentDto;
import io.mosip.payment.controller.dto.PaymentRequestDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.UUID;

@RestController
@RequestMapping("/payment")
public class PaymentController {

    private LinkedHashMap<String, PaymentDto> paymentMap = new LinkedHashMap<>();

    @PostMapping(value = "/createPaymentRequest", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BaseResponseDto> createPayment(@RequestBody(required = true) BaseRequestDto<PaymentRequestDto> baseRequestDto) {
        PaymentDto dto = new PaymentDto();
        PaymentRequestDto paymentRequestDto = (PaymentRequestDto) baseRequestDto.getRequest();
        dto.setStatus(paymentRequestDto.getStatus());
        dto.setTransactionId(UUID.randomUUID().toString());
        paymentMap.put(dto.getTransactionId(), dto);

        BaseResponseDto<PaymentDto> baseResponseDto = new BaseResponseDto<>();
        baseResponseDto.setResponse(dto);
        baseResponseDto.setResponseTime(LocalDateTime.now());
        baseResponseDto.setVersion("1.0");
        baseResponseDto.setId("io.mosip.payment.gateway");

        ResponseEntity<BaseResponseDto> response = new ResponseEntity<>(baseResponseDto, HttpStatus.OK);
        return response;
    }

    @GetMapping(value = "/verifyPayment/{transactionId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BaseResponseDto> verifyPayment(@PathVariable("transactionId") String transactionId) {
        PaymentDto dto = paymentMap.get(transactionId);

        Boolean status = false;
        if(dto != null && dto.getStatus().equals("SUCCESS"))
            status = true;

        BaseResponseDto<Boolean> baseResponseDto = new BaseResponseDto<>();
        baseResponseDto.setResponse(status);
        baseResponseDto.setResponseTime(LocalDateTime.now());
        baseResponseDto.setVersion("1.0");
        baseResponseDto.setId("io.mosip.payment.gateway");

        ResponseEntity<BaseResponseDto> response = new ResponseEntity<BaseResponseDto>(baseResponseDto, HttpStatus.OK);
        return response;
    }
}
