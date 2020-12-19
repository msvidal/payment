package com.nubank.payment.core.usecase;

import com.nubank.payment.core.exception.ValidationException;
import com.nubank.payment.core.ports.AccountPort;
import com.nubank.payment.core.domain.Account;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CreateAccountUseCase {

    private final AccountPort port;

    public Account execute(final Account account) {

        if(port.checkIfAccountAlreadyExists()){
            throw new ValidationException("account-already-initialized");
        }

        return port.save(account);
    }

}
