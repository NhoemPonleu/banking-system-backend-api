package com.example.backendapimbanking.security;

import com.example.backendapimbanking.api.auth.web.Role;
import com.example.backendapimbanking.api.user.Authority;
import com.example.backendapimbanking.api.user.User;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomUserDetail implements UserDetails {
    private User user;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> simpleGrantedAuthorityList=new ArrayList<>();
        for(Role role:user.getRoles()){
            simpleGrantedAuthorityList.add(new SimpleGrantedAuthority(role.getAuthority()));
            for(Authority authority: role.getAuthorities()){
                simpleGrantedAuthorityList.add(new SimpleGrantedAuthority(authority.getName()));
            }
        }

        return simpleGrantedAuthorityList;
    }
    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
