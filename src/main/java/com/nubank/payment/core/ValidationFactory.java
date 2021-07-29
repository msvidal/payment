package com.nubank.payment.core;

import java.util.ArrayList;
import java.util.List;


public class ValidationFactory
{
    private static ValidationFactory instance = null;
    private static ArrayList violations;

    private ValidationFactory() {
        violations = new ArrayList();
    }

    public static ValidationFactory getInstance()
    {
        if (instance == null)
            instance = new ValidationFactory();
        return instance;
    }

    public void addValidation(String validation){
        violations.add(validation);
    }

    public List<String> getValidations(){
        return violations;
    }

    public void invalidate(){
        violations = new ArrayList();
    }
}
