package com.nubank.payment.entrypoint;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.nubank.payment.entrypoint.dto.AccountRequest;
import com.nubank.payment.entrypoint.dto.TransactionRequest;

public class Utils {

    public static AccountRequest getAccount(String line) {
        try {
            return getObjectMapper().readValue(line, AccountRequest.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static TransactionRequest getTransaction(String line) {
        try {
            return getObjectMapper().readValue(line,TransactionRequest.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static ObjectMapper getObjectMapper(){
        var objectMapper = new ObjectMapper();
        return objectMapper.registerModule(new JavaTimeModule());
    }
}
