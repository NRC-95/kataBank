package com.banktest.kataBank.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class TransactionDTO {
    private String type;
    private double amount;
    private LocalDateTime timestamp;
}
