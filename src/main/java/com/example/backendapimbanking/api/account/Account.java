package com.example.backendapimbanking.api.account;

import com.example.backendapimbanking.api.accountType.AccountType;
import com.example.backendapimbanking.api.user.User;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@Entity
@NoArgsConstructor
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name="account_no")
    private String accountNo;
    @Column(name="account_name")
    private String accountName;
    @Column(name = "profile")
    private String profile;
    @Column(name="pin")
    private  int pin;
    @Column(name="password")
    private String  password;
    @Column(name="phone_number")
    private String phoneNumber;
//    @OneToOne
//    @JoinColumn(name = "account_type")
    //private AccountType accountType1;

}
