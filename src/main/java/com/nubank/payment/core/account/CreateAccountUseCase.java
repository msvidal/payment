package com.nubank.payment.core.account;

import com.nubank.payment.core.ValidationFactory;

public class CreateAccountUseCase {

    private final AccountPort accountPort;

    public CreateAccountUseCase(final AccountPort accountPort) {
        this.accountPort = accountPort;
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
