package com.dto.demo.validation.validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import com.dto.demo.repositories.UserRepository;
import com.dto.demo.validation.annotations.UserIdPathVariableExists;

public class UserIdPathVariableExistsValidator implements ConstraintValidator<UserIdPathVariableExists, String> {
    @Autowired
    private UserRepository userRepository;

    @Override public boolean isValid(String value, ConstraintValidatorContext context) {
        if (!StringUtils.isEmpty(value)) {
            return userRepository.findById(value).isPresent();
        }
        return false;
    }
}
