package com.nubank.payment.entrypoint.config;

import com.nubank.payment.core.ports.AccountPort;
import com.nubank.payment.core.usecase.CreateAccountUseCase;
import com.nubank.payment.core.validators.AccountAlreadyInitializedValidation;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class UseCaseConfig {

    private final AccountPort accountPort;
    private final AccountAlreadyInitializedValidation accountAlreadyInitialized;

    CreateAccountUseCase createAccountUseCase(){
        return new CreateAccountUseCase(accountPort,accountAlreadyInitialized);
    }
}
