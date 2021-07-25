package com.nubank.payment;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nubank.payment.core.domain.Account;
import com.nubank.payment.core.usecase.CreateAccountUseCase;
import com.nubank.payment.entrypoint.dto.AccountRequest;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;


@SpringBootApplication(scanBasePackages = "com.nubank.payment.*")
@ShellComponent
public class PaymentShell {

    private final CreateAccountUseCase createAccountUseCase;

    public PaymentShell(final CreateAccountUseCase createAccountUseCase) {
        this.createAccountUseCase = createAccountUseCase;
    }

    public static void main(String[] args) throws Exception {
        ConfigurableApplicationContext context = SpringApplication.run(PaymentShell.class, args);
    }

    @ShellMethod("Add account.")
    public String add(String request) {

        AccountRequest accountRequest = AccountRequest.toObject(request);

        try {

            return createAccountUseCase.execute(AccountRequest.toDomain(accountRequest)).toString();

        } catch (RuntimeException exception) {
            return exception.toString();
        }
    }
}
