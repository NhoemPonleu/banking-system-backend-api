package com.example.backendapimbanking.api.auth.web;

import com.example.backendapimbanking.api.user.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AuthorMapStruct {
    User toEntityUser(ResponseVerificationDto responseVerificationDto);
}
