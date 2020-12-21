package com.nubank.payment.core.validators;

import com.nubank.payment.core.domain.Account;
import com.nubank.payment.core.domain.Transaction;
import com.nubank.payment.core.exception.ValidationException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class InsufficienteLimitValidation {

    public void validate(Account account, Transaction transaction) {
        if (account == null) return;

        if(transaction.getAmount() > account.getAvailableLimit()) {
            throw new ValidationException("insufficient-limit");
        }
    }
}
