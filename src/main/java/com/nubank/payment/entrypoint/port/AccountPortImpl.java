package com.nubank.payment.entrypoint.port;

import com.nubank.payment.core.account.Account;
import com.nubank.payment.core.account.AccountPort;
import com.nubank.payment.entrypoint.database.repository.AccountRepository;
import com.nubank.payment.entrypoint.database.entity.AccountEntity;
import com.nubank.payment.entrypoint.database.repository.AccountRepositoryImpl;

public class AccountPortImpl implements AccountPort {

    public static final Integer ID_ACCOUNT = 1;
    private AccountRepository repository;

    public AccountPortImpl() {
        this.repository = AccountRepositoryImpl.getInstance();
    }

    @Override
    public Account save(final Account account) {

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

        if(accountEntity != null){
            return Account.builder()
                .id(accountEntity.getId())
                .activeCard(accountEntity.getActiveCard())
                .availableLimit(accountEntity.getAvailableLimit())
                .build();
        }

        return null;
    }
}
