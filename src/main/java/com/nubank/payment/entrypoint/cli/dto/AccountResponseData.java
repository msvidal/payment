package com.nubank.payment.entrypoint.cli.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor
@Getter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AccountResponseData {

    @JsonProperty("active-card")
    private Boolean activeCard;
    @JsonProperty("available-limit")
    private Integer availableLimit;

}
