package com.nubank.payment.core.transaction;

import com.nubank.payment.core.ValidationSingleton;
import com.nubank.payment.entrypoint.port.PortFactory;

import java.time.Duration;

public class DoubleTransactionValidation {

    private static final Integer MAX_INTERVAL_TRANSACTION_MINUTE = 2;

    private final TransactionPort transactionPort;

    public DoubleTransactionValidation() {
        this.transactionPort = new PortFactory().createTransactionPort();
    }

    public void validate(Transaction transaction) {

        var transactions = transactionPort.findAll();

        transactions.stream().filter(transaction1 ->
            transaction1.getAmount().equals(transaction.getAmount()) &&
            transaction1.getMerchant().equals(transaction.getMerchant()))
            .findFirst().map(transaction1 -> {
                Duration duration = Duration.between(transaction1.getTime(), transaction.getTime());
                if(Math.abs(duration.toMinutes()) < MAX_INTERVAL_TRANSACTION_MINUTE) {
                    ValidationSingleton.getInstance().addValidation("doubled-transaction");
                }
            return null;
        });
    }
}
