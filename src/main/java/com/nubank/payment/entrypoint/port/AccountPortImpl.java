package com.nubank.payment.entrypoint.port;

import com.nubank.payment.core.domain.Account;
import com.nubank.payment.core.ports.AccountPort;
import com.nubank.payment.entrypoint.database.AccountRepository;
import com.nubank.payment.entrypoint.database.entity.AccountEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.persistence.EntityNotFoundException;

@RequiredArgsConstructor
@Component
public class AccountPortImpl implements AccountPort {

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
    public Account find(final Integer id) {
        try {

            return AccountEntity.toDomain(repository.getOne(id));
        } catch (EntityNotFoundException ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
