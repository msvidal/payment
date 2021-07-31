package com.nubank.payment.entrypoint.database.entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AccountEntity {

    private Integer id;
    private Boolean activeCard;
    private Integer availableLimit;

}
