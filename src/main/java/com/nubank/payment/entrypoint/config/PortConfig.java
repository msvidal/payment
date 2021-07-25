package com.nubank.payment.entrypoint.config;

import com.nubank.payment.core.ports.AccountPort;
import com.nubank.payment.core.usecase.CreateAccountUseCase;
import com.nubank.payment.core.validators.AccountAlreadyInitializedValidation;
import com.nubank.payment.entrypoint.database.AccountRepository;
import com.nubank.payment.entrypoint.port.AccountPortImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class PortConfig {

    private final AccountRepository accountRepository;

    AccountPort accountPort(){
        return new AccountPortImpl(accountRepository);
    }
}
