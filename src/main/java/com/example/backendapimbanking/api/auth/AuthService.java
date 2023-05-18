package com.example.backendapimbanking.api.auth;

import com.example.backendapimbanking.api.auth.web.AuthDto;
import com.example.backendapimbanking.api.auth.web.LogInDto;
import com.example.backendapimbanking.api.auth.web.RegisterDto;

public interface AuthService {
    void register(RegisterDto registerDto);
    void verified(String email);
    void checkVerify(String email,String verifiedCode);
    AuthDto loginUser(LogInDto loginDto);
}
