package com.nubank.payment.core.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
@Setter
@Builder
public class Transaction {

    private String merchant;
    private Integer amount;
    private LocalDateTime time;

    private boolean validateFrequency(Integer maxInterval){
        return LocalDateTime.now().isAfter(time) && time.isBefore(LocalDateTime.now().plusMinutes(maxInterval));
    }

    public boolean validateHighFrequency(Integer maxInterval){
        return validateFrequency(maxInterval);
    }

    public boolean validateDoubleTransaction(String merchant, Integer amount, Integer maxInterval){
        return (this.merchant.equalsIgnoreCase(merchant) && this.amount.equals(amount))
                    && validateFrequency(maxInterval);
    }
}
