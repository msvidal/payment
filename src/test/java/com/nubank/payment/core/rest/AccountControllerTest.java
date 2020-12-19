package com.nubank.payment.core.rest;

import com.nubank.payment.core.domain.Account;
import com.nubank.payment.core.usecase.CreateAccountUseCase;
import com.nubank.payment.entrypoint.dto.AccountRequest;
import com.nubank.payment.entrypoint.rest.AccountController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;


import static com.nubank.payment.helper.ObjectMapperHelper.OBJECT_MAPPER;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class AccountControllerTest {

    public static final String BASE_URL = "/v1/account";

    private MockMvc mockMvc;

    @InjectMocks
    private AccountController controller;

    @Mock
    private CreateAccountUseCase useCase;

    private AccountRequest accountRequest;

    private Account account;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders
            .standaloneSetup(controller)
            .build();

        accountRequest = AccountRequest.builder()
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
    public void when_createAccountWithSuccess_expect_statusCreated() throws Exception {
        when(useCase.execute(any())).thenReturn(account);

        mockMvc.perform(post(BASE_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .content(OBJECT_MAPPER.asJsonString(accountRequest)))
            .andExpect(status().isCreated());
    }
}
