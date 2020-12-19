package com.nubank.payment.core.ports;

import com.nubank.payment.core.domain.Transaction;

import java.util.List;

public interface TransactionPort {

    Transaction authorize(Transaction transaction);

    List<Transaction> findAll();
}
