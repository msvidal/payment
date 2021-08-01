package com.nubank.payment.core.transaction;

import com.nubank.payment.core.ValidationSingleton;
import com.nubank.payment.core.account.Account;

public class InsufficienteLimitValidation {

    public void validate(Account account, Transaction transaction) {
        if (account == null) return;

        if(transaction.getAmount() > account.getAvailableLimit()) {
            ValidationSingleton.getInstance().addValidation("insufficient-limit");
        }
    }
}
