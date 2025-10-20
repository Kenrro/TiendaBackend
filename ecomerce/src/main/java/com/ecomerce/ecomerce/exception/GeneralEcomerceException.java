package com.ecomerce.ecomerce.exception;

import org.springframework.http.HttpStatus;

import com.ecomerce.ecomerce.enums.IError;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class GeneralEcomerceException extends RuntimeException {

    final HttpStatus httpStatus;
    final String description;

    public <T extends IError> GeneralEcomerceException(T error){
        super(error.getMessage());
        this.httpStatus = error.getHttpStatus();
        this.description = error.getMessage();
    }
}
