package com.example.ang.controller;

import com.example.ang.reponse.AccountResponse;
import com.example.ang.service.AccountService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/account")
public class AccountResource {

    private final AccountService accountService;

    public AccountResource(AccountService accountService) {
        this.accountService = accountService;
    }

// for demo project we are  doing all operations on only one account in db;
    @GetMapping
    public ResponseEntity<Long> getMyBalance(){
        return ResponseEntity.ok().body(accountService.checkMyBalance());
    }

    @GetMapping("/deposit")
    public ResponseEntity<AccountResponse> doDeposit(@RequestParam("deposit") Long depositAmount){
        return ResponseEntity.ok().body(accountService.doDeposit(depositAmount));
    }

    @GetMapping("/withdraw")
    public ResponseEntity<AccountResponse> doWithdrawal(@RequestParam("withdraw")Long withdrawalAmount){
        return ResponseEntity.ok().body(accountService.doWithdrawal(withdrawalAmount));
    }
}
