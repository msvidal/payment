package com.nubank.payment.entrypoint.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.nubank.payment.core.account.Account;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@AllArgsConstructor
@Getter
@Builder
public class AccountResponse {

    @JsonProperty("active-card")
    private Boolean activeCard;
    @JsonProperty("available-limit")
    private Integer availableLimit;
    @JsonProperty("violations")
    private List<String> violations = Collections.emptyList();

    public static Account toDomain(AccountResponse accountDto) {
        return Account.builder()
            .activeCard(accountDto.getActiveCard())
            .availableLimit(accountDto.getAvailableLimit())
            .build();
    }

    public static AccountResponse toRequest(AccountData accountDto, String message) {
        return AccountResponse.builder()
            .activeCard(accountDto.getActiveCard())
            .availableLimit(accountDto.getAvailableLimit())
            .violations(toViolations(message))
            .build();
    }

    private static List<String> toViolations(String message) {
        if(message == null)
            return Collections.emptyList();

        return Arrays.asList(message);
    }
}
