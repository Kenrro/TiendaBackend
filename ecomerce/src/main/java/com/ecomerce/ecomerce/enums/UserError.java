package com.ecomerce.ecomerce.enums;

import org.springframework.http.HttpStatus;

public enum UserError implements IError {
    BAD_CREDENTIALS(HttpStatus.NOT_FOUND, "Bad credentials"),
    ERROR_DELETING_USER(HttpStatus.INTERNAL_SERVER_ERROR, "Error deleting user"),
    ERROR_CREATING_USER(HttpStatus.INTERNAL_SERVER_ERROR, "Error creating user");
    
    UserError(HttpStatus httpStatus, String message){
        this.httpStatus = httpStatus;
        this.message = message;
    }
    HttpStatus httpStatus;
    String message;
    @Override
    public HttpStatus getHttpStatus() {
        return httpStatus;    
    }

    @Override
    public String getMessage() {
        return message;    
    }

}
