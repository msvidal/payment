package com.nubank.payment;

import com.nubank.payment.core.account.Account;
import com.nubank.payment.core.account.AccountAlreadyInitializedValidation;
import com.nubank.payment.core.account.AccountNotInitializedValidation;
import com.nubank.payment.core.account.CreateAccountUseCase;
import com.nubank.payment.core.transaction.AuthorizeTransactionUseCase;
import com.nubank.payment.core.transaction.CardNotActiveValidation;
import com.nubank.payment.core.transaction.DoubleTransactionValidation;
import com.nubank.payment.core.transaction.HighFrequencyValidation;
import com.nubank.payment.core.transaction.InsufficienteLimitValidation;
import com.nubank.payment.core.transaction.Transaction;
import com.nubank.payment.entrypoint.Utils;
import com.nubank.payment.entrypoint.dto.AccountResponse;
import com.nubank.payment.entrypoint.port.AccountPortImpl;
import com.nubank.payment.entrypoint.port.TransactionPortImpl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.stream.Collectors;

public class PaymentApplication {

	public static void main(String[] args) throws IOException {
		try (BufferedReader in = new BufferedReader(new InputStreamReader(System.in))) {
			process(in.lines().collect(Collectors.toList()));
		}
	}

	public static void process(List<String> lines) {

		for (String line : lines){
			if(line.contains("account")){
				var accountRequest = Utils.getAccount(line);
				var account = Account.builder()
					.activeCard(accountRequest.getAccountData().getActiveCard())
					.availableLimit(accountRequest.getAccountData().getAvailableLimit())
					.build();

				account = getCreateAccountUseCase().execute(account);
				AccountResponse.parseJson(account);

			}

			if(line.contains("transaction")){
				var transactionRequest = Utils.getTransaction(line);
				var transaction = Transaction.builder()
					.amount(transactionRequest.getTransactionData().getAmount())
					.merchant(transactionRequest.getTransactionData().getMerchant())
					.time(transactionRequest.getTransactionData().getTime())
					.build();

				var account = getAuthorizeTransactionUseCase().execute(transaction);

				AccountResponse.parseJson(account);
			}
		}
	}

	private static CreateAccountUseCase getCreateAccountUseCase(){
		return new CreateAccountUseCase(new AccountPortImpl(), new AccountAlreadyInitializedValidation());
	}

	private static AuthorizeTransactionUseCase getAuthorizeTransactionUseCase(){
		return new AuthorizeTransactionUseCase(new TransactionPortImpl(), new AccountPortImpl(), new AccountNotInitializedValidation(),
			new CardNotActiveValidation(),new InsufficienteLimitValidation(), new HighFrequencyValidation(), new DoubleTransactionValidation());
	}

}