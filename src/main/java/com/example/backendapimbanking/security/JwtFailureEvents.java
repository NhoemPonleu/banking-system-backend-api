package com.example.backendapimbanking.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.event.AuthenticationFailureBadCredentialsEvent;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.server.resource.authentication.BearerTokenAuthenticationToken;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtFailureEvents implements AuthenticationFailureHandler {
    private ObjectMapper objectMapper = new ObjectMapper();


    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
      //  HttpServletRequest request,
      //  HttpServletResponse response,
      //  AuthenticationException exception)
      //throws IOException, ServletException {

            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            Map<String, Object> data = new HashMap<>();
            data.put(
                    "timestamp",
                    Calendar.getInstance().getTime());
            data.put(
                    "exception",
                    exception.getMessage());

            response.getOutputStream()
                    .println(objectMapper.writeValueAsString(data));
    }
}