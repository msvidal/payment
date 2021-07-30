package com.nubank.payment.core.transaction;

import com.nubank.payment.entrypoint.port.AccountPortImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
public class AuthorizeTransactionUseCaseTest {

    @InjectMocks
    private AuthorizeTransactionUseCase authorizeTransactionUseCase;

    @Mock
    private AccountPortImpl accountPort;

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
    void should_create_account_execute(){
        assertDoesNotThrow(() -> {
            var response = authorizeTransactionUseCase.execute(transaction);
            assertNotNull(response);
        });
    }
}
