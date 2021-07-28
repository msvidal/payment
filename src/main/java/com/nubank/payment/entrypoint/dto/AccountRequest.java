package com.nubank.payment.entrypoint.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AccountRequest {

    @JsonProperty("account")
    private AccountData accountData;

    @Override
    public String toString() {
        return "AccountRequest{" +
            "accountData=" + accountData +
            '}';
    }

}
