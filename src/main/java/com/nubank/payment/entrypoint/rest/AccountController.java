package com.nubank.payment.entrypoint.rest;

import com.nubank.payment.core.exception.ValidationException;
import com.nubank.payment.core.usecase.CreateAccountUseCase;
import com.nubank.payment.entrypoint.dto.AccountRequest;
import com.nubank.payment.entrypoint.dto.AccountResponse;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping(value = "v1/account", produces = "application/json")
@AllArgsConstructor
public class AccountController {

    private final CreateAccountUseCase useCase;

    @PostMapping
    @ResponseStatus(CREATED)
    public AccountResponse create(@RequestBody AccountRequest requestDTO) {
        try {

            useCase.execute(AccountRequest.toDomain(requestDTO));

        } catch(ValidationException ex) {
            return AccountResponse.toRequest(requestDTO, ex.getMessage());
        }

        return AccountResponse.toRequest(requestDTO, null);
    }
}
