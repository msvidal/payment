package com.nubank.payment.entrypoint.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
public class TransactionData {

    private String merchant;
    private Integer amount;
    private LocalDateTime time;

}
