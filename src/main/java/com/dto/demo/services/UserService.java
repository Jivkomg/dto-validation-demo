package com.dto.demo.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dto.demo.dtos.UserDto;
import com.dto.demo.models.Book;
import com.dto.demo.models.StudentUrl;
import com.dto.demo.models.User;
import com.dto.demo.repositories.BookRepository;
import com.dto.demo.repositories.StudentUrlRepository;
import com.dto.demo.repositories.UserRepository;
import com.dto.demo.utils.enums.Degree;
import com.dto.demo.utils.EntityToDtoMapper;

@Service
public class UserService {

    private UserRepository userRepository;

    private BookRepository bookRepository;

    private StudentUrlRepository studentUrlRepository;

    @Autowired
    public UserService(UserRepository userRepository, BookRepository bookRepository, StudentUrlRepository studentUrlRepository){
        this.userRepository = userRepository;
        this.bookRepository = bookRepository;
        this.studentUrlRepository = studentUrlRepository;
    }

    public UserDto createUser(UserDto userDto) {
//        this.validateUserDto(userDto);

        User userDtoToBeCreated = EntityToDtoMapper.mapUserDtoToUser(userDto);

        this.setupAdditionalFields(userDto, userDtoToBeCreated);

        User savedUser =  userRepository.save(userDtoToBeCreated);

        return EntityToDtoMapper.mapUserToUserDto(savedUser);
    }

    public UserDto updateUser(String id, UserDto userDto) {
//        this.validateUserDto(userDto);

        Optional<User> userOptional = userRepository.findById(id);

        if (userOptional.isEmpty()) {
            throw new RuntimeException("User with id " + id + " does not exist");
        }
        User dbUser = userOptional.get();

//        this.checkValidDegreeChange(dbUser.getDegree(), userDto.getDegree());

        EntityToDtoMapper.mapUserDtoToUser(dbUser, userDto);

        this.setupAdditionalFields(userDto, dbUser);

        User savedUser =  userRepository.save(dbUser);

        return EntityToDtoMapper.mapUserToUserDto(savedUser);
    }

    private void checkValidDegreeChange(Degree degree, String userDtoDegree) {
        if (degree.toString().equals(Degree.DOCTORATE.toString()) && !userDtoDegree.equals(Degree.DOCTORATE.toString())) {
            throw new RuntimeException("Cannot change degree from DOCTORATE");
        }
    }

    private void validateUserDto(UserDto userDto) {
        if (userDto.getName().isBlank() || null == userDto.getName()) {
            throw new RuntimeException("Name is required");
        }

        if (userDto.getEmail().isBlank() || null == userDto.getEmail()) {
            throw new RuntimeException("Email is required");
        }

        if (!userDto.getEmail().endsWith("@uni-sofia.com")) {
            throw new RuntimeException("Email must end with @uni-sofia.com");
        }

        if (userDto.getAge() < 18) {
            throw new RuntimeException("User must be 18 or older");
        }

        if (userDto.getAge() > 100) {
            throw new RuntimeException("User must be 100 or younger");
        }

        if (!userDto.getDegree().equals(Degree.BACHELOR.toString()) && !userDto.getDegree().equals(Degree.MASTER.toString()) && !userDto.getDegree().equals(Degree.DOCTORATE.toString())) {
            throw new RuntimeException("Degree must be BACHELOR, MASTER or DOCTORATE");
        }

        if (userDto.getUrl().length() > 100) {
            throw new RuntimeException("Url must be less than 100 characters");
        }

        if (userDto.getDegree().equals(Degree.DOCTORATE.toString()) && userDto.getUrl().isEmpty()) {
            throw new RuntimeException("Url is required for DOCTORATE degree");
        }
    }

    private void setupAdditionalFields(UserDto userDto, User userDtoToBeCreated) {
        if (!userDto.getUrl().isBlank()) {
            Optional<StudentUrl> optionalStudentUrl = this.studentUrlRepository.findByUrl(userDto.getUrl());
            if (optionalStudentUrl.isEmpty()) {
                throw new RuntimeException("Url does not exist");
            }
            StudentUrl studentUrl = optionalStudentUrl.get();
            userDtoToBeCreated.setStudentUrl(studentUrl);
        }

        if (!userDto.getBookTitles().isEmpty()) {
            List<Book> books = this.bookRepository.findAllByTitleIn(userDto.getBookTitles());
            if (books.size() != userDto.getBookTitles().size()) {
                throw new RuntimeException("One or more books do not exist");
            }
            userDtoToBeCreated.setBooks(books);
        }
    }

    public UserDto getUserById(String id) {
        if (userRepository.findById(id).isEmpty()) {
            throw new RuntimeException("User with id " + id + " does not exist");
        }

        return EntityToDtoMapper.mapUserToUserDto(userRepository.findById(id).get());
    }

    public List<UserDto> getAllUsers() {
        return userRepository.findAll()
            .stream()
            .map(EntityToDtoMapper::mapUserToUserDto)
            .collect(Collectors.toList());
    }
}
