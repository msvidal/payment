package com.nubank.payment.entrypoint.dto;

import com.nubank.payment.core.transaction.Transaction;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class TransactionData {

    private String merchant;
    private Integer amount;
    private LocalDateTime time;

    public static Transaction toDomain(TransactionData transactionRequest) {
        return Transaction.builder()
            .amount(transactionRequest.getAmount())
            .merchant(transactionRequest.getMerchant())
            .time(transactionRequest.getTime())
            .build();
    }
}
