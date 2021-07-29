package com.nubank.payment.core.transaction;

import com.nubank.payment.core.ValidationFactory;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.List;

@Service
@AllArgsConstructor
public class DoubleTransactionValidation {

    private static final Integer MAX_INTERVAL_TRANSACTION_MINUTE = 2;

    private final TransactionPort transactionPort;

    public void validate(Transaction transaction) {

        var transactions = transactionPort.findAll();

        var filter = transactions.stream().filter(transaction1 ->
            transaction1.getAmount().equals(transaction.getAmount()) &&
            transaction1.getMerchant().equals(transaction.getMerchant()))
            .findFirst().orElse(null);

        if(filter != null) {
            Duration duration = Duration.between(filter.getTime(), transaction.getTime());
            if(Math.abs(duration.toMinutes()) < MAX_INTERVAL_TRANSACTION_MINUTE) {
                ValidationFactory.getInstance().addValidation("doubled-transaction");
            }
        }
    }
}
