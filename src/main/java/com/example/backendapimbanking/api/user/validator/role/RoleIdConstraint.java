package com.example.backendapimbanking.api.user.validator.role;

import com.example.backendapimbanking.api.user.validator.role.RoleIdConstraintValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = RoleIdConstraintValidator.class)
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD,ElementType.METHOD})
public @interface RoleIdConstraint {
    String message() default "roleIds is Not  exist";
    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };

}
