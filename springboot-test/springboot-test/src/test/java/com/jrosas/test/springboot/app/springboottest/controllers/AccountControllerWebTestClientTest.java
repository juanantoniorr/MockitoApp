package com.jrosas.test.springboot.app.springboottest.controllers;

import com.jrosas.test.springboot.app.springboottest.controllers.dto.AccountTransferDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.math.BigDecimal;

import static org.hamcrest.core.Is.is;

//Levanta un servidor real, necesitamos levantar primero la aplicacion
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class AccountControllerWebTestClientTest {
    @Autowired
    private WebTestClient webTestClient;

    @Test
    void testTransfer(){
        //given
        AccountTransferDTO transactionDto = new AccountTransferDTO();
        transactionDto.setOriginId(1L);
        transactionDto.setTargetId(2L);
        transactionDto.setBankId(1L);
        transactionDto.setAmount(new BigDecimal(100));

        //when
        webTestClient.post()
                .uri("http://localhost:8080/transfer")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(transactionDto)
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.message").isNotEmpty()
                .jsonPath("$.message").value(is("transfer success"));



    }

}