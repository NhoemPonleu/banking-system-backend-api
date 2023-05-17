package com.example.backendapimbanking.security;

import com.example.backendapimbanking.api.auth.AuthMapper;
import com.example.backendapimbanking.api.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
@RequiredArgsConstructor
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private final AuthMapper authMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = authMapper.loadUserByUsername(username).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"User is not valid."));

        CustomUserDetail customUserDetail = new CustomUserDetail();
        customUserDetail.setUser(user);

        return customUserDetail;
    }
}
