package com.nubank.payment.core.validators;

import com.nubank.payment.core.domain.Account;
import com.nubank.payment.core.exception.ValidationException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CardNotActiveValidation {

    public void validate(Account account) {
        if (account == null) return;

        if (Boolean.FALSE.equals(account.getActiveCard())){
            throw new ValidationException("card-not-active");
        }
    }
}
