package com.example.backendapimbanking.api.auth.web;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

@Builder
public record RegisterDto(
       @Email @NotBlank(message = "email is valid") String email,
       @NotBlank(message = "password is valid") String password,
       @NotBlank(message = "please confirm your password") String confirmPassword) {
}
