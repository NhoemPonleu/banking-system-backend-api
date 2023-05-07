package com.example.backendapimbanking.api.accountType;

import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Mapper
@Repository
public interface AccountTypeMapper {
    @SelectProvider(type = AccountTypeProvider.class,method = "selectSql")
    List<AccountType> select();
    @SelectProvider(type = AccountTypeProvider.class,method = "selectByIdSql")
    Optional<AccountType>selectById(@Param("id" ) Integer id);
    @InsertProvider(type = AccountTypeProvider.class,method = "insertSql")
    @Options(useGeneratedKeys = true , keyColumn = "id", keyProperty = "id")
    void insertAccount(@Param("a") AccountType accountType);
    @UpdateProvider(type = AccountTypeProvider.class,method = "buildUpdatAccountType")
    void updateAccountType(@Param("ac") AccountType accountType);


}
