package com.nubank.payment.core.transaction;

import com.nubank.payment.core.ValidationFactory;
import com.nubank.payment.core.account.Account;
import com.nubank.payment.core.account.AccountPort;
import com.nubank.payment.core.account.AccountNotInitializedValidation;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CreateTransactionUseCase {

    private final TransactionPort transactionPort;

    private final AccountPort accountPort;

    private final AccountNotInitializedValidation accountNotInitializedValidation;

    private final CardNotActiveValidation cardNotActiveValidation;

    private final InsufficienteLimitValidation insufficienteLimitValidation;

    private final HighFrequencyValidation highFrequencyValidation;

    private final DoubleTransactionValidation doubleTransactionValidation;

    public Account execute(Transaction transaction) {

        accountNotInitializedValidation.validate();

        var account = accountPort.find();

        cardNotActiveValidation.validate(account);

        insufficienteLimitValidation.validate(account,transaction);

        highFrequencyValidation.validate(transaction);

        doubleTransactionValidation.validate(transaction);

        if(ValidationFactory.getInstance().getValidations().size() == 0) {

            if (account != null) {
                account.setAvailableLimit(account.getAvailableLimit() - transaction.getAmount());
                account = accountPort.save(account);
            }

            transactionPort.authorize(transaction);
        }

        return account;
    }
}
