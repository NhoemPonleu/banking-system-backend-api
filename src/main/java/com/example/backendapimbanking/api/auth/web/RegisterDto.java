package com.example.backendapimbanking.api.auth.web;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

import java.util.List;

@Builder
public record RegisterDto(
       @Email @NotBlank(message = "email is valid") String email,
       @NotBlank(message = "password is valid") String password,
       @NotNull(message = "The field roles is required.")
       List<Integer> roleIds,
       @NotBlank(message = "please confirm your password") String confirmPassword) {
}
