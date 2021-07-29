package com.nubank.payment.core.transaction;

import com.nubank.payment.core.ValidationFactory;
import com.nubank.payment.entrypoint.port.TransactionPortImpl;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class HighFrequencyValidation {

    private static final Integer MAX_HIGH_FREQUENCY = 3;
    private static final Integer MAX_INTERVAL_FREQUENCY_MINUTE = 2;

    private final TransactionPort transactionPort;

    public HighFrequencyValidation() {
        this.transactionPort = new TransactionPortImpl();
    }

    public void validate(Transaction transaction) {

        var transactions = transactionPort.findAll();

        var filterCount = transactions.stream().filter(transaction1 -> {
            return getDateTimeInterval(transaction.getTime()).isBefore(transaction1.getTime()) &&
                transaction1.getTime().isAfter(getDateTimeInterval(transaction.getTime()));
        }).count();

        if(filterCount == MAX_HIGH_FREQUENCY){
            ValidationFactory.getInstance().addValidation("high-frequency-small-interval");
        }
    }

    private LocalDateTime getDateTimeInterval(LocalDateTime localDateTime){
        return localDateTime.minus(MAX_INTERVAL_FREQUENCY_MINUTE, ChronoUnit.MINUTES);
    }
}
