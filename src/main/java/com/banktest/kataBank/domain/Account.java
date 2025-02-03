package com.banktest.kataBank.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private double balance; // Solde du compte

    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<Transaction> transactions = new ArrayList<>();

    public List<Transaction> getTransactions() {
        return transactions;
    }

    /**
     * Ajoute une transaction au compte
     * @param transaction La transaction à ajouter
     */
    public void addTransaction(Transaction transaction) {
        transactions.add(transaction);
        transaction.setAccount(this); // Établit la relation bidirectionnelle
    }
}
