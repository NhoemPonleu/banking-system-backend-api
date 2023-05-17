package com.example.backendapimbanking.api.auth.web;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Role implements GrantedAuthority {
    private Integer id;
    private String name;


    @Override
    public String getAuthority() {
        return "ROLE_"+ name;
    }
}
