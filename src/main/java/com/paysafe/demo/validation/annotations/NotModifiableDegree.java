package com.paysafe.demo.validation.annotations;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.CONSTRUCTOR;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.ElementType.TYPE_USE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import com.paysafe.demo.validation.validators.NotModifiableDegreeValidator;

/**
 * Validates that field {@code dependFieldName} is not empty array if
 * field {@code fieldName} has value {@code fieldValue}.
 **/
@Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE })
@Retention(RUNTIME)
@Constraint(validatedBy = NotModifiableDegreeValidator.class)
@Documented
public @interface NotModifiableDegree {
    String message() default "{ Degree cannot be modified from DOCTORATE to MASTERS or BACHELORS }";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}