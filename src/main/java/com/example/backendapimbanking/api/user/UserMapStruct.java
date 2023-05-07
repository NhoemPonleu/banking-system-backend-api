package com.example.backendapimbanking.api.user;

import com.example.backendapimbanking.api.user.web.CreateUserDto;
import com.example.backendapimbanking.api.user.web.UserDto;
import com.github.pagehelper.PageInfo;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapStruct {
    User toUser(CreateUserDto createUserDto);
    UserDto toUserDto(User user);
    User toUserFDto(UserDto userDto);
    PageInfo<UserDto>userPageInfoToDtoPageInfo(PageInfo<User>userPageInfo);

}
