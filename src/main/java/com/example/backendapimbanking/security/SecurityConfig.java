package com.example.backendapimbanking.security;

import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.access.expression.SecurityExpressionHandler;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPublicKey;
import java.util.UUID;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableMethodSecurity
public class SecurityConfig {
    private final PasswordEncoder encoder;
    private final UserDetailsServiceImpl userDetailsService;
    private final CustomAuthenticationEntryPoint customAuthenticationEntryPoint;
    private final JwtFailureEvents jwtFailureEvents;

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
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
//            request.requestMatchers(HttpMethod.GET, "/api/v1/users/**").hasAnyAuthority("SCOPE_ROLE_ADMIN");
//            request.requestMatchers(HttpMethod.POST, "/api/v1/users/**")
//                    .hasAnyAuthority("SCOPE_ROLE_CUSTOMER", "SCOPE_ROLE_SYSTEM");
//            request.requestMatchers(HttpMethod.GET, "/api/v1/users/**").hasAnyAuthority("SCOPE_READ", "SCOPE_FULL_CONTROL");
//            request.requestMatchers(HttpMethod.POST, "/api/v1/users/**").hasAnyAuthority("SCOPE_WRITE", "SCOPE_FULL_CONTROL");
//            request.requestMatchers(HttpMethod.PUT, "/api/v1/users/**").hasAnyAuthority("SCOPE_UPDATE", "SCOPE_FULL_CONTROL");
//            request.requestMatchers(HttpMethod.DELETE, "/api/v1/users/**").hasAnyAuthority("SCOPE_DELETE", "SCOPE_FULL_CONTROL");
             request.requestMatchers(HttpMethod.GET,"/api/v1/users/**").hasAnyAuthority("SCOPE_user:read");
             request.requestMatchers(HttpMethod.POST,"/api/v1/users/**").hasAnyAuthority("SCOPE_user:write");
             request.requestMatchers(HttpMethod.PUT,"/api/v1/users/**").hasAnyAuthority("SCOPE_user:delete");
             request.requestMatchers(HttpMethod.DELETE,"/api/v1/users/**").hasAnyAuthority("SCOPE_user:update");
             request.requestMatchers(HttpMethod.GET,"/api/v1/user/**").hasAnyAuthority("SCOPE_account:read");
             request.anyRequest().authenticated();
        });


        //Security mechanism
        //   http.httpBasic();
        http.oauth2ResourceServer(oauth2 -> oauth2.jwt());


        http.sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        //excepttion status code
        http.exceptionHandling()
                .authenticationEntryPoint(customAuthenticationEntryPoint);

   //http.exceptionHandling().defaultAccessDeniedHandlerFor(jwtFailureEvents);

        // make security stateless
//                .formLog/in(); // using for normal web without api
        return http.build();
    }

    @Bean
    public KeyPair keyPair() throws NoSuchAlgorithmException {
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(2048);
        return keyPairGenerator.generateKeyPair();
    }

    @Bean
    public RSAKey rsaKey(KeyPair keyPair) {

        return new RSAKey.Builder((RSAPublicKey) keyPair.getPublic())
                .privateKey(keyPair.getPrivate())
                .keyID(UUID.randomUUID().toString())
                .build();
    }

    @Bean
    public JwtDecoder jwtDecoder(RSAKey rsaKey) throws JOSEException {
        return NimbusJwtDecoder.withPublicKey(rsaKey.toRSAPublicKey()).build();


    }

    //config Jwt Encoder
    @Bean
    public JwtEncoder jwtEncoder(JWKSource<SecurityContext> jwkSource) {
        return new NimbusJwtEncoder(jwkSource);
    }

    @Bean
    public JWKSource<SecurityContext> jwkSource(RSAKey rsaKey) {
        JWKSet jwkSet = new JWKSet(rsaKey);
        return (jwkSelector, context) -> jwkSelector.select(jwkSet);
    }
//    @Bean
//    public JwtDecoder jwtDecoder(RSAKey rsaKey){
//        return null;
//    }

}
