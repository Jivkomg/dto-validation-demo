package com.dto.demo.dtos;

import java.util.List;

import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.UniqueElements;

import com.dto.demo.validation.annotations.NotBlankIfAnotherFieldHasValue;
import com.dto.demo.validation.annotations.NotModifiableDegree;
import com.dto.demo.validation.ValidationGroups.Update;

@NotBlankIfAnotherFieldHasValue(fieldName = "degree", fieldValue = "DOCTORATE", dependFieldName = "url", message = "Url cannot be blank for doctorate degree")
@NotModifiableDegree(groups = Update.class)
public class UserDto {
    private String id;

    @NotBlank(message = "Name cannot be blank")
    private String name;

    @NotBlank(message = "Email cannot be blank")
    @Email(message = "Email should be valid", regexp = "^[A-Za-z0-9+_.-]+@uni-sofia.com")
    private String email;

    @Min(value = 18, message = "Age should be greater than 18")
    @Max(value = 100, message = "Age should be less than 100")
    private int age;

    @Pattern(regexp = "BACHELOR|MASTER|DOCTORATE", message = "Degree should be BACHELOR, MASTER or DOCTORATE")
    private String degree;

    @Size(max = 100, message = "Url should be up to 100 characters")
    private String url;

    @UniqueElements(message = "Book titles should be unique")
    private List<String> bookTitles;

    public UserDto() {
    }

    public UserDto(String id, String name, String email, int age, String degree, String url, List<String> bookTitles) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.age = age;
        this.degree = degree;
        this.url = url;
        this.bookTitles = bookTitles;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public List<String> getBookTitles() {
        return bookTitles;
    }

    public void setBookTitles(List<String> bookTitles) {
        this.bookTitles = bookTitles;
    }
}
