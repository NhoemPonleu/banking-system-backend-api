package com.example.backendapimbanking.api.auth;

import com.example.backendapimbanking.api.auth.web.AuthDto;
import com.example.backendapimbanking.api.auth.web.LogInDto;
import com.example.backendapimbanking.api.auth.web.RegisterDto;
import com.example.backendapimbanking.api.user.User;
import com.example.backendapimbanking.api.user.UserMapStruct;
import com.example.backendapimbanking.util.MailUtil;
import jakarta.mail.MessagingException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Base64;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthServiceImpl implements AuthService {
    private final AuthMapper authMapper;
    private final UserMapStruct userMapStruct;
    private PasswordEncoder encoder;

    private final MailUtil mailUtil;
    private final DaoAuthenticationProvider daoAuthenticationProvider;

    @Value("${spring.mail.username}")
    private String appMail;

    @Autowired
    void setEncoder(PasswordEncoder encoder) {
        this.encoder = encoder;
    }


    @Transactional
    @Override
    public void register(RegisterDto registerDto) {
        User user = userMapStruct.registerDtoToUser(registerDto);
        log.info("User: {}", user.getEmail());
        user.setPassword(encoder.encode(registerDto.password()));
        user.setIsVerified(false);
        authMapper.register(user);
        for (Integer roleId : registerDto.roleIds()) {
            authMapper.createUserRole(user.getId(), roleId);
        }


    }


    @Override
    public void verified(String email) {
        User user = authMapper.selectByEmail(email)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Email not found."));

        String verifiedCode = UUID.randomUUID().toString();

        if (authMapper.updateVerifiedCode(email, verifiedCode)) {
            user.setVerifiedCode(verifiedCode);
        } else {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "User cannot be verify.");
        }


        MailUtil.Meta<?> meta = MailUtil.Meta.builder()
                .to(email)
                .from(appMail)
                .subject("Account Verify")
                .templatesUrl("auth/verify")
                .data(user)
                .build();
        try {
            mailUtil.send(meta);
        } catch (MessagingException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    @Override
    public void checkVerify(String email, String code) {
        User user = authMapper.selectByEmailAndVerifiedCode(email, code)

                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Email not found."));
        System.out.println("User" + user.getEmail());
        if (!user.getIsVerified()) {
            authMapper.updateIsVerifyStatus(email, code);
        }
    }

    @Override
    public AuthDto loginUser(LogInDto loginDto) {
        Authentication authentication = new UsernamePasswordAuthenticationToken(loginDto.email(), loginDto.password());

        authentication = daoAuthenticationProvider.authenticate(authentication);

        log.info("Authentication: {}", authentication);
        log.info("Authentication: {}", authentication.getName());
        log.info("Authentication: {}", authentication.getCredentials());

        // Logic on basic authorization header
        String basicAuthFormat = authentication.getName() + ":" + authentication.getCredentials();
        String encoding = Base64.getEncoder().encodeToString(basicAuthFormat.getBytes());

        log.info("Basic {}", encoding);

        return new AuthDto(String.format("Basic: %s", encoding));
    }
}
