package com.nubank.payment.entrypoint.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.nubank.payment.core.account.Account;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AccountData {

    @JsonProperty("active-card")
    private Boolean activeCard;
    @JsonProperty("available-limit")
    private Integer availableLimit;

    public static Account toDomain(AccountData accountDto) {
        return Account.builder()
            .activeCard(accountDto.getActiveCard())
            .availableLimit(accountDto.getAvailableLimit())
            .build();
    }
}
