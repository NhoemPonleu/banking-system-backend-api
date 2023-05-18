package com.example.backendapimbanking.api.auth.web;

import com.example.backendapimbanking.api.user.validator.email.EmailUniqe;
import com.example.backendapimbanking.api.user.validator.password.Password;
import com.example.backendapimbanking.api.user.validator.role.RoleIdConstraint;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

import java.util.List;

@Builder
public record RegisterDto(
        @EmailUniqe @Email @NotBlank(message = "email is valid") String email,
        @NotBlank(message = "password required")
        @Password
        String password,
        @NotNull(message = "The field roles is required.")
        @RoleIdConstraint
        List<Integer> roleIds,
        @NotBlank(message = "please confirm password")
        @Password
        String confirmPassword) {
}
