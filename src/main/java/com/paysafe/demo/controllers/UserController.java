package com.paysafe.demo.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.paysafe.demo.dtos.UserDto;
import com.paysafe.demo.services.UserService;
import com.paysafe.demo.utils.ErrorMapper;
import com.paysafe.demo.validation.annotations.UserIdPathVariableExists;
import com.paysafe.demo.validation.ValidationGroups.Update;

@Validated
@RestController
public class UserController {

    private UserService userService;

    private ErrorMapper errorMapper;

    @Autowired
    public UserController(UserService userService, ErrorMapper errorMapper) {
        this.userService = userService;
        this.errorMapper = errorMapper;
    }

    @GetMapping("/users")
    public List<UserDto> getUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/users/{id}")
    public ResponseEntity getUser(
        @UserIdPathVariableExists
        @PathVariable String id
    ) {
        try {
            return new ResponseEntity<>(this.userService.getUserById(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(this.errorMapper.createErrorMap(e), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/users")
    ResponseEntity createUser(
        @Valid
        @RequestBody UserDto userDto) {
        try {
            return new ResponseEntity<>(this.userService.createUser(userDto), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(this.errorMapper.createErrorMap(e), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/users/{id}")
    ResponseEntity updateUser(
        @UserIdPathVariableExists
        @PathVariable String id,
//        @Valid
        @Validated(Update.class)
        @RequestBody UserDto userDto
    ) {
        try {
            return new ResponseEntity<>(this.userService.updateUser(id, userDto), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(this.errorMapper.createErrorMap(e), HttpStatus.BAD_REQUEST);
        }
    }
}