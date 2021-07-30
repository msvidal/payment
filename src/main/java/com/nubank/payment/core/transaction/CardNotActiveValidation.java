package com.nubank.payment.core.transaction;

import com.nubank.payment.core.ValidationFactory;
import com.nubank.payment.core.account.Account;
import lombok.AllArgsConstructor;

public class CardNotActiveValidation {

    public void validate(Account account) {
        if (account != null && Boolean.FALSE.equals(account.getActiveCard())){
            ValidationFactory.getInstance().addValidation("card-not-active");
        }
    }
}
