package com.nubank.payment.core.transaction;

import com.nubank.payment.core.account.Account;
import com.nubank.payment.core.account.AccountNotInitializedValidation;
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
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AuthorizeTransactionUseCaseTest {

    @InjectMocks
    private AuthorizeTransactionUseCase authorizeTransactionUseCase;

    @Mock
    private AccountNotInitializedValidation accountNotInitializedValidation;

    @Mock
    private CardNotActiveValidation cardNotActiveValidation;

    @Mock
    private InsufficienteLimitValidation insufficienteLimitValidation;

    @Mock
    private HighFrequencyValidation highFrequencyValidation;

    @Mock
    private DoubleTransactionValidation doubleTransactionValidation;

    @Mock
    private AccountPortImpl accountPort;

    @Mock
    private TransactionPort transactionPort;

    private Transaction transaction;
    private Account account;

    @BeforeEach
    void init(){
        transaction = Transaction.builder()
            .merchant("Startup")
            .amount(100)
            .time(LocalDateTime.now())
            .build();

        account = Account.builder()
            .id(1)
            .activeCard(true)
            .availableLimit(100)
            .build();

    }

    @Test
    void should_create_account_execute(){

        doNothing().when(accountNotInitializedValidation).validate();
        when(accountPort.find()).thenReturn(account);
        lenient().doNothing().when(cardNotActiveValidation).validate(account);
        doNothing().when(insufficienteLimitValidation).validate(account,transaction);
        doNothing().when(cardNotActiveValidation).validate(account);
        doNothing().when(highFrequencyValidation).validate(transaction);
        doNothing().when(doubleTransactionValidation).validate(transaction);
        lenient().when(accountPort.save(account)).thenReturn(account);
        lenient().when(transactionPort.authorize(transaction)).thenReturn(transaction);

        assertDoesNotThrow(() -> {
            var response = authorizeTransactionUseCase.execute(transaction);
            assertNotNull(response);
        });
    }
}
