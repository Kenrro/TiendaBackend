package com.ecomerce.ecomerce.exception;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
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
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
            HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        Map<String, Object> body = new HashMap<>();
        List<String> reason = new ArrayList<>();
        
        for(FieldError error : ex.getBindingResult().getFieldErrors()){
            reason.add(String.format("%s - %s", error.getField(), error.getDefaultMessage()));
        }
        body.put("Status", HttpStatus.BAD_REQUEST);
        body.put("Reasons", reason);
        body.put("Timestamp", LocalDateTime.now());
        return ResponseEntity.status(status).body(body);
        
    }
}
