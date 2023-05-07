package com.example.backendapimbanking.api.accountType;

import com.example.backendapimbanking.base.BaseRest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.net.http.HttpClient;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/account-types")
public class AccountTypeController {
    private final AccountTypeService accountTypeService;
    @GetMapping
  public BaseRest<?>listAccountType(){
      List<AccountTypeDto> accountTypeDtoList=accountTypeService.findAll();
      return BaseRest.builder().code(HttpStatus.OK.value())
              .status(true).timeStamp(LocalDateTime.now())
              .data(accountTypeDtoList)
              .messages("sucessfull").build();
  }
    @GetMapping("/{id}")
    public BaseRest<?>findById(@PathVariable Integer id){
       AccountTypeDto getById=accountTypeService.findById(id);
        return BaseRest.builder().code(HttpStatus.OK.value())
                .status(true).timeStamp(LocalDateTime.now())
                .data(getById)
                .messages("sucessfull").build();
    }
    @PostMapping
    public BaseRest<?>createNewAccountType(@Valid @RequestBody AccountTypeDto accountTypeDto){
          AccountTypeDto accountTypeDto1=accountTypeService.createNewAccountType(accountTypeDto);
        return BaseRest.builder().code(HttpStatus.OK.value())
                .status(true).timeStamp(LocalDateTime.now())
                .data(accountTypeDto1)
                .messages("sucessfull").build();
    }
    @PutMapping("/{id}")
    public BaseRest<?>updateAccountType(@PathVariable Integer id,@Valid @RequestBody AccountTypeDto accountTypeDto){
        AccountTypeDto accountTypeDto1=accountTypeService.updateAccountTypeById(id,accountTypeDto);
        return BaseRest.builder()
                .code(HttpStatus.OK.value())
                .status(true).timeStamp(LocalDateTime.now())
                .data(accountTypeDto1)
                .messages("updated sucesfully")
                .build();
    }
}
