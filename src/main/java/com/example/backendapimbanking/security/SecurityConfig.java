package com.example.backendapimbanking.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig  {
    private final PasswordEncoder encoder;
    private final UserDetailsServiceImpl userDetailsService;
    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider(){
        DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
        auth.setUserDetailsService(userDetailsService);
        auth.setPasswordEncoder(encoder);
        return auth;
    }

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.csrf(AbstractHttpConfigurer::disable);
//        http.csrf(token -> token.disable());

        http.authorizeHttpRequests(request -> {
            //Authorize URL mapping
            request.requestMatchers("/api/v1/auth/**").permitAll();
            request.requestMatchers(HttpMethod.GET,"/api/v1/users/**").hasRole("ADMIN");
            request.requestMatchers(HttpMethod.POST,"/api/v1/users/**")
                    .hasAnyRole("CUSTOMER","SYSTEM");
            request.anyRequest().authenticated();
        });


        //Security mechanism
        http.httpBasic();


        http.sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS); // make security stateless
//                .formLog/in(); // using for normal web without api
        return http.build();
    }
}
