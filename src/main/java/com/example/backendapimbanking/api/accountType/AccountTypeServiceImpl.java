package com.example.backendapimbanking.api.accountType;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
@Service
@RequiredArgsConstructor
public class AccountTypeServiceImpl implements AccountTypeService{
    private final AccountTypeMapper accountTypeMapper;
    private final AccountTypeMapStruct accountTypeMapStruct;
    @Override
    public List<AccountTypeDto> findAll() {
      List<AccountType>findAll=accountTypeMapper.select();
//      List<AccountTypeDto>accountTypeDtos=findAll.stream()
//              .map(a->new AccountTypeDto(a.getName())).toList();
        return accountTypeMapStruct.toDto(findAll);
    }

    @Override
    public AccountTypeDto findById(Integer id) {
      AccountType findById=accountTypeMapper.selectById(id).orElseThrow(()->new
                 ResponseStatusException(HttpStatus.NOT_FOUND,
                 String.format("AccountType with %d =id",id)));
        return accountTypeMapStruct.toDto(findById);
    }

    @Override
    public AccountTypeDto createNewAccountType(AccountTypeDto accountTypeDto) {
      AccountType create =accountTypeMapStruct.toEntity(accountTypeDto);
      accountTypeMapper.insertAccount(create);

        return this.findById(create.getId());
    }

    @Override
    public AccountTypeDto updateAccountTypeById(Integer id, AccountTypeDto accountTypeDto) {
       AccountType accountType= accountTypeMapStruct.toEntity(accountTypeDto);
        accountType.setId(id);
       accountTypeMapper.updateAccountType(accountType);

        return accountTypeMapStruct.toDto(accountType);
    }
}
