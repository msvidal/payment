package com.nubank.payment.core.account;

import com.nubank.payment.core.ValidationFactory;

public class AccountAlreadyInitializedValidation {

    public AccountAlreadyInitializedValidation() {
    }

    public void validate(Account account) {
        if (account != null) {
            ValidationFactory.getInstance().addValidation("account-already-initialized");
        }
    }
}