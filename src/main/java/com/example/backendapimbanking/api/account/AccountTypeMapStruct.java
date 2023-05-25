package com.example.backendapimbanking.api.account;

import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface AccountTypeMapStruct {
    @InsertProvider(type = AccountProvider.class,method = "insertAccountSql")
    void insertAccount(AccountDto accountDto);

}
