package com.example.backendapimbanking.api.user;

import com.example.backendapimbanking.api.auth.web.RegisterDto;
import com.example.backendapimbanking.api.user.web.CreateUserDto;
import com.example.backendapimbanking.api.user.web.UserDto;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.mapstruct.Mapper;

import java.util.Optional;

@Mapper(componentModel = "spring")
public interface UserMapStruct {
    User toUser(CreateUserDto createUserDto);
    UserDto toUserDto(User user);
    User toUserFDto(UserDto userDto);
    PageInfo<UserDto>userPageInfoToDtoPageInfo(PageInfo<User>userPageInfo);
    User registerDtoToUser(RegisterDto registerDto);


}
