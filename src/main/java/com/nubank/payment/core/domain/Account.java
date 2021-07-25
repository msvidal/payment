package com.nubank.payment.core.domain;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;
import lombok.ToString;

@AllArgsConstructor
@Getter
@Setter
@Builder
public class Account {

    private Integer id;
    private Boolean activeCard;
    private Integer availableLimit;

    public Account(){

    }

    @SneakyThrows
    @Override
    public String toString() {
        return new ObjectMapper().writeValueAsString(this);
    }

}
