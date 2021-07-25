package com.nubank.payment.entrypoint.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
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

    public static AccountRequest toObject(String request) {
        try {
            return new ObjectMapper().readValue(request, AccountRequest.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Account toDomain(AccountRequest accountDto) {
        return Account.builder()
            .activeCard(accountDto.getActiveCard())
            .availableLimit(accountDto.getAvailableLimit())
            .build();
    }
}
