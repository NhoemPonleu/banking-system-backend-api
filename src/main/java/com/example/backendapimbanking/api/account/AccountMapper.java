package com.example.backendapimbanking.api.account;

import com.example.backendapimbanking.api.accountType.AccountTypeService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
@Mapper
public interface AccountMapper {
    AccountMapper INSTANCE= Mappers.getMapper(AccountMapper.class);
    //@Mapping(target = "accountType",source = "accountDto.accountTypeId")
    Account toEntity(AccountDto accountDto);
   // @Mapping(target = "accountTypeId" ,source = "accountType.id")
    AccountDto toDto(Account account);

}
