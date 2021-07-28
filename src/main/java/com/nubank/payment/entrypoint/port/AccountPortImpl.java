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
        return AccountEntity.toDomain(repository.save(AccountEntity.from(account)));
    }

    @Override
    public boolean checkIfAccountAlreadyExists() {
        return repository.count() > 0;
    }

    @Override
    public Account find() {
        try {

            return AccountEntity.toDomain(repository.findById(ID_ACCOUNT).orElse(null));

        } catch (EntityNotFoundException ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
