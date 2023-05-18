package com.example.backendapimbanking.api.auth.web;

import com.example.backendapimbanking.api.user.validator.password.Password;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record LogInDto(@Email
                       @NotBlank String email,
                       @NotBlank
                       @Password String password) {
}
