package com.nubank.payment.entrypoint.dto;

import com.nubank.payment.core.domain.Transaction;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TransactionRequest {

    private String merchant;
    private Integer amount;
    private LocalDateTime time;

    public static Transaction toDomain(TransactionRequest transactionRequest) {
        return Transaction.builder()
            .amount(transactionRequest.getAmount())
            .merchant(transactionRequest.getMerchant())
            .time(transactionRequest.getTime())
            .build();
    }
}
