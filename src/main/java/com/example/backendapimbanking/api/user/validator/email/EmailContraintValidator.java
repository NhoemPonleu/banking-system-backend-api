package com.example.backendapimbanking.api.user.validator.email;

import com.example.backendapimbanking.api.user.UserMapper;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class EmailContraintValidator implements ConstraintValidator<EmailUniqe,String> {
    private final UserMapper userMapper;
    @Override
    public boolean isValid(String email, ConstraintValidatorContext context) {
        return !userMapper.existByEmail(email);
    }
}
