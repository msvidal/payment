package com.nubank.payment.entrypoint.database.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class TransactionEntity {

    private Integer Id;
    private String merchant;
    private Integer amount;
    private LocalDateTime time;

}
