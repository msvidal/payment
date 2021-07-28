package com.nubank.payment.core.transaction;

import com.nubank.payment.core.account.Account;
import com.nubank.payment.core.ValidationException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CardNotActiveValidation {

    public void validate(Account account) {
        if (Boolean.FALSE.equals(account.getActiveCard())){
            throw new ValidationException(account,"card-not-active");
        }
    }
}
