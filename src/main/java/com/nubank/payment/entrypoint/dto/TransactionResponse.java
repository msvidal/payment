package com.nubank.payment.entrypoint.dto;

import com.nubank.payment.core.domain.Transaction;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

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

    public static TransactionResponse toRequest(TransactionRequest transactionRequest, String message) {
        return TransactionResponse.builder()
            .merchant(transactionRequest.getMerchant())
            .amount(transactionRequest.getAmount())
            .time(transactionRequest.getTime())
            .violations(toValidations(message))
            .build();
    }

    private static List<String> toValidations (String message) {
        if(message == null) {
            return Collections.EMPTY_LIST;
        }

        return Arrays.asList(message);
    }
}
