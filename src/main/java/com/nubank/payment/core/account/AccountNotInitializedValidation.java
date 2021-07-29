package com.nubank.payment.core.account;

import com.nubank.payment.core.ValidationFactory;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AccountNotInitializedValidation {

    private final AccountPort port;

    public void validate() {
        if(!port.checkIfAccountAlreadyExists()){
            ValidationFactory.getInstance().addValidation("account-not-initialized");
        }
    }
}