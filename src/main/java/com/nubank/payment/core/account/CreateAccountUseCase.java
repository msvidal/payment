package com.nubank.payment.core.account;

import com.nubank.payment.core.ValidationFactory;

public class CreateAccountUseCase {

    private final AccountPort accountPort;

    private final AccountAlreadyInitializedValidation accountAlreadyInitializedValidation;

    public CreateAccountUseCase(final AccountPort accountPort,
        final AccountAlreadyInitializedValidation accountAlreadyInitializedValidation) {
        this.accountPort = accountPort;
        this.accountAlreadyInitializedValidation = accountAlreadyInitializedValidation;
    }

    public Account execute(Account account) {

        var accountFind = accountPort.find();

        accountAlreadyInitializedValidation.validate(account);

        if(!ValidationFactory.getInstance().getValidations().isEmpty()){
            return accountFind;
        }

        return accountPort.save(account);
    }
}
