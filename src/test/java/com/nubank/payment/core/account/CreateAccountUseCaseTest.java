package com.nubank.payment.core.account;

import com.nubank.payment.core.transaction.InsufficienteLimitValidation;
import com.nubank.payment.core.transaction.Transaction;
import com.nubank.payment.entrypoint.port.AccountPortImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CreateAccountUseCaseTest {

    @InjectMocks
    private CreateAccountUseCase createAccountUseCase;

    @Mock
    private AccountPortImpl accountPort;

    @Mock
    private AccountAlreadyInitializedValidation accountAlreadyInitializedValidation;

    private Account account;
    private Transaction transaction;

    @BeforeEach
    void init(){
         account = Account.builder()
             .id(1)
             .activeCard(true)
             .availableLimit(100)
             .build();

        transaction = Transaction.builder()
            .merchant("Startup")
            .amount(100)
            .time(LocalDateTime.now())
            .build();

    }

    @Test
    void should_create_account_execute(){

        when(accountPort.find()).thenReturn(account);
        lenient().when(accountPort.save(account)).thenReturn(account);
        doNothing().when(accountAlreadyInitializedValidation).validate(account);

        assertDoesNotThrow(() -> {
            var response = createAccountUseCase.execute(account);
            assertNotNull(response);
            assertEquals(account.getId(), response.getId());
        });
    }
}
