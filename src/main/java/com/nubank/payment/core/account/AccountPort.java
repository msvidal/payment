package com.nubank.payment.core.account;

public interface AccountPort {

    Account save(Account account);

    boolean checkIfAccountAlreadyExists();

    Account find();

}
