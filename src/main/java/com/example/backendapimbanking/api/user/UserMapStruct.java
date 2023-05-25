package com.example.backendapimbanking.api.user;

import com.example.backendapimbanking.api.auth.web.RegisterDto;
import com.example.backendapimbanking.api.user.web.CreateUserDto;
import com.example.backendapimbanking.api.user.web.UserDto;
import com.github.pagehelper.PageInfo;
import org.mapstruct.Mapper;

import java.util.Set;

@Mapper(componentModel = "spring")
public interface UserMapStruct {
    User toUser(CreateUserDto createUserDto);

    UserDto toUserDto(User user);

    User toUserFDto(UserDto userDto);

    Set<PageInfo<UserDto>> userPageInfoToDtoPageInfo(Set<PageInfo<User>> userPageInfo);

    User registerDtoToUser(RegisterDto registerDto);
}
