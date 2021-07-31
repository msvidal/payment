package com.nubank.payment.core.transaction;

import com.nubank.payment.core.account.Account;
import com.nubank.payment.core.transaction.CardNotActiveValidation;
import com.nubank.payment.entrypoint.database.entity.AccountEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@ExtendWith(MockitoExtension.class)
class CardNotActiveValidationTest {

    @InjectMocks
    private CardNotActiveValidation cardNotActiveValidation;

    private Account account;

    @BeforeEach
    void init(){
        account = Account.builder()
            .id(1)
            .activeCard(false)
            .availableLimit(100)
            .build();
    }

    @Test
    void should_validate(){
        assertDoesNotThrow(() -> {
            cardNotActiveValidation.validate(account);
        });
    }
}
