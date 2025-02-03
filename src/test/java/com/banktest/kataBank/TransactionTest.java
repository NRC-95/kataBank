package com.banktest.kataBank;

import com.banktest.kataBank.domain.Account;
import com.banktest.kataBank.domain.Transaction;
import com.banktest.kataBank.domain.TransactionType;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class TransactionTest {

    @Test
    public void testTransactionCreation() {
        Account account = new Account();
        Transaction transaction = new Transaction(account, TransactionType.DEPOSIT, 100.0);

        assertEquals(account, transaction.getAccount());
        assertEquals(TransactionType.DEPOSIT, transaction.getType());
        assertEquals(100.0, transaction.getAmount());
    }
}
