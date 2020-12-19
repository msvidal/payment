package com.nubank.payment.core.usecase;

import com.nubank.payment.core.domain.Transaction;
import com.nubank.payment.core.exception.ValidationException;
import com.nubank.payment.core.ports.AccountPort;
import com.nubank.payment.core.ports.TransactionPort;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
@AllArgsConstructor
public class CreateTransactionUseCase {

    private final TransactionPort transactionPort;

    private final AccountPort accountPort;

    private static final Integer MAX_HIGH_FREQUENCY = 3;

    private static final Integer MAX_INTERVAL_FREQUENCY_MINUTE = 2;

    private static final Integer MAX_INTERVAL_TRANSACTION_MINUTE = 2;

    private static final Integer MAX_HIGH_TRANSACTION_FREQUENCY = 1;

    private Map<String, ValidationException> validationExceptions;

    public Transaction execute(final Transaction transaction, final Integer accountID) {

        validationExceptions = new HashMap();

        if(!accountPort.checkIfAccountAlreadyExists()){
            addException("account-not-initialized");
        }

        var account = accountPort.find(accountID);

        if(account != null) {

            if (Boolean.FALSE.equals(account.getActiveCard())){
                addException("card-not-active");
            }

            if(transaction.getAmount() > account.getAvailableLimit()) {
                addException("insufficient-limit");
            }
        }

        var transactions = transactionPort.findAll();

        validateHighFrequency(transactions);

        validateDoubleTransaction(transaction, transactions);

        if(!validationExceptions.isEmpty()) {
            throw new ValidationException(new ArrayList(validationExceptions.values()));
        }

        account.setAvailableLimit(account.getAvailableLimit() - transaction.getAmount());

        accountPort.save(account);

        transaction.setTime(LocalDateTime.now());

        return transactionPort.authorize(transaction);
    }

    private void validateHighFrequency(final List<Transaction> transactions) {
        if(transactions.stream().filter(transaction1 ->
                    transaction1.validateHighFrequency(MAX_INTERVAL_FREQUENCY_MINUTE)).count() > MAX_HIGH_FREQUENCY){
            addException("high-frequency-small-interval");
        }
    }

    private void validateDoubleTransaction(final Transaction transaction, final List<Transaction> transactions) {
        if(transactions.stream().filter(transaction1 ->
            transaction1.validateDoubleTransaction(transaction.getMerchant(), transaction.getAmount(),
                MAX_INTERVAL_TRANSACTION_MINUTE)).count() == MAX_HIGH_TRANSACTION_FREQUENCY){
            addException("doubled-transaction");
        }
    }

    private void addException(String message) {
        validationExceptions.put(message, new ValidationException(message));
    }

}
