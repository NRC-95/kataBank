package com.banktest.kataBank;

import com.banktest.kataBank.domain.Account;
import com.banktest.kataBank.domain.Transaction;
import com.banktest.kataBank.domain.TransactionType;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class AccountTest {

    @Test
    public void testAddTransaction() {
        Account account = new Account();
        Transaction transaction = new Transaction(account, TransactionType.DEPOSIT, 100.0);
        account.addTransaction(transaction);

        assertEquals(1, account.getTransactions().size());
        assertEquals(transaction, account.getTransactions().get(0));
    }
}