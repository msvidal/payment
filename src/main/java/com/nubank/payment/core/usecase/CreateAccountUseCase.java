package com.nubank.payment.core.usecase;

import com.nubank.payment.core.ports.AccountPort;
import com.nubank.payment.core.domain.Account;
import com.nubank.payment.core.validators.AccountAlreadyInitializedValidation;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CreateAccountUseCase {

    private final AccountPort accountPort;
    private final AccountAlreadyInitializedValidation accountAlreadyInitialized;

    public Account execute(final Account account) {

        accountAlreadyInitialized.validate();

        return accountPort.save(account);
    }

}
