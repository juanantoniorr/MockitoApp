package com.jrosas.test.springboot.app.springboottest.controllers.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Value;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountTransferDTO {
    private Long originId;
    private Long targetId;
    private BigDecimal amount;
    private Long bankId;

}
