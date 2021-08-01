package com.nubank.payment.core.transaction;

import com.nubank.payment.core.ValidationSingleton;
import com.nubank.payment.core.account.Account;

public class CardNotActiveValidation {

    public void validate(Account account) {
        if (account != null && Boolean.FALSE.equals(account.getActiveCard())){
            ValidationSingleton.getInstance().addValidation("card-not-active");
        }
    }
}
