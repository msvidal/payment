package com.nubank.payment.core.transaction;

import com.nubank.payment.core.ValidationFactory;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class DoubleTransactionValidation {

    private static final Integer MAX_INTERVAL_TRANSACTION_MINUTE = 2;

    private static final Integer MAX_HIGH_TRANSACTION_FREQUENCY = 1;

    public void validate(Transaction transaction, final List<Transaction> transactions) {
        if(transactions.stream().filter(transaction1 ->
            transaction1.validateDoubleTransaction(transaction.getMerchant(), transaction.getAmount(),
                MAX_INTERVAL_TRANSACTION_MINUTE)).count() == MAX_HIGH_TRANSACTION_FREQUENCY){
            ValidationFactory.getInstance().addValidation("doubled-transaction");
        }
    }
}
