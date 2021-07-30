package com.nubank.payment.core.account;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@ExtendWith(MockitoExtension.class)
public class AccountNotInitializedValidationTest {

    @InjectMocks
    private AccountNotInitializedValidation accountNotInitializedValidation;

    @Test
    void should_validate(){
        assertDoesNotThrow(() -> {
            accountNotInitializedValidation.validate();
        });
    }
}
