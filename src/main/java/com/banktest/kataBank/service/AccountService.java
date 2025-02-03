package com.banktest.kataBank.service;

import com.banktest.kataBank.domain.Transaction;

import java.util.List;

public interface AccountService {

    /**
     * Service pour gérer un depot
     * @param accountId
     * @param amount
     */
    void deposit(Long accountId, double amount);

    /**
     * Service pour gérer un retrait
     * @param accountId
     * @param amount
     */
    void withdraw(Long accountId, double amount);

    /**
     * Service pour retourner le solde d'un compte
     * @param accountId
     * @return
     */
    double getBalance(Long accountId);

    /**
     * Service pour retourner la liste des transactions lier à un compte
     * @param accountId
     * @return
     */
    List<Transaction> getTransactions(Long accountId);
}
