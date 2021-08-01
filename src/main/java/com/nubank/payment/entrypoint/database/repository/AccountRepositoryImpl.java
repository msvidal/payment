package com.nubank.payment.entrypoint.database.repository;

import com.nubank.payment.core.account.AccountPort;
import com.nubank.payment.entrypoint.database.entity.AccountEntity;

import java.util.HashMap;
import java.util.Map;

public class AccountRepositoryImpl implements AccountRepository {

    private static AccountRepositoryImpl instance = null;

    private static Map<Integer,AccountEntity> repository = new HashMap<>();

    private AccountRepositoryImpl() {
    }

    public static AccountRepository getInstance()
    {
        if (instance == null)
            instance = new AccountRepositoryImpl();
        return instance;
    }

    @Override
    public AccountEntity save(AccountEntity accountEntity) {
        accountEntity.setId(AccountPort.ID_ACCOUNT);
        repository.put(accountEntity.getId(),accountEntity);
        return accountEntity;
    }

    @Override
    public int count() {
        return repository.size();
    }

    @Override
    public AccountEntity findById(Integer id) {
        return repository.get(id);
    }

}
