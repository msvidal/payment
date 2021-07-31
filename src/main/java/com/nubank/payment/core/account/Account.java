package com.nubank.payment.core.account;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Account {

    private Integer id;
    private Boolean activeCard;
    private Integer availableLimit;
    private List<String> validations;

    public void setAvailableLimit(final int availableLimit) {
        this.availableLimit = availableLimit;
    }

    public List<String> getValidations() {
        return Optional.ofNullable(validations).orElse(Collections.emptyList());
    }
}
