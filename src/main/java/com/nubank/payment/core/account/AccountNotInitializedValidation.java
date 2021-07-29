package com.nubank.payment.core.account;

import com.nubank.payment.core.ValidationFactory;
import com.nubank.payment.entrypoint.port.AccountPortImpl;

public class AccountNotInitializedValidation {

    private final AccountPort accountPort;

    public AccountNotInitializedValidation() {
        this.accountPort = new AccountPortImpl();
    }

    public void validate() {
        if(!accountPort.checkIfAccountAlreadyExists()){
            ValidationFactory.getInstance().addValidation("account-not-initialized");
        }
    }
}