package com.nubank.payment.core.account;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CreateAccountUseCase {

    private final AccountPort port;

    private final AccountAlreadyInitializedValidation accountAlreadyInitialized;

    public Account execute(final Account account) {

        accountAlreadyInitialized.validate();

        return port.save(account);
    }

}
