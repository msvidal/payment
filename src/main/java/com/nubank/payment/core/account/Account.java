package com.nubank.payment.core.account;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class Account {

    private Integer id;
    @JsonProperty("active-card")
    private Boolean activeCard;
    private Integer availableLimit;

    public void setAvailableLimit(final int availableLimit) {
        this.availableLimit = availableLimit;
    }

}
