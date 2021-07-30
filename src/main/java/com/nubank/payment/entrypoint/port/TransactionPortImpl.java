package com.nubank.payment.entrypoint.port;

import com.nubank.payment.core.transaction.Transaction;
import com.nubank.payment.core.transaction.TransactionPort;
import com.nubank.payment.entrypoint.database.repository.TransactionRepository;
import com.nubank.payment.entrypoint.database.entity.TransactionEntity;
import com.nubank.payment.entrypoint.database.repository.TransactionRepositoryImpl;

import java.util.List;
import java.util.stream.Collectors;

public class TransactionPortImpl implements TransactionPort {

    private final TransactionRepository transactionRepository;

    public TransactionPortImpl() {
        this.transactionRepository = TransactionRepositoryImpl.getInstance();
    }

    @Override
    public Transaction authorize(final Transaction transaction) {

        var transactionEntity = TransactionEntity.builder()
            .merchant(transaction.getMerchant())
            .amount(transaction.getAmount())
            .time(transaction.getTime())
            .build();

        transactionEntity = transactionRepository.save(transactionEntity);

        return Transaction.builder()
            .merchant(transactionEntity.getMerchant())
            .amount(transactionEntity.getAmount())
            .time(transactionEntity.getTime())
            .build();
    }

    public List<Transaction> findAll() {
        return transactionRepository.findAll().stream().map(transactionEntity -> {
            return Transaction.builder()
                .merchant(transactionEntity.getMerchant())
                .amount(transactionEntity.getAmount())
                .time(transactionEntity.getTime())
                .build();
        }).collect(Collectors.toList());
    }
}
