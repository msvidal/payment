package com.nubank.payment.core.transaction;

import com.nubank.payment.core.ValidationException;
import com.nubank.payment.core.account.Account;
import com.nubank.payment.core.account.AccountPort;
import com.nubank.payment.core.account.AccountNotInitializedValidation;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;


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

    private Map<String, ValidationException> validationExceptions;

    public Account execute(final Transaction transaction) {

        accountNotInitializedValidation.validate();

        var account = accountPort.find();

        cardNotActiveValidation.validate(account);

        insufficienteLimitValidation.validate(account,transaction);

        var transactions = transactionPort.findAll();

        highFrequencyValidation.validate(account,transactions);

        doubleTransactionValidation.validate(account,transaction,transactions);

        account.setAvailableLimit(account.getAvailableLimit() - transaction.getAmount());

        account = accountPort.save(account);

        transactionPort.authorize(transaction);

        return account;
    }
}
