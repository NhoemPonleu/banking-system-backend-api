package com.example.backendapimbanking.api.user;

import com.example.backendapimbanking.api.user.web.CreateUserDto;
import com.example.backendapimbanking.api.user.web.UserDto;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashSet;
import java.util.Set;


@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserMapper userMapper;
    private final UserMapStruct userMapStruct;

    @Override
    public UserDto createNewUser(CreateUserDto createUserDto) {
        User user = userMapStruct.toUser(createUserDto);
        userMapper.insert(user);

        return this.findUserById(user.getId());
    }

    @Override
    public UserDto findUserById(Integer id) {
        User find = userMapper.selectById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        String.format("User with %d is not found", id)));
        return userMapStruct.toUserDto(find);
    }

    @Override
    public Integer deleteUserById(Integer id) {
        boolean byId = userMapper.existById(id);
        System.out.println("found" + byId);
        if (byId) {
            userMapper.deleteById(id);
            return id;
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND
                , String.format("User with %d id not found", id));
    }

    @Override
    public Integer updateIsDeletedStatus(Integer id, boolean status) {
        boolean isExisted = userMapper.existById(id);
        if (isExisted) {
            userMapper.updateIsDeletedById(id, status);
            return id;
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND
                , String.format("User with %d id not found", id));
    }

    @Override
    public Set<PageInfo<UserDto>> getPagination(int page, int limit, String name) {
     PageInfo<User> pageDto = PageHelper.startPage(page, limit)
                .doSelectPageInfo(() -> userMapper.select(name));
        Set<PageInfo<User>> setUser=new HashSet<>();
        setUser.add(pageDto);
        return userMapStruct.userPageInfoToDtoPageInfo(setUser);
    }

    @Override
    public UserDto finduserByStudentCardId(String studentCardId) {

        User find = userMapper.selectByStudentCardId(studentCardId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        String.format("StudentCardId with %s is not found", studentCardId)));
        return userMapStruct.toUserDto(find);
    }
}
