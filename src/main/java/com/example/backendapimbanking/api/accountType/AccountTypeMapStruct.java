package com.example.backendapimbanking.api.accountType;

import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AccountTypeMapStruct {
    List<AccountTypeDto>toDto(List<AccountType>accountTypes);
    AccountType toEntity(AccountTypeDto accountTypeDto);
    AccountTypeDto toDto(AccountType accountType);
}
