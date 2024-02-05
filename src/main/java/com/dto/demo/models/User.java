package com.dto.demo.models;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.dto.demo.utils.enums.Degree;

@Entity
@Table(name="users")
public class User extends BaseEntity {

    private String name;

    private String email;

    private int age;

    private Degree degree;

    private StudentUrl studentUrl;

    private List<Book> books;

    @Column(name="name", nullable = false)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "email", nullable = false)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Column(name = "age")
    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Column(name = "degree")
    @Enumerated(value = EnumType.STRING)
    public Degree getDegree() {
        return degree;
    }

    public void setDegree(Degree degree) {
        this.degree = degree;
    }

    @ManyToOne
    @JoinColumn
    public StudentUrl getStudentUrl() {
        return studentUrl;
    }

    public void setStudentUrl(StudentUrl url) {
        this.studentUrl = url;
    }

    @ManyToMany
    @JoinTable(
        name = "users_books",
        joinColumns = @JoinColumn(name = "user_id"),
        inverseJoinColumns = @JoinColumn(name = "book_id")
    )
    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }
}