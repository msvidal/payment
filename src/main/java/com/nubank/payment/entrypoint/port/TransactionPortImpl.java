package com.nubank.payment.entrypoint.port;

import com.nubank.payment.core.transaction.Transaction;
import com.nubank.payment.core.transaction.TransactionPort;
import com.nubank.payment.entrypoint.database.repository.TransactionRepository;
import com.nubank.payment.entrypoint.database.entity.TransactionEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Component
public class TransactionPortImpl implements TransactionPort {

    private final TransactionRepository repository;

    @Override
    public Transaction authorize(final Transaction transaction) {

        var transactioEntity = TransactionEntity.builder()
            .merchant(transaction.getMerchant())
            .amount(transaction.getAmount())
            .time(transaction.getTime())
            .build();

        transactioEntity = repository.save(transactioEntity);

        return Transaction.builder()
            .merchant(transactioEntity.getMerchant())
            .amount(transactioEntity.getAmount())
            .time(transactioEntity.getTime())
            .build();
    }

    public List<Transaction> findAll() {
        return repository.findAll().stream().map(transactionEntity -> {
            return Transaction.builder()
                .merchant(transactionEntity.getMerchant())
                .amount(transactionEntity.getAmount())
                .time(transactionEntity.getTime())
                .build();
        }).collect(Collectors.toList());
    }
}
