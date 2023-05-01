package com.jrosas.test.springboot.app.springboottest;

import com.jrosas.test.springboot.app.springboottest.models.Account;

import java.math.BigDecimal;

public class Data {
    public static final Account ACCOUNT_001 = new Account(1L,"Juan", new BigDecimal(1000));
    public static final Account ACCOUNT_002 = new Account(2L,"Andres", new BigDecimal(2000));
    public static final Account ACCOUNT_003 = new Account(3L,"Santander", BigDecimal.ZERO);
}
