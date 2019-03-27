package com.myRetail.myRetailAPI.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/*
* Exception when JSON request for a put request is invalid
*
* */
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class BadJSONRequestException extends RuntimeException {
    public BadJSONRequestException(String errorMessage)
    {
        super(errorMessage);
    }
}
