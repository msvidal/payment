package com.nubank.payment;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.nubank.payment.core.account.CreateAccountUseCase;
import com.nubank.payment.core.transaction.CreateTransactionUseCase;
import com.nubank.payment.entrypoint.Utils;
import com.nubank.payment.entrypoint.dto.AccountData;
import com.nubank.payment.entrypoint.dto.AccountRequest;
import com.nubank.payment.entrypoint.dto.TransactionData;
import com.nubank.payment.entrypoint.dto.TransactionRequest;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class PaymentRunner  implements CommandLineRunner {

    private final CreateAccountUseCase createAccountUseCase;

    private final CreateTransactionUseCase createTransactionUseCase;

    @Override
    public void run(final String... args) throws Exception {
        process();
    }

    private void process() {

        List<String> lines = processFile();

        lines.stream().filter(linha -> linha.contains("account")).map(Utils::getAccount).
            forEach(accountRequest -> createAccountUseCase.execute(AccountData.toDomain(accountRequest.getAccountData())));

        System.out.println("===========================================================================");

        lines.stream().filter(linha -> linha.contains("transaction")).map(Utils::getTransaction).
            forEach(transactionRequest -> createTransactionUseCase.execute(TransactionData.toDomain(transactionRequest.getTransactionData())));

    }

    private List<String> processFile() {
        List<String> lines = null;
        try {
            String path = ResourceUtils.getFile("classpath:operations").getPath();
            lines = Files.lines(Paths.get(path)).collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lines;
    }
}
