package com.nubank.payment.entrypoint.rest;

import com.nubank.payment.core.exception.ValidationException;
import com.nubank.payment.core.usecase.CreateTransactionUseCase;
import com.nubank.payment.entrypoint.dto.TransactionRequest;
import com.nubank.payment.entrypoint.dto.TransactionResponse;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping(value = "v1/transaction", produces = "application/json")
@AllArgsConstructor
public class TransactionController {

    private final CreateTransactionUseCase useCase;

    private final Integer ACCOUNT_ID = 1;

    @PostMapping
    @ResponseStatus(CREATED)
    public TransactionResponse create(@RequestBody TransactionRequest requestDTO) {
        try {

            useCase.execute(TransactionRequest.toDomain(requestDTO),ACCOUNT_ID);

        } catch(ValidationException ex) {
            return TransactionResponse.toRequest(requestDTO, ex.getValidationExceptions());
        }

        return TransactionResponse.toRequest(requestDTO, null);
    }
}
