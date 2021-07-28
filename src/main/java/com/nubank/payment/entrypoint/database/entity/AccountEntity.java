package com.nubank.payment.entrypoint.database.entity;

import com.nubank.payment.core.account.Account;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "account")
public class AccountEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Boolean activeCard;
    private Integer availableLimit;

    public static AccountEntity from(Account account) {
        return AccountEntity.builder()
            .id(account.getId())
            .activeCard(account.getActiveCard())
            .availableLimit(account.getAvailableLimit())
            .build();
    }

    public static Account toDomain(AccountEntity accountEntity) {
        return Account.builder()
            .id(accountEntity.getId())
            .activeCard(accountEntity.getActiveCard())
            .availableLimit(accountEntity.getAvailableLimit())
            .build();
    }
}
