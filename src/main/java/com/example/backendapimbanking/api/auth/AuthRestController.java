package com.example.backendapimbanking.api.auth;

import com.example.backendapimbanking.api.auth.web.AuthDto;
import com.example.backendapimbanking.api.auth.web.LogInDto;
import com.example.backendapimbanking.api.auth.web.RegisterDto;
import com.example.backendapimbanking.base.BaseRest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@Slf4j
@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthRestController {
    private final AuthService authService;

    @PostMapping("/register")
    public BaseRest<?> register(@Valid @RequestBody RegisterDto registerDto) {
        authService.register(registerDto);
        return BaseRest.builder()
                .status(true)
                .messages("Success")
                .timeStamp(LocalDateTime.now())
                .data(registerDto.email())
                .build();
    }

    @PostMapping("/verify")
    public BaseRest<?> verifiedEmail(@RequestParam String email) {
        authService.verified(email);
        return BaseRest.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .messages("Success")
                .timeStamp(LocalDateTime.now())
                .data(email)
                .build();
    }

    @GetMapping("/check-verify")
    public BaseRest<?> checkVerifY(@RequestParam String email, @RequestParam String verifiedCode) {
        log.warn("email {}", email);
        log.warn("Verify {}", verifiedCode);
        authService.checkVerify(email, verifiedCode);
        return BaseRest.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .messages("Success Verification")
                .timeStamp(LocalDateTime.now())
                .data(email)
                .build();
    }
    @PostMapping("/login")
    public BaseRest<?>loginUserByPass(@Valid @RequestBody LogInDto loginDto){
      AuthDto authDto= authService.loginUser(loginDto);
        return BaseRest.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .messages("Success Login")
                .timeStamp(LocalDateTime.now())
                .data(authDto)
                .build();
    }
}
