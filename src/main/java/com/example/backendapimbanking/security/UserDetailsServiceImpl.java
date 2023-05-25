package com.example.backendapimbanking.security;

import com.example.backendapimbanking.api.auth.AuthMapper;
import com.example.backendapimbanking.api.auth.web.Role;
import com.example.backendapimbanking.api.user.Authority;
import com.example.backendapimbanking.api.user.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@RequiredArgsConstructor
@Service
@Slf4j
public class UserDetailsServiceImpl implements UserDetailsService {
    private final AuthMapper authMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = authMapper.loadUserByUsername(username)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User is not valid."));
        log.info("User:{}",user.getRoles());
        for(Role role:user.getRoles()){
            for(Authority authority:role.getAuthorities()){
                System.out.println(authority.getName());
            }
        }

        CustomUserDetail customUserDetail = new CustomUserDetail();
        customUserDetail.setUser(user);
        log.info("Custom User Details:{}",customUserDetail.getAuthorities());

        return customUserDetail;
    }
}
