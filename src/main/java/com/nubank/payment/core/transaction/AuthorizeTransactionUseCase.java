package com.nubank.payment.core.transaction;

import com.nubank.payment.core.ValidationFactory;
import com.nubank.payment.core.account.Account;
import com.nubank.payment.core.account.AccountPort;
import com.nubank.payment.core.account.AccountNotInitializedValidation;

public class AuthorizeTransactionUseCase {

    private final TransactionPort transactionPort;

    private final AccountPort accountPort;

    private final AccountNotInitializedValidation accountNotInitializedValidation;

    private final CardNotActiveValidation cardNotActiveValidation;

    private final InsufficienteLimitValidation insufficienteLimitValidation;

    private final HighFrequencyValidation highFrequencyValidation;

    private final DoubleTransactionValidation doubleTransactionValidation;

    public AuthorizeTransactionUseCase(final TransactionPort transactionPort, final AccountPort accountPort,
        final AccountNotInitializedValidation accountNotInitializedValidation,
        final CardNotActiveValidation cardNotActiveValidation,
        final InsufficienteLimitValidation insufficienteLimitValidation,
        final HighFrequencyValidation highFrequencyValidation,
        final DoubleTransactionValidation doubleTransactionValidation) {
        this.transactionPort = transactionPort;
        this.accountPort = accountPort;
        this.accountNotInitializedValidation = accountNotInitializedValidation;
        this.cardNotActiveValidation = cardNotActiveValidation;
        this.insufficienteLimitValidation = insufficienteLimitValidation;
        this.highFrequencyValidation = highFrequencyValidation;
        this.doubleTransactionValidation = doubleTransactionValidation;
    }

    public Account execute(Transaction transaction) {

        accountNotInitializedValidation.validate();

        var account = accountPort.find();

        cardNotActiveValidation.validate(account);

        insufficienteLimitValidation.validate(account,transaction);

        highFrequencyValidation.validate(transaction);

        doubleTransactionValidation.validate(transaction);

        if(account != null && ValidationFactory.getInstance().getValidations().size() == 0) {

            account.setAvailableLimit(account.getAvailableLimit() - transaction.getAmount());
            account = accountPort.save(account);
            transactionPort.authorize(transaction);
            return account;
        }

        return account;
    }
}
