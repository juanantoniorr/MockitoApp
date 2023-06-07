package com.jrosas.test.springboot.app.springboottest.models;

import com.jrosas.test.springboot.app.springboottest.exceptions.InsuficientMoneyException;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigDecimal;

@Data
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

        this.balance = this.balance.add(amount);
    }
}
