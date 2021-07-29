package com.nubank.payment.entrypoint.cli.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class TransactionRequest {

    @JsonProperty("transaction")
    private TransactionData transactionData;

}
