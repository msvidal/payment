package com.nubank.payment.core.transaction;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class Transaction {

    private String merchant;
    private Integer amount;
    private LocalDateTime time;

}
