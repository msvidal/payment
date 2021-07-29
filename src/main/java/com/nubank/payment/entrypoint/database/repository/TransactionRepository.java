package com.nubank.payment.entrypoint.database.repository;

import com.nubank.payment.entrypoint.database.entity.TransactionEntity;
import java.util.List;

public interface TransactionRepository {

    TransactionEntity save(TransactionEntity transactioEntity);

    List<TransactionEntity> findAll();

}
