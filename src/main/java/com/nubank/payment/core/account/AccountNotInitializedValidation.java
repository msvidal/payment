package com.nubank.payment.core.account;

import com.nubank.payment.core.ValidationException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AccountNotInitializedValidation {

    private final AccountPort port;

    public void validate() {
        if(!port.checkIfAccountAlreadyExists()){
            throw new ValidationException(null,"account-not-initialized");
        }
    }
}
