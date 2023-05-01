package com.jrosas.test.springboot.app.springboottest.models;

import com.jrosas.test.springboot.app.springboottest.exceptions.InsuficientMoneyException;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class Account {
    private Long id;
    private String name;
    private BigDecimal balance;

    public void debit(BigDecimal amount){
       BigDecimal newBalance = this.balance.subtract(amount);
       if (newBalance.compareTo(BigDecimal.ZERO)< 0){
           throw new InsuficientMoneyException("Not enough money");
       }
       this.balance = newBalance;
    }

    public void credit(BigDecimal amount){
    this.balance.add(amount);
    }
}
