package com.myRetail.myRetailAPI.exceptions;

public class ExceptionResponse {
    private int status;
    private String message;

    public ExceptionResponse(int status, String message)
    {
        this.status = status;
        this.message = message;
    }

    public String getMessage()
    {
        return message;
    }

    public int getStatus()
    {
        return status;
    }
}
