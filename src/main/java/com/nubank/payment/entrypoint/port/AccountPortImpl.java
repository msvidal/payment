package com.nubank.payment.entrypoint.port;

import com.nubank.payment.core.account.Account;
import com.nubank.payment.core.account.AccountPort;
import com.nubank.payment.entrypoint.database.DatabaseFactory;
import com.nubank.payment.entrypoint.database.repository.AccountRepository;
import com.nubank.payment.entrypoint.database.entity.AccountEntity;

import java.util.Optional;

public class AccountPortImpl implements AccountPort {

    private AccountRepository repository;

    public AccountPortImpl() {
        this.repository = new DatabaseFactory().createAccountRepository();
    }

    @Override
    public Account save(Account account) {

        var accountEntity = AccountEntity.builder()
            .id(account.getId())
            .activeCard(account.getActiveCard())
            .availableLimit(account.getAvailableLimit())
            .build();

        accountEntity = repository.save(accountEntity);

        return Account.builder()
            .id(accountEntity.getId())
            .activeCard(accountEntity.getActiveCard())
            .availableLimit(accountEntity.getAvailableLimit())
            .build();
    }

    @Override
    public boolean checkIfAccountAlreadyExists() {
        return repository.count() > 0;
    }

    @Override
    public Account find() {

        var accountEntity = repository.findById(ID_ACCOUNT);

        return Optional.ofNullable(accountEntity).map(accountEntity1 -> {
            return Account.builder()
                .id(accountEntity.getId())
                .activeCard(accountEntity.getActiveCard())
                .availableLimit(accountEntity.getAvailableLimit())
                .build();
        }).orElse(null);
    }
}
