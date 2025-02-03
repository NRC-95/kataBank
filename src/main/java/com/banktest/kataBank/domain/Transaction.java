package com.banktest.kataBank.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "account_id", nullable = false)
    private Account account;

    private TransactionType type;
    private double amount;
    private LocalDateTime timestamp = LocalDateTime.now();

    public Transaction(Account account, TransactionType type, double amount) {
        this.account = account;
        this.type = type;
        this.amount = amount;
    }

    /**
     * Associe cette transaction à un compte
     * @param account Le compte à lier
     */
    public void setAccount(Account account) {
        this.account = account;
    }
}
