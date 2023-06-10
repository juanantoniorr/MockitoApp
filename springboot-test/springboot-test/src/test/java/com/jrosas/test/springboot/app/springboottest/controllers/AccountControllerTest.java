package com.jrosas.test.springboot.app.springboottest.controllers;

import com.jrosas.test.springboot.app.springboottest.Data;
import com.jrosas.test.springboot.app.springboottest.services.AccountService;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;

import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(AccountController.class)
class AccountControllerTest {
    @Autowired
    private MockMvc mockMvc;
    //MockBean: replace any existing bean, useful for IT
    //Mock: only creates a mock, will cause an error because bean will not be part of context
    @MockBean
    AccountService accountService;

    @Test
    void getDetails() throws Exception {
        //Given
        when(accountService.findById(1L)).thenReturn(Data.ACCOUNT_001);
        //when
        mockMvc.perform(get("/details/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Juan"));

    }
}