package com.nubank.payment.entrypoint.port;

import com.nubank.payment.core.domain.Transaction;
import com.nubank.payment.core.ports.TransactionPort;
import com.nubank.payment.entrypoint.database.TransactionRepository;
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
        return TransactionEntity.toDomain(repository.save(TransactionEntity.from(transaction)));
    }

    public List<Transaction> findAll() {
        return repository.findAll().stream().map((TransactionEntity::toDomain)).collect(Collectors.toList());
    }
}
