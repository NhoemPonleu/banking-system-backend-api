package com.example.backendapimbanking.api.user.validator.password;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;
@Constraint(validatedBy = PasswordConstrainValidator.class)
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD,ElementType.METHOD})
public @interface Password {
    String message() default "Password is not confirm ";
    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };

}
