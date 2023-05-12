package com.example.backendapimbanking.api.auth;

import com.example.backendapimbanking.api.auth.web.RegisterDto;

public interface AuthService {
    void register(RegisterDto registerDto);
    void verified(String email);
}
