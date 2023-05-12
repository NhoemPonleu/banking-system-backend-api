package com.example.backendapimbanking.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig  {
    @Bean
    public PasswordEncoder encoder(){
        return new BCryptPasswordEncoder();
    }
    //define in memory users
    @Bean
    public UserDetailsService userDetailsService() {
        UserDetailsManager userDetailsManager=new InMemoryUserDetailsManager();

        UserDetails user = User.builder()
                .username("admin")
                .password(encoder().encode("123"))
                .roles("ADMIN")
                .build();
        UserDetails goldUser=User.builder()
                .username("ponleu")
                //.password("{noop}1234")
                .password(encoder().encode("1234"))
                .roles("ACCOUNT")
                .build();
        userDetailsManager.createUser(user);
        userDetailsManager.createUser(goldUser);
        return userDetailsManager;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable);
        http.authorizeHttpRequests(
                request->{
                    request.requestMatchers("api/v1/users/**")
                            .hasRole("ADMIN");
                    request.requestMatchers("api/v1/account_types/**").hasRole("ACCOUNT");
                    request.anyRequest().permitAll();
                });


       http.httpBasic();
//        http.authorizeHttpRequests()
//                .requestMatchers("/**")
//                .hasRole("USER")
//                .and()
               http.sessionManagement()
                       .sessionCreationPolicy(SessionCreationPolicy.STATELESS);//http state less

        return http.build();
    }
}
