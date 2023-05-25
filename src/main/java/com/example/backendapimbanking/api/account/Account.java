package com.example.backendapimbanking.api.account;

import com.example.backendapimbanking.api.accountType.AccountType;
import com.example.backendapimbanking.api.user.User;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Account {
    private int id;
    private String accountNo;
    private String accountName;
    private String profile;
    private  int pin;
    private String  password;
    private String phoneNumber;
   private AccountType type;
}
