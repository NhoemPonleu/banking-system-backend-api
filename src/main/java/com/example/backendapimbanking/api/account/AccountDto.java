package com.example.backendapimbanking.api.account;

import com.example.backendapimbanking.api.accountType.AccountTypeDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
public class AccountDto{
    Integer accountTypeId;
    String accountNo;
    String accountName;
    String profile;
    int pin;
    String  password;
    String phoneNumber;
    private AccountTypeDto accountType;

}


