package com.example.backendapimbanking.api.user.validator.password;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import jakarta.validation.constraints.NotBlank;

import java.lang.annotation.*;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Constraint(validatedBy = PasswordMatchContraintValidator.class)
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
public @interface PasswordMatch {
    String message() default "Password is not match ";
    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
    String password() default "password";
    String confirmPassword() default "confirmPassword";
    @Target({ TYPE })
    @Retention(RUNTIME)
    @Documented
    @interface List {
        NotBlank[] value();
    }
}
