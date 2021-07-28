package com.nubank.payment;

import com.nubank.payment.core.ValidationException;
import com.nubank.payment.core.account.CreateAccountUseCase;
import com.nubank.payment.core.transaction.CreateTransactionUseCase;
import com.nubank.payment.entrypoint.Utils;
import com.nubank.payment.entrypoint.dto.AccountData;
import com.nubank.payment.entrypoint.dto.AccountRequest;
import com.nubank.payment.entrypoint.dto.AccountResponse;
import com.nubank.payment.entrypoint.dto.TransactionData;
import com.nubank.payment.entrypoint.dto.TransactionRequest;
import com.nubank.payment.entrypoint.dto.TransactionResponse;
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

        processAccounts(lines);

        System.out.println("===========================================================================");

        processTransactions(lines);
    }

    private void processAccounts(final List<String> lines) {
        var listAccounts = lines.stream().filter(linha -> linha.contains("account")).map(Utils::getAccount).collect(Collectors.toList());

        for (AccountRequest accountRequest : listAccounts){
            try {

                createAccountUseCase.execute(AccountData.toDomain(accountRequest.getAccountData()));

            } catch(ValidationException ex) {
                //AccountResponse.toRequest(null, ex.getMessage());
            }

            //AccountResponse.toRequest(null, null);

        }
    }

    private void processTransactions(final List<String> lines) {
        var listTransactions = lines.stream().filter(linha -> linha.contains("transaction")).map(Utils::getTransaction).collect(Collectors.toList());

        for (TransactionRequest transactionRequest : listTransactions){
            try {

                createTransactionUseCase.execute(TransactionData.toDomain(transactionRequest.getTransactionData()));

            } catch(ValidationException ex) {
                //TransactionResponse.toRequest(null, ex.getMessage());
            }

            //TransactionResponse.toRequest(null, null);

        }
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
