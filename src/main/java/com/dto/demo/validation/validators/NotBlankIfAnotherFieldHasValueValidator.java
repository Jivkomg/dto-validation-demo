package com.dto.demo.validation.validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.BeanWrapperImpl;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import com.dto.demo.validation.annotations.NotBlankIfAnotherFieldHasValue;

/**
 * Implementation of {@link NotBlankIfAnotherFieldHasValue} validator.
 **/
public class NotBlankIfAnotherFieldHasValueValidator
    implements ConstraintValidator<NotBlankIfAnotherFieldHasValue, Object> {

    private String fieldName;
    private String expectedFieldValue;
    private String dependFieldName;

    @Override
    public void initialize(NotBlankIfAnotherFieldHasValue annotation) {
        fieldName = annotation.fieldName();
        expectedFieldValue = annotation.fieldValue();
        dependFieldName = annotation.dependFieldName();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext ctx) {
        if (ObjectUtils.isEmpty(value)) {
            return true;
        }

        BeanWrapperImpl wrapper = new BeanWrapperImpl(value);
        Object fieldValue = wrapper.getPropertyValue(fieldName);
        Object dependFieldValue = wrapper.getPropertyValue(dependFieldName);

        if (expectedFieldValue.equals(fieldValue) && StringUtils.isEmpty(dependFieldValue)) {
            ctx.disableDefaultConstraintViolation();
            ctx.buildConstraintViolationWithTemplate(ctx.getDefaultConstraintMessageTemplate()).addConstraintViolation();
            return false;
        }

        return true;
    }
}