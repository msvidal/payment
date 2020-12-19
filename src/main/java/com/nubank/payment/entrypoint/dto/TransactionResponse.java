package com.nubank.payment.entrypoint.dto;

import com.nubank.payment.core.domain.Transaction;
import com.nubank.payment.core.exception.ValidationException;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@AllArgsConstructor
@Getter
@Builder
public class TransactionResponse {

    private String merchant;
    private Integer amount;
    private LocalDateTime time;
    private List<String> violations;

    public static Transaction toDomain(TransactionResponse transactionResponse) {
        return Transaction.builder()
            .merchant(transactionResponse.getMerchant())
            .amount(transactionResponse.getAmount())
            .time(transactionResponse.getTime())
            .build();
    }

    public static TransactionResponse toRequest(TransactionRequest transactionRequest, List<ValidationException> validationExceptions) {
        return TransactionResponse.builder()
            .merchant(transactionRequest.getMerchant())
            .amount(transactionRequest.getAmount())
            .time(transactionRequest.getTime())
            .violations(toValidations(validationExceptions))
            .build();
    }

    private static List<String> toValidations (List<ValidationException> validationExceptions) {
        if(validationExceptions == null) {
            return Collections.EMPTY_LIST;
        }

        return validationExceptions.stream()
            .map(validationException -> validationException.getMessage()).collect(Collectors.toList());
    }
}
