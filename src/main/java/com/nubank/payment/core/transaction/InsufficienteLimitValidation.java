package com.nubank.payment.core.transaction;

import com.nubank.payment.core.account.Account;
import com.nubank.payment.core.ValidationException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class InsufficienteLimitValidation {

    public void validate(Account account, Transaction transaction) {
        if (account == null) return;

        if(transaction.getAmount() > account.getAvailableLimit()) {
            throw new ValidationException(account,"insufficient-limit");
        }
    }
}
