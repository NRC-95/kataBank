package com.banktest.kataBank;

import com.banktest.kataBank.adapter.AccountRepository;
import com.banktest.kataBank.domain.Account;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class AccountControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private AccountRepository accountRepository;

    private Account account;

    @BeforeEach
    public void setup() {
        accountRepository.deleteAll();
        account = new Account();
        accountRepository.save(account);
    }

    @Test
    public void testDeposit() throws Exception {
        mockMvc.perform(post("/api/account/" + account.getId() + "/deposit/100")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string("Dépôt réussi"));
    }

    @Test
    public void testWithdraw() throws Exception {
        // Ensure the account has sufficient balance
        mockMvc.perform(post("/api/account/" + account.getId() + "/deposit/100")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string("Dépôt réussi"));

        // Now perform the withdrawal
        mockMvc.perform(post("/api/account/" + account.getId() + "/withdraw/50")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string("Retrait réussi"));
    }

    @Test
    public void testGetBalance() throws Exception {
        mockMvc.perform(get("/api/account/" + account.getId() + "/balance")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetTransactions() throws Exception {
        mockMvc.perform(get("/api/account/" + account.getId() + "/transactions")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}
