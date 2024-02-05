package com.dto.demo.utils;

import java.time.LocalDateTime;
import java.util.Arrays;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dto.demo.models.Book;
import com.dto.demo.models.StudentUrl;
import com.dto.demo.models.User;
import com.dto.demo.repositories.BookRepository;
import com.dto.demo.repositories.StudentUrlRepository;
import com.dto.demo.repositories.UserRepository;
import com.dto.demo.utils.enums.Degree;

@Component
public class DataPopulator {

    private UserRepository userRepository;

    private StudentUrlRepository studentUrlRepository;

    private BookRepository bookRepository;

    @Autowired
    public DataPopulator(UserRepository userRepository, StudentUrlRepository studentUrlRepository, BookRepository bookRepository) {
        this.userRepository = userRepository;
        this.studentUrlRepository = studentUrlRepository;
        this.bookRepository = bookRepository;
    }

    @PostConstruct
    public void populateDatabase() {
        StudentUrl studentUrl1 = new StudentUrl();
        studentUrl1.setUrl("https://www.learn.com/student1");
        studentUrl1.setLastAccessed(LocalDateTime.now());
        studentUrlRepository.save(studentUrl1);

        StudentUrl studentUrl2 = new StudentUrl();
        studentUrl2.setUrl("https://www.learn.com/student2");
        studentUrl2.setLastAccessed(LocalDateTime.now());
        studentUrlRepository.save(studentUrl2);

        Book book1 = new Book();
        book1.setTitle("Introduction to Algorithms");
        book1.setAuthor("Thomas H. Cormen");
        bookRepository.save(book1);

        Book book2 = new Book();
        book2.setTitle("Clean Code");
        book2.setAuthor("Robert C. Martin");
        bookRepository.save(book2);

        Book book3 = new Book();
        book3.setTitle("Python for Beginners");
        book3.setAuthor("Lisa Smith");
        bookRepository.save(book3);

        Book book4 = new Book();
        book4.setTitle("Design Patterns");
        book4.setAuthor("Erich Gamma");
        bookRepository.save(book4);

        User user = new User();
        user.setName("John Doe");
        user.setEmail("johndoe@uni-sofia.com");
        user.setAge(23);
        user.setBooks(Arrays.asList(book1, book2));
        user.setStudentUrl(studentUrl1);
        user.setDegree(Degree.BACHELOR);
        userRepository.save(user);
    }
}