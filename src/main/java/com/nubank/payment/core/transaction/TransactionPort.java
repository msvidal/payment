package com.nubank.payment.core.transaction;

import java.util.List;

public interface TransactionPort {

    Transaction authorize(Transaction transaction);

    List<Transaction> findAll();
}
