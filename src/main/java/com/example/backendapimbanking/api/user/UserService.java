package com.example.backendapimbanking.api.user;

import com.example.backendapimbanking.api.user.web.CreateUserDto;
import com.example.backendapimbanking.api.user.web.UserDto;
import com.github.pagehelper.PageInfo;

import java.util.Set;

public interface UserService {
    UserDto createNewUser(CreateUserDto createUserDto);
    UserDto findUserById(Integer id);
    Integer deleteUserById(Integer id);
    Integer updateIsDeletedStatus(Integer id,boolean status);
    Set<PageInfo<UserDto>>getPagination(int page, int limit, String name);
    UserDto finduserByStudentCardId(String studentCardId);

}
