package com.nubank.payment.core.exception;

public class ValidationException  extends RuntimeException {

    public ValidationException(String message){
        super(message);
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("{\"");
        stringBuilder.append(this.getMessage());
        stringBuilder.append("\"}");
        return stringBuilder.toString();
    }

}
