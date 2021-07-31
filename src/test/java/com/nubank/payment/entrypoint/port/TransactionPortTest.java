package com.nubank.payment.entrypoint.port;

import com.nubank.payment.core.transaction.Transaction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import java.time.LocalDateTime;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@ExtendWith(MockitoExtension.class)
class TransactionPortTest {

    @InjectMocks
    private TransactionPortImpl transactionPort;

    private Transaction transaction;

    @BeforeEach
    void init() {
        transaction = Transaction.builder()
            .merchant("Startup")
            .amount(100)
            .time(LocalDateTime.now())
            .build();
    }

    @Test
    void should_authorize() {
        assertDoesNotThrow(() -> {
            transactionPort.authorize(transaction);
        });
    }

    @Test
    void should_findAll() {
        assertDoesNotThrow(() -> {
            transactionPort.findAll();
        });
    }
}
