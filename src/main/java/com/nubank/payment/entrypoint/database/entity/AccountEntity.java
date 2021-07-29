package com.nubank.payment.entrypoint.database.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AccountEntity {

    private Integer id;
    private Boolean activeCard;
    private Integer availableLimit;

}
