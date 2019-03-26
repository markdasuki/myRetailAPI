package com.myRetail.myRetailAPI.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/*
* Exception when JSON request is malformed or improper
*
* */
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class BadJSONRequestException extends RuntimeException {

    public BadJSONRequestException(String errorMessage)
    {
        super(errorMessage);
    }

}
