package com.nubank.payment.entrypoint.database;

import com.nubank.payment.entrypoint.database.repository.AccountRepository;
import com.nubank.payment.entrypoint.database.repository.AccountRepositoryImpl;
import com.nubank.payment.entrypoint.database.repository.TransactionRepository;
import com.nubank.payment.entrypoint.database.repository.TransactionRepositoryImpl;

public class DatabaseFactory {

    public AccountRepository createAccountRepository(){
        return AccountRepositoryImpl.getInstance();
    }

    public TransactionRepository createTransactionRepository(){
        return TransactionRepositoryImpl.getInstance();
    }

}
