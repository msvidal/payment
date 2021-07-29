package com.nubank.payment.core.account;

import com.nubank.payment.core.ValidationFactory;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CreateAccountUseCase {

    private final AccountPort port;

    public Account execute(Account account) {

        var accountExist = port.find();

        if (accountExist != null) {
            ValidationFactory.getInstance().addValidation("account-already-initialized");
            return accountExist;
        }

        return port.save(account);
    }
}
