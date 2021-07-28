package com.nubank.payment.entrypoint.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.nubank.payment.core.account.Account;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.Collections;
import java.util.List;

@AllArgsConstructor
@Getter
@Builder
public class AccountResponse extends PaymentResponse {

    @JsonProperty("account")
    private AccountResponseData data;

    @JsonProperty("violations")
    private List<String> violations = Collections.emptyList();

    public static void toJsonFormat(Account account) {
        parseJson(AccountResponse.builder()
            .violations(toValidations(null))
            .data(AccountResponseData.builder()
                .activeCard(account.getActiveCard())
                .availableLimit(account.getAvailableLimit())
                .build()).build());
    }

    public static void toJsonFormat(Account account, String message) {
        parseJson(AccountResponse.builder()
            .violations(toValidations(message))
            .data(account != null ? AccountResponseData.builder()
                .activeCard(account.getActiveCard())
                .availableLimit(account.getAvailableLimit())
                .build() : null).build());
    }
}
