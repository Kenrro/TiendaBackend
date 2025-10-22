package com.ecomerce.ecomerce.enums;

import org.springframework.http.HttpStatus;

public enum CartError implements IError {
    PRODUCT_IS_NOT_IN_THE_CART(HttpStatus.NOT_FOUND, "This product is not in the cart"),
    PRODUCT_NOT_ADDED_TO_CART(HttpStatus.BAD_REQUEST, "Product not added to cart"),
    CART_NOT_FOUND(HttpStatus.NOT_FOUND, "Cart not found"),
    AN_ERROR_CREATED_CART(HttpStatus.INTERNAL_SERVER_ERROR, "An error while created cart"),
    CONSTRAINS_VIOLATION(HttpStatus.CONFLICT, "Duplicate entry");
    ;
    CartError(HttpStatus httpStatus, String message){
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
