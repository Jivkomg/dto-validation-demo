package com.paysafe.demo.validation.validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import com.paysafe.demo.repositories.UserRepository;
import com.paysafe.demo.validation.annotations.UserIdPathVariableExists;

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
