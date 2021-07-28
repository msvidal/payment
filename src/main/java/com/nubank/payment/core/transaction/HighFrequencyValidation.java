package com.nubank.payment.core.transaction;

import com.nubank.payment.core.ValidationException;
import com.nubank.payment.core.account.Account;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class HighFrequencyValidation {

    private static final Integer MAX_HIGH_FREQUENCY = 3;

    private static final Integer MAX_INTERVAL_FREQUENCY_MINUTE = 2;

    public void validate(Account account, List<Transaction> transactions) {
        if(transactions.stream().filter(transaction1 ->
            transaction1.validateHighFrequency(MAX_INTERVAL_FREQUENCY_MINUTE)).count() > MAX_HIGH_FREQUENCY){
            throw new ValidationException(account,"high-frequency-small-interval");
        }
    }
}
