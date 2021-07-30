package com.nubank.payment.core.account;

import com.nubank.payment.entrypoint.port.AccountPortImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
public class CreateAccountUseCaseTest {

    @InjectMocks
    private CreateAccountUseCase createAccountUseCase;

    @Mock
    private AccountPortImpl accountPort;

    private Account account;

    @BeforeEach
    void init(){
         account = Account.builder()
             .id(1)
             .activeCard(true)
             .availableLimit(100)
             .build();
    }

    @Test
    void should_create_account_execute(){
        assertDoesNotThrow(() -> {
            var response = createAccountUseCase.execute(account);
            assertNotNull(response);
            assertEquals(account.getId(), response.getId());
        });
    }
}
