package com.example.ang.service;

import com.example.ang.reponse.AccountResponse;

public interface AccountService {

    Long checkMyBalance();

    AccountResponse doDeposit(Long depositAmount);

    AccountResponse doWithdrawal(Long withdrawalAmount);
}
