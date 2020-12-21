package com.nubank.payment.core.exception;

public class ValidationException  extends RuntimeException {

    public ValidationException(String message){
        super(message);
    }
}
