package com.example.ang.service.impl;

import com.example.ang.exception.AccountDemoNotFoundException;
import com.example.ang.exception.InsufficientFundsException;
import com.example.ang.model.Account;
import com.example.ang.model.repo.AccountRepo;
import com.example.ang.reponse.AccountResponse;
import com.example.ang.service.AccountService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountServiceImpl implements AccountService {

    private final AccountRepo accountRepo;

    public AccountServiceImpl(AccountRepo accountRepo) {
        this.accountRepo = accountRepo;
    }

    @Override
    public Long checkMyBalance() {
        return getBalance();

    }

    private Long getBalance() {
        List<Account> accounts = this.accountRepo.getAllAccounts();
        if(accounts == null || accounts.isEmpty()){
            throw new AccountDemoNotFoundException("No accounts found");
        }
        return accounts.get(0).getBalance();
    }

    @Override
    public AccountResponse doDeposit(Long depositAmount) {
        List<Account> accounts = this.accountRepo.getAllAccounts();
        if(accounts == null || accounts.isEmpty()){
            throw new AccountDemoNotFoundException("No accounts found");
        }
        Account account = accounts.get(0);
        account.setDeposit(depositAmount);
        account.setBalance(account.getBalance()+depositAmount);
        this.accountRepo.save(account);
        return convertToAccountResponse(account);
    }

    private AccountResponse convertToAccountResponse(Account account) {
        return AccountResponse.builder()
                .id(account.getId())
                .balance(account.getBalance())
                .deposit(account.getDeposit())
                .withdrawal(account.getWithdrawal())
                .build();
    }

    @Override
    public AccountResponse doWithdrawal(Long withdrawalAmount) {
        Long balance = getBalance();
        if(balance == null || balance == 0L || balance < withdrawalAmount){
            throw new InsufficientFundsException("Balance is less than withdrawal amount. Balance is:"+balance);
        }
        List<Account> accounts = this.accountRepo.getAllAccounts();
        if(accounts == null || accounts.isEmpty()){
            throw new AccountDemoNotFoundException("No accounts found");
        }
        Account account = accounts.get(0);
        account.setWithdrawal(withdrawalAmount);
        account.setBalance(account.getBalance()-withdrawalAmount);
        this.accountRepo.save(account);
        return convertToAccountResponse(account);
    }
}
