package com.nubank.payment.entrypoint.database.repository;

import com.nubank.payment.entrypoint.database.entity.AccountEntity;

public interface AccountRepository {

    AccountEntity save(AccountEntity accountEntity);

    int count();

    AccountEntity findById(Integer id);

}
