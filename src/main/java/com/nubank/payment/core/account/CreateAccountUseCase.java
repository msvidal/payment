package com.nubank.payment.core.account;

import com.nubank.payment.core.ValidationFactory;
import com.nubank.payment.entrypoint.port.AccountPortImpl;

public class CreateAccountUseCase {

    private final AccountPort accountPort;

    public CreateAccountUseCase() {
        this.accountPort = new AccountPortImpl();
    }

    public Account execute(Account account) {

        var accountExist = accountPort.find();

        if (accountExist != null) {
            ValidationFactory.getInstance().addValidation("account-already-initialized");
            return accountExist;
        }

        return accountPort.save(account);
    }
}
