package com.banktest.kataBank.service.impl;

import com.banktest.kataBank.domain.TransactionType;
import com.banktest.kataBank.service.AccountService;
import com.banktest.kataBank.domain.Account;
import com.banktest.kataBank.domain.Transaction;
import com.banktest.kataBank.adapter.AccountRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Override
    @Transactional
    public void deposit(Long accountId, double amount) {
        log.info("Début du dépôt pour le compte ID: {}", accountId);
        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new IllegalArgumentException("Compte introuvable"));

        if (amount <= 0) {
            log.error("Le montant du dépôt doit être supérieur à zéro. Montant fourni: {}", amount);
            throw new IllegalArgumentException("Le dépôt doit être supérieur à zéro");
        }

        Transaction transaction = new Transaction(null, account, TransactionType.DEPOSIT, amount, LocalDateTime.now());
        account.addTransaction(transaction);
        account.setBalance(account.getBalance() + amount);

        accountRepository.save(account);
        log.info("Dépôt réussi pour le compte ID: {}", accountId);
    }

    @Override
    @Transactional
    public void withdraw(Long accountId, double amount) {
        log.info("Début du retrait pour le compte ID: {}", accountId);
        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new IllegalArgumentException("Compte introuvable"));

        double accountBalance = account.getBalance();
        if (amount <= 0 || amount > accountBalance) {
            log.error("Montant de retrait invalide: {}. Solde du compte: {}", amount, accountBalance);
            throw new IllegalArgumentException("Retrait invalide");
        }

        Transaction transaction = new Transaction(null, account, TransactionType.WITHDRAWAL, amount, LocalDateTime.now());
        account.addTransaction(transaction);
        account.setBalance(accountBalance - amount);
        accountRepository.save(account);
        log.info("Retrait réussi pour le compte ID: {}", accountId);
    }

    @Override
    @Transactional
    public double getBalance(Long accountId) {
        log.info("Récupération du solde pour le compte ID: {}", accountId);
        double balance = accountRepository.findById(accountId)
                .orElseThrow(() -> new IllegalArgumentException("Compte introuvable"))
                .getBalance();
        log.info("Solde pour le compte ID {}: {}", accountId, balance);
        return balance;
    }

    @Override
    @Transactional
    public List<Transaction> getTransactions(Long accountId) {
        log.info("Récupération des transactions pour le compte ID: {}", accountId);
        List<Transaction> transactions = accountRepository.findById(accountId)
                .orElseThrow(() -> new IllegalArgumentException("Compte introuvable"))
                .getTransactions();
        log.info("Transactions pour le compte ID {}: {}", accountId, transactions);
        return transactions;
    }
}
