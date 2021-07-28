package com.nubank.payment.core;

import com.nubank.payment.core.account.Account;
import lombok.Getter;

@Getter
public class ValidationException  extends RuntimeException {

    private Account account;

    public ValidationException(Account account, String message){
        super(message);
        this.account = account;
    }
}
