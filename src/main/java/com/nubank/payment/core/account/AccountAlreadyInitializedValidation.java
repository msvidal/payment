package com.nubank.payment.core.account;

import com.nubank.payment.core.ValidationException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AccountAlreadyInitializedValidation {

    private final AccountPort port;

    public void validate(Account account) {
        if(port.checkIfAccountAlreadyExists()){
            throw new ValidationException(account,"account-already-initialized");
        }
    }

}
