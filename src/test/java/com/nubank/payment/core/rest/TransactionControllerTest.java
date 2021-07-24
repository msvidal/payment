package com.nubank.payment.core.rest;

import com.nubank.payment.core.domain.Transaction;
import com.nubank.payment.core.usecase.CreateTransactionUseCase;
import com.nubank.payment.entrypoint.dto.TransactionRequest;
import com.nubank.payment.entrypoint.rest.TransactionController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDateTime;

import static com.nubank.payment.helper.ObjectMapperHelper.OBJECT_MAPPER;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class TransactionControllerTest {

    private static final String BASE_URL = "/v1/transaction";

    private MockMvc mockMvc;

    @InjectMocks
    private TransactionController controller;

    @Mock
    private CreateTransactionUseCase useCase;

    private TransactionRequest transactionRequest;

    private Transaction transaction;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders
            .standaloneSetup(controller)
            .build();

        transactionRequest = TransactionRequest.builder()
            .merchant("Burguer King")
            .amount(10)
            .time(LocalDateTime.now())
            .build();

        transaction = Transaction.builder()
            .merchant("Burguer King")
            .amount(10)
            .time(LocalDateTime.now())
            .build();
    }
    @Test
    void when_createTransactionWithSuccess_expect_statusCreated() throws Exception {
        when(useCase.execute(any(),any())).thenReturn(transaction);

        mockMvc.perform(post(BASE_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .content(OBJECT_MAPPER.asJsonString(transactionRequest)))
            .andExpect(status().isCreated());
    }
}
