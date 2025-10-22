package com.ecomerce.ecomerce.enums;

import org.springframework.http.HttpStatus;

public enum ProductFamilyError implements IError {
    PRODUCT_FAMILY_NOT_FOUND(HttpStatus.NOT_FOUND, "Product family not found"),
    ALREDY_EXIST_A_PRODUCT_FAMILY_WITH_THE_SAME_NAME(HttpStatus.CONFLICT, "Alredy exist a family product with the same name"),
    ERROR_CREATING_PRODUCT_FAMILY(HttpStatus.INTERNAL_SERVER_ERROR, "an error ocurred while creating product"),
    ERROR_UPDATING_PRODUCT_FAMILY(HttpStatus.INTERNAL_SERVER_ERROR, "an error ocurred while updating product"),
    ERROR_DELETING_PRODUCT_FAMILY(HttpStatus.INTERNAL_SERVER_ERROR, "an error ocurred while deleting product"),
    CONSTRAINS_VIOLATION(HttpStatus.CONFLICT, "Duplicate entry");
    ;
    ProductFamilyError(HttpStatus httpStatus, String message) {
        this.message = message;
        this.httpStatus = httpStatus;
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
