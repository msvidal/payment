package com.nubank.payment.core.usecase;

import com.nubank.payment.core.domain.Transaction;
import com.nubank.payment.core.exception.ValidationException;
import com.nubank.payment.core.ports.AccountPort;
import com.nubank.payment.core.ports.TransactionPort;
import com.nubank.payment.core.validators.AccountNotInitializedValidation;
import com.nubank.payment.core.validators.CardNotActiveValidation;
import com.nubank.payment.core.validators.DoubleTransactionValidation;
import com.nubank.payment.core.validators.HighFrequencyValidation;
import com.nubank.payment.core.validators.InsufficienteLimitValidation;
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

    public Transaction execute(final Transaction transaction, final Integer accountID) {

        accountNotInitializedValidation.validate();

        var account = accountPort.find(accountID);

        cardNotActiveValidation.validate(account);

        insufficienteLimitValidation.validate(account,transaction);

        var transactions = transactionPort.findAll();

        highFrequencyValidation.validate(transactions);

        doubleTransactionValidation.validate(transaction,transactions);

        account.setAvailableLimit(account.getAvailableLimit() - transaction.getAmount());

        accountPort.save(account);

        return transactionPort.authorize(transaction);
    }
}
