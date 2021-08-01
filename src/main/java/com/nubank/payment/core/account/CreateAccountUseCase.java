package com.nubank.payment.core.account;

import com.nubank.payment.core.PaymentUseCase;
import com.nubank.payment.entrypoint.port.PortFactory;

public class CreateAccountUseCase extends PaymentUseCase {

    private final AccountPort accountPort;

    public CreateAccountUseCase(final AccountPort accountPort) {
        this.accountPort = new PortFactory().createAccountPort();
    }

    public Account execute(Account account) {

        new AccountAlreadyInitializedValidation().validate();

        if (!isEmptyValidations()) {
            account.setValidations(getValidations());
            return account;
        }

        return accountPort.save(account);
    }
}
