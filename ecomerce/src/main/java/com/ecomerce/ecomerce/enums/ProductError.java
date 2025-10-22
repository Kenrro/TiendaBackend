package com.ecomerce.ecomerce.enums;

import org.springframework.http.HttpStatus;

public enum ProductError implements IError {
    PRODUCT_NOT_FOUND(HttpStatus.NOT_FOUND, "Product not found"),
    ALREDY_EXIST_A_PRODUCT_WITH_THE_SAME_NAME(HttpStatus.CONFLICT, "Alredy exist a product with the same name"),
    ERROR_CREATING_PRODUCT(HttpStatus.INTERNAL_SERVER_ERROR, "an error ocurred while creating the product"),
    ERROR_UPDATING_PRODUCT(HttpStatus.INTERNAL_SERVER_ERROR, "an error ocurred while updating the product"),
    ERROR_DELETING_PRODUCT(HttpStatus.INTERNAL_SERVER_ERROR, "an error ocurred while deleting the product"),
    CONSTRAINS_VIOLATION(HttpStatus.CONFLICT, "Duplicate entry");
    ProductError(HttpStatus httpStatus, String message){
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
