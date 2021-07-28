package com.nubank.payment.entrypoint.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
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
public class TransactionRequest {


    @JsonProperty("transaction")
    private TransactionData transactionData;

    @Override
    public String toString() {
        return "TransactionRequest{" +
            "transactionData=" + transactionData +
            '}';
    }

}
