package com.nubank.payment.entrypoint.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.nubank.payment.core.ValidationFactory;
import com.nubank.payment.core.account.Account;
import com.nubank.payment.entrypoint.Utils;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
@Builder
public class AccountResponse {

    @JsonProperty("account")
    private AccountResponseData data;

    @JsonProperty("violations")
    private List<String> violations;

    public static void parseJson(Account account) {
        try {

            var accountResponse = AccountResponse.builder()
                .data(account != null ? AccountResponseData.builder()
                    .activeCard(account.getActiveCard())
                    .availableLimit(account.getAvailableLimit())
                    .build() : null)
                .violations(ValidationFactory.getInstance().getValidations()).build();

            ValidationFactory.getInstance().invalidate();

            System.out.println(Utils.getObjectMapper().writeValueAsString(accountResponse));

        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

}
