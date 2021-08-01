package com.nubank.payment.core;

import java.util.ArrayList;
import java.util.List;


public class ValidationSingleton
{
    private static ValidationSingleton instance = null;
    private static List<String> violations = new ArrayList<>();

    private ValidationSingleton() {
    }

    public static ValidationSingleton getInstance()
    {
        if (instance == null)
            instance = new ValidationSingleton();
        return instance;
    }

    public void addValidation(String validation){
        violations.add(validation);
    }

    public List<String> getValidations(){
        return violations;
    }

    public void invalidate(){
        violations = new ArrayList<>();
    }
}
