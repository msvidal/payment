package com.nubank.payment.core.account;

import com.nubank.payment.core.ValidationSingleton;
import com.nubank.payment.entrypoint.port.PortFactory;

public class AccountNotInitializedValidation {

    private final AccountPort accountPort;

    public AccountNotInitializedValidation() {
        this.accountPort = new PortFactory().createAccountPort();
    }

    public void validate() {
        if(!accountPort.checkIfAccountAlreadyExists()){
            ValidationSingleton.getInstance().addValidation("account-not-initialized");
        }
    }
}