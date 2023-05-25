package com.example.backendapimbanking.api.account;

import org.apache.ibatis.jdbc.SQL;

public class AccountProvider {
    public String insertAccountSql(){
        return new SQL(){{
            INSERT_INTO("account");
            VALUES("acount_no","#{ac.accountNo}");
            VALUES("account_name","#{ac.accountName}");
            VALUES("profile","#{ac.profile}");
            VALUES("pin","#{ac.pin}");
            VALUES("phone_number","#{ac.phoneNumber}");
            VALUES("transfer_limit","#{ac.transferLimit}");

        }}.toString();
    }
}
