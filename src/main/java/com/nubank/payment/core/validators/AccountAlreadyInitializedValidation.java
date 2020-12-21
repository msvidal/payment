package com.nubank.payment.core.validators;

import com.nubank.payment.core.exception.ValidationException;
import com.nubank.payment.core.ports.AccountPort;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AccountAlreadyInitializedValidation {

    private final AccountPort port;

    public void validate() {
        if(port.checkIfAccountAlreadyExists()){
            throw new ValidationException("account-already-initialized");
        }
    }

}
