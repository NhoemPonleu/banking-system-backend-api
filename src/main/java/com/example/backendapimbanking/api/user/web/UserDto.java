package com.example.backendapimbanking.api.user.web;

import com.example.backendapimbanking.api.auth.web.Role;
import lombok.Builder;

import java.util.List;

@Builder
public record UserDto(
        String name,
        String gender,
        String studentCardId,
        Boolean isStudent,
        List<Role> roles
) {
}
