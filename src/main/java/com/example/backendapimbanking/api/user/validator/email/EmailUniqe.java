package com.example.backendapimbanking.api.user.validator.email;

import com.example.backendapimbanking.api.user.validator.email.EmailContraintValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
@Constraint(validatedBy = EmailContraintValidator.class)
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD,ElementType.METHOD})
public @interface EmailUniqe {

    String message() default "email Already exist";
    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };


}
