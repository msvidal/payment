package com.nubank.payment.entrypoint.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.nubank.payment.core.domain.Account;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AccountRequest {

    @JsonProperty("active-card")
    private Boolean activeCard;
    @JsonProperty("available-limit")
    private Integer availableLimit;

    public static Account toDomain(AccountRequest accountDto) {
        return Account.builder()
            .activeCard(accountDto.getActiveCard())
            .availableLimit(accountDto.getAvailableLimit())
            .build();
    }
}
