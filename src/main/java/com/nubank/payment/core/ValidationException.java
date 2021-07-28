package com.nubank.payment.core;

public class ValidationException  extends RuntimeException {

    public ValidationException(String message){
        super(message);
    }
}
