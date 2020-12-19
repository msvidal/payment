package com.nubank.payment.core.ports;

import com.nubank.payment.core.domain.Account;

public interface AccountPort {

    Account save(Account account);

    boolean checkIfAccountAlreadyExists();

    Account find(Integer id);

}
