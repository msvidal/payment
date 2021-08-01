package com.nubank.payment.entrypoint.port;

import com.nubank.payment.core.account.AccountPort;
import com.nubank.payment.core.transaction.TransactionPort;

public class PortFactory {

    public AccountPort createAccountPort(){
        return new AccountPortImpl();
    }

    public TransactionPort createTransactionPort(){
        return new TransactionPortImpl();
    }
}
