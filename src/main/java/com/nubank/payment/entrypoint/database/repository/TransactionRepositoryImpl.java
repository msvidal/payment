package com.nubank.payment.entrypoint.database.repository;

import com.nubank.payment.entrypoint.database.entity.TransactionEntity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class TransactionRepositoryImpl implements TransactionRepository {

    private static TransactionRepositoryImpl instance = null;

    private static Map<Integer,TransactionEntity> repository;

    private TransactionRepositoryImpl() {
        repository = new HashMap<>();
    }

    public static TransactionRepository getInstance()
    {
        if (instance == null)
            instance = new TransactionRepositoryImpl();
        return instance;
    }

    @Override
    public TransactionEntity save(final TransactionEntity transactioEntity) {
        transactioEntity.setId(new Random().nextInt());
        repository.put(transactioEntity.getId(),transactioEntity);
        return transactioEntity;
    }

    @Override
    public List<TransactionEntity> findAll() {
        return new ArrayList<>(repository.values());
    }

}
