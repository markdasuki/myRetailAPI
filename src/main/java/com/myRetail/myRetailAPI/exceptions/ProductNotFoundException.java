package com.myRetail.myRetailAPI.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/*
* Exception when product is not found in data store or api
*
* */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class ProductNotFoundException extends RuntimeException {

    public ProductNotFoundException(String errorMessage)
    {
        super(errorMessage);
    }
}
