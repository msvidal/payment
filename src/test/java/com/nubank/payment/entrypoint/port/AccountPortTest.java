package com.nubank.payment.entrypoint.port;

import com.nubank.payment.core.account.Account;
import com.nubank.payment.entrypoint.database.repository.AccountRepositoryImpl;
import com.nubank.payment.entrypoint.database.entity.AccountEntity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AccountPortTest {

    @InjectMocks
    private AccountPortImpl accountPort;

    @Mock
    private AccountRepositoryImpl accountRepositoryImpl;

    private AccountEntity accountEntity;

    private Account account;

    @BeforeEach
    void init(){
         accountEntity = AccountEntity.builder()
            .id(1)
            .activeCard(true)
            .availableLimit(100)
            .build();

         account = Account.builder()
             .id(1)
             .activeCard(true)
             .availableLimit(100)
             .build();
    }

    @Test
    void should_save(){
        when(accountRepositoryImpl.save(any())).thenReturn(accountEntity);

        assertDoesNotThrow(() -> {
            var response = accountPort.save(account);
            assertNotNull(response);
            assertEquals(account.getId(), response.getId());
        });
    }

    @Test
    void should_checkIfAccountAlreadyExists(){
        when(accountRepositoryImpl.count()).thenReturn(1);

        assertDoesNotThrow(() -> {
            assertTrue(accountPort.checkIfAccountAlreadyExists());
        });
    }

    @Test
    void should_find(){
        when(accountRepositoryImpl.findById(any())).thenReturn(accountEntity);

        assertDoesNotThrow(() -> {
            var response = accountPort.find();
            assertNotNull(response);
            assertEquals(account.getId(), response.getId());
        });
    }

}
