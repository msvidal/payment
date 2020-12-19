package com.nubank.payment.core.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@Builder
public class Account {

    private Integer id;
    private Boolean activeCard;
    private Integer availableLimit;
}
