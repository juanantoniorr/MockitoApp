package com.jrosas.test.springboot.app.springboottest.controllers;

import com.jrosas.test.springboot.app.springboottest.controllers.dto.AccountTransferDTO;
import com.jrosas.test.springboot.app.springboottest.models.Account;
import com.jrosas.test.springboot.app.springboottest.repos.AccountRepository;
import com.jrosas.test.springboot.app.springboottest.services.AccountService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

@AllArgsConstructor
@RestController
public class AccountController {

    private final AccountService accountService;

    @GetMapping("/details/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Account getDetails(@PathVariable Long id){
        return accountService.findById(id);
    }
    @PostMapping("/transfer")
    public ResponseEntity<?> transferir (@RequestParam AccountTransferDTO accountTransferDTO){
        accountService.transfer(accountTransferDTO.getOriginId(),
                accountTransferDTO.getTargetId(), accountTransferDTO.getAmount(),
                accountTransferDTO.getBankId());
        Map<String, Object> response = new HashMap<>();
        response.put("Date", LocalDate.now());
        response.put("status", "ok");
        response.put("message", "transfer success");
        response.put("transaction", accountTransferDTO);

        return ResponseEntity.ok(response);
    }

}
