package com.nubank.payment.entrypoint.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.*;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.nubank.payment.core.domain.Account;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Getter
@Builder
public class AccountResponse {

    @JsonProperty("active-card")
    private Boolean activeCard;
    @JsonProperty("available-limit")
    private Integer availableLimit;
    @JsonProperty("violations")
    private List<String> violations = Collections.EMPTY_LIST;

    public static Account toDomain(AccountResponse accountDto) {
        return Account.builder()
            .activeCard(accountDto.getActiveCard())
            .availableLimit(accountDto.getAvailableLimit())
            .build();
    }

    public static AccountResponse toRequest(AccountRequest accountDto, String message) {
        return AccountResponse.builder()
            .activeCard(accountDto.getActiveCard())
            .availableLimit(accountDto.getAvailableLimit())
            .violations(toViolations(message))
            .build();
    }

    private static List<String> toViolations(String message) {
        if(message == null)
            return Collections.EMPTY_LIST;

        return Arrays.asList(message);
    }
}
