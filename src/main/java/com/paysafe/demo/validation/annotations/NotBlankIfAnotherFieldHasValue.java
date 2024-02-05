package com.paysafe.demo.validation.annotations;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import com.paysafe.demo.validation.validators.NotBlankIfAnotherFieldHasValueValidator;

/**
 * Validates that field {@code dependFieldName} is not null if
 * field {@code fieldName} has value {@code fieldValue}.
 **/
@Target({TYPE, ANNOTATION_TYPE})
@Retention(RUNTIME)
@Repeatable(NotBlankIfAnotherFieldHasValue.List.class)
@Constraint(validatedBy = NotBlankIfAnotherFieldHasValueValidator.class)
@Documented
public @interface NotBlankIfAnotherFieldHasValue {

    String fieldName();
    String fieldValue();
    String dependFieldName();

    String message() default "{NotBlankIfAnotherFieldHasValue.message}";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

    @Target({TYPE, ANNOTATION_TYPE})
    @Retention(RUNTIME)
    @Documented
    @interface List {
        NotBlankIfAnotherFieldHasValue[] value();
    }
}