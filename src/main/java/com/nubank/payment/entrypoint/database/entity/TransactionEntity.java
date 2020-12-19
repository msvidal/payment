package com.nubank.payment.entrypoint.database.entity;

import com.nubank.payment.core.domain.Transaction;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "transaction")
public class TransactionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer Id;
    private String merchant;
    private Integer amount;
    private LocalDateTime time;

    public static TransactionEntity from(Transaction transaction) {
        return TransactionEntity.builder()
            .merchant(transaction.getMerchant())
            .amount(transaction.getAmount())
            .time(transaction.getTime())
            .build();
    }

    public static Transaction toDomain(TransactionEntity transactionEntity) {
        return Transaction.builder()
            .merchant(transactionEntity.getMerchant())
            .amount(transactionEntity.getAmount())
            .time(transactionEntity.getTime())
            .build();
    }

}
