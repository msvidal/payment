package com.nubank.payment.entrypoint.cli;

import com.nubank.payment.core.account.Account;
import com.nubank.payment.core.account.CreateAccountUseCase;
import com.nubank.payment.core.transaction.CreateTransactionUseCase;
import com.nubank.payment.core.transaction.Transaction;
import com.nubank.payment.entrypoint.Utils;
import com.nubank.payment.entrypoint.cli.dto.AccountResponse;
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
public class PaymentRunner implements CommandLineRunner {

    private final CreateAccountUseCase createAccountUseCase;

    private final CreateTransactionUseCase createTransactionUseCase;

    @Override
    public void run(final String... args) throws Exception {
        process();
    }

    private void process() throws IOException {

        List<String> lines = processFile();

        System.out.println("===========================================================================");

        processOperations(lines);

        System.out.println("===========================================================================");
        System.out.println("Pressione alguma tecla e enter para finalizar");
        System.in.read();
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

    private void processOperations(final List<String> lines) {

        for (String line : lines){
            if(line.contains("account")){
                var accountRequest = Utils.getAccount(line);
                var account = Account.builder()
                    .activeCard(accountRequest.getAccountData().getActiveCard())
                    .availableLimit(accountRequest.getAccountData().getAvailableLimit())
                    .build();

                account = createAccountUseCase.execute(account);
                AccountResponse.parseJson(account);

            }

            if(line.contains("transaction")){
                var transactionRequest = Utils.getTransaction(line);
                var transaction = Transaction.builder()
                    .amount(transactionRequest.getTransactionData().getAmount())
                    .merchant(transactionRequest.getTransactionData().getMerchant())
                    .time(transactionRequest.getTransactionData().getTime())
                    .build();

                var account = createTransactionUseCase.execute(transaction);

                AccountResponse.parseJson(account);
            }
        }
    }
}
