package com.example.backendapimbanking.api.auth;

import com.example.backendapimbanking.api.auth.web.AuthorMapStruct;
import com.example.backendapimbanking.api.auth.web.RegisterDto;
import com.example.backendapimbanking.base.BaseRest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthRestController {
    private final AuthService authService;
    @PostMapping("/register")
    public BaseRest<?>register(@Valid @RequestBody RegisterDto registerDto){
         authService.register(registerDto);
        return BaseRest.builder()
                .status(true)
                .messages("Success")
                .timeStamp(LocalDateTime.now())
                .data(registerDto.email())
                .build();
    }
    @PostMapping("/verify")
    public BaseRest<?>verifiedEmail(@RequestParam String email){
        authService.verified(email);
        return  BaseRest.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .messages("Success")
                .timeStamp(LocalDateTime.now())
                .data(email)
                .build();
    }
}
