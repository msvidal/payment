package com.nubank.payment.core.transaction;

import com.nubank.payment.core.account.Account;
import com.nubank.payment.core.transaction.InsufficienteLimitValidation;
import com.nubank.payment.core.transaction.Transaction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@ExtendWith(MockitoExtension.class)
public class InsuffcienteLimitValidationTest {

    @InjectMocks
    private InsufficienteLimitValidation insufficienteLimitValidation;

    private Account account;

    private Transaction transaction;

    @BeforeEach
    void init(){
        account = Account.builder()
            .id(1)
            .activeCard(true)
            .availableLimit(50)
            .build();

        transaction = Transaction.builder()
            .merchant("Startup")
            .amount(100)
            .time(LocalDateTime.now())
            .build();
    }

    @Test
    void should_validate(){
        assertDoesNotThrow(() -> {
            insufficienteLimitValidation.validate(account,transaction);
        });
    }
}
