package com.nubank.payment.core.account;

import com.nubank.payment.core.PaymentUseCase;
import com.nubank.payment.core.ValidationSingleton;
import com.nubank.payment.entrypoint.port.PortFactory;

public class CreateAccountUseCase extends PaymentUseCase {

    private final AccountPort accountPort;

    public CreateAccountUseCase(final AccountPort accountPort) {
        this.accountPort = new PortFactory().createAccountPort();
    }

    public Account execute(Account account) {

        var accountFind = accountPort.find();

        if (accountFind != null) {
            ValidationSingleton.getInstance().addValidation("account-already-initialized");
            accountFind.setValidations(getValidations());
            return accountFind;
        }

        return accountPort.save(account);
    }
}
