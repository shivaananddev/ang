package com.example.ang.reponse;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class AccountResponse {
    private long id;
    private long balance;
    private long deposit;
    private long withdrawal;

}
