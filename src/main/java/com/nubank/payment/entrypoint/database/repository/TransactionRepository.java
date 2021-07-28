package com.nubank.payment.entrypoint.database.repository;

import com.nubank.payment.entrypoint.database.entity.TransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends JpaRepository<TransactionEntity, Integer> {

}
