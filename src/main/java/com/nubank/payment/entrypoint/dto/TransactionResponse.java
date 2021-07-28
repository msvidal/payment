package com.nubank.payment.entrypoint.dto;

import com.nubank.payment.core.transaction.Transaction;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@Getter
@Builder
public class TransactionResponse extends PaymentResponse {

    private String merchant;
    private Integer amount;
    private LocalDateTime time;
    private List<String> violations;

    public static void toJsonFormat(Transaction transaction) {
        parseJson(TransactionResponse.builder()
            .merchant(transaction.getMerchant())
            .amount(transaction.getAmount())
            .time(transaction.getTime())
            .violations(toValidations(null))
            .build());
    }

    public static void toJsonFormat(Transaction transaction, String message) {
         parseJson(TransactionResponse.builder()
            .merchant(transaction.getMerchant())
            .amount(transaction.getAmount())
            .time(transaction.getTime())
            .violations(toValidations(message))
            .build());
    }
}
