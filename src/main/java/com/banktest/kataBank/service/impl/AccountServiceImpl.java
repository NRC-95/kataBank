package com.banktest.kataBank.service.impl;

import com.banktest.kataBank.domain.TransactionType;
import com.banktest.kataBank.service.AccountService;
import com.banktest.kataBank.domain.Account;
import com.banktest.kataBank.domain.Transaction;
import com.banktest.kataBank.adapter.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Override
    @Transactional
    public void deposit(Long accountId, double amount) {
        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new IllegalArgumentException("Compte introuvable"));

        if (amount <= 0) throw new IllegalArgumentException("Le dépôt doit être supérieur à zéro");

        Transaction transaction = new Transaction(null, account, TransactionType.DEPOSIT, amount, LocalDateTime.now());
        account.addTransaction(transaction);
        account.setBalance(account.getBalance() + amount);

        accountRepository.save(account);
    }

    @Override
    @Transactional
    public void withdraw(Long accountId, double amount) {
        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new IllegalArgumentException("Compte introuvable"));

        double accountBalance = account.getBalance();
        if (amount <= 0 || amount > accountBalance) {
            throw new IllegalArgumentException("Retrait invalide");
        }

        Transaction transaction = new Transaction(null, account, TransactionType.WITHDRAWAL, amount, LocalDateTime.now());
        account.addTransaction(transaction);
        account.setBalance(accountBalance - amount);
        accountRepository.save(account);
    }

    @Override
    @Transactional
    public double getBalance(Long accountId) {
        return accountRepository.findById(accountId)
                .orElseThrow(() -> new IllegalArgumentException("Compte introuvable"))
                .getBalance();
    }

    @Override
    @Transactional
    public List<Transaction> getTransactions(Long accountId) {
        return accountRepository.findById(accountId)
                .orElseThrow(() -> new IllegalArgumentException("Compte introuvable"))
                .getTransactions();
    }
}
