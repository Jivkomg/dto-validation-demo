package com.dto.demo.validation.annotations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import com.dto.demo.validation.validators.UserIdPathVariableExistsValidator;

@Documented
@Target({ ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = { UserIdPathVariableExistsValidator.class })
public @interface UserIdPathVariableExists {
    String message() default "Entity with the given id not found in DB!";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
