package com.nubank.payment.core.transaction;

import com.nubank.payment.core.ValidationFactory;
import com.nubank.payment.core.account.Account;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class InsufficienteLimitValidation {

    public void validate(Account account, Transaction transaction) {
        if (account == null) return;

        if(transaction.getAmount() > account.getAvailableLimit()) {
            ValidationFactory.getInstance().addValidation("insufficient-limit");
        }
    }
}
