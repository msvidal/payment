package com.nubank.payment.entrypoint.port;

import com.nubank.payment.core.account.Account;
import com.nubank.payment.core.account.AccountPort;
import com.nubank.payment.entrypoint.database.repository.AccountRepository;
import com.nubank.payment.entrypoint.database.entity.AccountEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.persistence.EntityNotFoundException;

@RequiredArgsConstructor
@Component
public class AccountPortImpl implements AccountPort {

    private static final Integer ID_ACCOUNT = 1;

    private final AccountRepository repository;

    @Override
    public Account save(final Account account) {

        var accountEntity = AccountEntity.builder()
            .id(account.getId())
            .activeCard(account.getActiveCard())
            .availableLimit(account.getAvailableLimit())
            .build();

        accountEntity = repository.save(accountEntity);

        return Account.builder()
            .id(accountEntity.getId())
            .activeCard(accountEntity.getActiveCard())
            .availableLimit(accountEntity.getAvailableLimit())
            .build();
    }

    @Override
    public boolean checkIfAccountAlreadyExists() {

        return repository.count() > 0;
    }

    @Override
    public Account find() {
        try {

            var accountEntity = repository.findById(ID_ACCOUNT). orElse(null);

            return Account.builder()
            .id(accountEntity.getId())
            .activeCard(accountEntity.getActiveCard())
            .availableLimit(accountEntity.getAvailableLimit())
            .build();

        } catch (EntityNotFoundException ex) {
            // omitindo o stacktrace para nao poluir o console
        }
        return null;
    }
}
