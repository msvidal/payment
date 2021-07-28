package com.nubank.payment.core.usecase;

import com.nubank.payment.core.account.Account;
import com.nubank.payment.core.account.AccountPort;
import com.nubank.payment.core.account.AccountAlreadyInitializedValidation;
import com.nubank.payment.core.account.CreateAccountUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CreateAccountUseCaseTest {

    @InjectMocks
    private CreateAccountUseCase useCase;

    @Mock
    private AccountPort port;

    @Mock
    private AccountAlreadyInitializedValidation accountAlreadyInitializedValidation;

    private Account account;

    @BeforeEach
    public void setup() {
        account = Account.builder()
            .id(1)
            .activeCard(true)
            .availableLimit(100)
            .build();
    }

    @Test
    void shouldSave(){

        doNothing().when(accountAlreadyInitializedValidation).validate();

        when(port.save(any())).thenReturn(account);

        var account1 = useCase.execute(account);

        assertThat(account1.getAvailableLimit()).isEqualTo(account.getAvailableLimit());

    }
}
