package com.dto.demo.utils;

import java.util.stream.Collectors;

import com.dto.demo.dtos.UserDto;
import com.dto.demo.models.Book;
import com.dto.demo.models.User;
import com.dto.demo.utils.enums.Degree;

public class EntityToDtoMapper {

    public static UserDto mapUserToUserDto(User user) {
        return new UserDto(user.getId(), user.getName(), user.getEmail(),
            user.getAge(), user.getDegree().toString(), user.getStudentUrl().getUrl(),
            user.getBooks().stream().map(Book::getTitle).collect(Collectors.toList()));
    }

    public static User mapUserDtoToUser(UserDto userDto) {
        User user = new User();
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setAge(userDto.getAge());
        user.setDegree(Degree.valueOf(userDto.getDegree()));
        return user;
    }

    public static void mapUserDtoToUser(User user, UserDto userDto) {
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setAge(userDto.getAge());
        user.setDegree(Degree.valueOf(userDto.getDegree()));
    }
}
