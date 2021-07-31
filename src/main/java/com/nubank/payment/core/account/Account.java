package com.nubank.payment.core.account;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Account {

    private Integer id;
    private Boolean activeCard;
    private Integer availableLimit;

    public void setAvailableLimit(final int availableLimit) {
        this.availableLimit = availableLimit;
    }

}
