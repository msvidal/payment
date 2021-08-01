package com.nubank.payment.core.account;

public interface AccountPort {

    Integer ID_ACCOUNT = 1;

    Account save(Account account);

    boolean checkIfAccountAlreadyExists();

    Account find();

}
