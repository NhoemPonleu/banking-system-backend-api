package com.example.backendapimbanking.api.user;

import com.example.backendapimbanking.api.accountType.AccountType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class User {
    private Integer id;
    private  String name;
    private String gender;
    private String oneSignalId;
    private String studentCardId;
    private Boolean isStudent;
    private Boolean isDeleted;
    private  AccountType type;
    private String email;
    private String password;
    private Boolean isVerified;
    private String verifiedCode;

}
