package com.nubank.payment.core.account;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.Mockito.lenient;

@ExtendWith(MockitoExtension.class)
public class AccountNotInitializedValidationTest {

    @InjectMocks
    private AccountNotInitializedValidation accountNotInitializedValidation;

    @Mock
    private AccountPort accountPort;

    @Test
    void should_validate(){
        lenient().when(accountPort.checkIfAccountAlreadyExists()).thenReturn(false);
        assertDoesNotThrow(() -> {
            accountNotInitializedValidation.validate();
        });
    }
}
