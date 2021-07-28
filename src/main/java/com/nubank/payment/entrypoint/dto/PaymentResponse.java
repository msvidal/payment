package com.nubank.payment.entrypoint.dto;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.nubank.payment.entrypoint.Utils;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public abstract class PaymentResponse {

    protected static List<String> toValidations(String message) {
        if(message == null) {
            return Collections.emptyList();
        }

        return Arrays.asList(message);
    }

    protected static void parseJson(TransactionResponse transactionResponse) {
        try {

            System.out.println(Utils.getObjectMapper().writeValueAsString(transactionResponse));

        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    protected static void parseJson(AccountResponse accountResponse) {
        try {

            System.out.println(Utils.getObjectMapper().writeValueAsString(accountResponse));

        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

}
