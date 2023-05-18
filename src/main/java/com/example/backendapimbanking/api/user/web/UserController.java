package com.example.backendapimbanking.api.user.web;

import com.example.backendapimbanking.api.user.UserService;
import com.example.backendapimbanking.base.BaseRest;
import com.github.pagehelper.PageInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.NoSuchElementException;
import java.util.Set;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor

public class UserController {
    private final UserService userService;
    @PostMapping
    public BaseRest<?> createUser(@RequestBody CreateUserDto createUserDto) {
        UserDto create = userService.createNewUser(createUserDto);
        return BaseRest.builder().code(HttpStatus.OK.value())
                .status(true)
                .messages("created users")
                .data(create)
                .build();
    }
    @GetMapping("/{identitfier}")
    public BaseRest<?> findUser(@PathVariable("identitfier") String identitfier) {
        UserDto userDto;
        try {
            Integer id = Integer.parseInt(identitfier);
            userDto = userService.findUserById(id);

        } catch (NoSuchElementException | NumberFormatException e) {
           userDto = userService.finduserByStudentCardId(identitfier);
            System.out.println("user ");
        }
        return BaseRest.builder()
                .code(HttpStatus.OK.value())
                .status(true)
                .messages("User has been found")
                .data(userDto)
                .build();
    }

    @DeleteMapping("/{id}")
    public BaseRest<?> deleteUser(@PathVariable Integer id) {
        Integer findId = userService.deleteUserById(id);
        return BaseRest.builder().code(HttpStatus.OK.value())
                .status(true)
                .messages("User has been deleted")
                .data(findId)
                .timeStamp(LocalDateTime.now())
                .build();
    }

    @PutMapping("/{id}")
    public BaseRest<?> idUpdatedDeleted(@PathVariable Integer id, @RequestBody IsDeletedDto isDeletedDto) {
        Integer isDeletedStatus = userService.updateIsDeletedStatus(id, isDeletedDto.status());
        return BaseRest.builder().code(HttpStatus.OK.value())
                .status(true)
                .messages("User has been deleted")
                .data(isDeletedStatus)
                .timeStamp(LocalDateTime.now())
                .build();
    }

    @GetMapping
    public BaseRest<?> findAllUser(@RequestParam(name = "page", required = false, defaultValue = "1") int pages,
                                   @RequestParam(name = "limit", required = false, defaultValue = "20") int limit,
                                   @RequestParam(name = "name", required = false, defaultValue = "") String name) {
        Set<PageInfo<UserDto>> getPage = userService.getPagination(pages, limit, name);
        return BaseRest.builder().code(HttpStatus.OK.value())
                .status(true)
                .messages("User has been deleted")
                .data(getPage)
                .timeStamp(LocalDateTime.now())
                .build();
    }
}

