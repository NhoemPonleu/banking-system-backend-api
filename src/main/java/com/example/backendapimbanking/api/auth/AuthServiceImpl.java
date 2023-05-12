package com.example.backendapimbanking.api.auth;

import com.example.backendapimbanking.api.auth.web.RegisterDto;
import com.example.backendapimbanking.api.user.User;
import com.example.backendapimbanking.api.user.UserMapStruct;
import com.example.backendapimbanking.api.user.UserMapper;
import com.example.backendapimbanking.util.MailUtil;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthServiceImpl implements AuthService {
    private final AuthMapper authMapper;
    private final UserMapStruct userMapStruct;
    private final PasswordEncoder passwordEncoder;
    private final MailUtil mailUtil;
    private final UserMapper userMapper;
    @Value("${spring.mail.username}")
    private String appMail;
    @Override
    public void register(RegisterDto registerDto) {
        User user=userMapStruct.registerDtoToUser(registerDto);
        log.warn("User {}",user.getEmail());
        if(userMapper.selectByEmail(user.getEmail()).isPresent()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"User email has been register");

        }else {
            user.setIsVerified(false);
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            authMapper.register(user);
        }

    }

    @Override
    public void verified(String email) {
       User user=authMapper.selectByEmail(email)
                .orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,"email Not found"));
        //prepate template
        user.setVerifiedCode(UUID.randomUUID().toString());
        //prepare email infomation
        //sender email
        MailUtil.Meta<?>mail=MailUtil.Meta.builder()
                .to(email)
                .from(appMail)
                .subject("Account Verification")
                .templatesUrl("auth/verify")
                .data(user)
                .build();
        try {
            mailUtil.send(mail);
        } catch (MessagingException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,e.getMessage());
        }

    }
}
