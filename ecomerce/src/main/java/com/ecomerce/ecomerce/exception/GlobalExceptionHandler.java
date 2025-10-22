package com.ecomerce.ecomerce.exception;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.ecomerce.ecomerce.dto.error.ErrorDto;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(GeneralEcomerceException.class)
    public ResponseEntity<?> handleTestException(
        GeneralEcomerceException e,
        WebRequest request
    ){
        Map<String, Object> body = new HashMap<>();
        body.put("Timestamp", LocalDateTime.now());
        body.put("Status", e.getHttpStatus());
        body.put("Error", new ErrorDto(e.getMessage()));
        return ResponseEntity.status(e.getHttpStatus()).body(body);
    }
}
