package com.nubank.payment.core;

import java.util.List;

public class PaymentUseCase {

    protected boolean isEmptyValidations(){
        return ValidationSingleton.getInstance().getValidations().isEmpty();
    }

    protected List<String> getValidations(){
        return ValidationSingleton.getInstance().getValidations();
    }
}
