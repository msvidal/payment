package com.nubank.payment.core.transaction;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class Transaction {

    private String merchant;
    private Integer amount;
    private LocalDateTime time;

}
