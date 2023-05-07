package com.example.backendapimbanking.api.accountType;

import java.util.List;

public interface AccountTypeService {
    List<AccountTypeDto>findAll();
    AccountTypeDto findById(Integer id);
    AccountTypeDto createNewAccountType(AccountTypeDto accountTypeDto);
    AccountTypeDto updateAccountTypeById(Integer id ,AccountTypeDto accountTypeDtot);

}
