package com.dto.demo.validation.validators;

import java.util.Optional;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.dto.demo.dtos.UserDto;
import com.dto.demo.models.User;
import com.dto.demo.repositories.UserRepository;
import com.dto.demo.validation.annotations.NotModifiableDegree;

public class NotModifiableDegreeValidator implements ConstraintValidator<NotModifiableDegree, UserDto> {

    private UserRepository userRepository;

    @Autowired
    public NotModifiableDegreeValidator(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override public boolean isValid(UserDto userDto, ConstraintValidatorContext context) {
        Optional<User> dbUser = this.userRepository.findById(userDto.getId());

        return dbUser
            .map(user -> !userDto.getDegree().equalsIgnoreCase(user.getDegree().toString()) && user.getDegree().toString().equals("DOCTORATE"))
            .orElse(true);
    }
}