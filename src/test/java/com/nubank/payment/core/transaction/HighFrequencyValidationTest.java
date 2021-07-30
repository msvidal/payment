package com.nubank.payment.core.transaction;

import com.nubank.payment.core.transaction.HighFrequencyValidation;
import com.nubank.payment.core.transaction.Transaction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@ExtendWith(MockitoExtension.class)
public class HighFrequencyValidationTest {

    @InjectMocks
    private HighFrequencyValidation highFrequencyValidation;

    private Transaction transaction;

    @BeforeEach
    void init(){
        transaction = Transaction.builder()
            .merchant("Startup")
            .amount(100)
            .time(LocalDateTime.now())
            .build();
    }

    @Test
    void should_validate(){
        assertDoesNotThrow(() -> {
            highFrequencyValidation.validate(transaction);
        });
    }
}
