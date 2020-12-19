package com.nubank.payment.core.exception;

import java.util.List;

public class ValidationException  extends RuntimeException {

    private List<ValidationException> validationExceptions;

    public ValidationException(String message){
        super(message);
    }

    public ValidationException(List<ValidationException> validationExceptions){
        this.validationExceptions = validationExceptions;
    }

    public List<ValidationException> getValidationExceptions(){
        return this.validationExceptions;
    }
}
