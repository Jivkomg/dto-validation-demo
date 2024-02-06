# Spring Boot DTO Validation Demo

This project is a demonstration of how to use annotation-based validation in a Spring Boot application, focusing on DTOs (Data Transfer Objects). It utilizes various annotations for validation and showcases best practices for handling errors using a global exception handler.

## Controllers

The main controller, `UserController`, handles CRUD operations for user entities. It leverages the power of Spring's validation annotations to ensure data integrity and adherence to business rules.

### Endpoints

1. **Get All Users**
    - Endpoint: `GET /users`
    - Returns a list of all users.

2. **Get User by ID**
    - Endpoint: `GET /users/{id}`
    - Validates the existence of the user ID using the `@UserIdPathVariableExists` custom annotation.
    - Returns the user details if found, otherwise returns a detailed error response.

3. **Create User**
    - Endpoint: `POST /users`
    - Validates the incoming `UserDto` using the `@Valid` annotation.
    - Returns the created user if successful, otherwise returns a detailed error response.

4. **Update User**
    - Endpoint: `PUT /users/{id}`
    - Validates the existence of the user ID using the `@UserIdPathVariableExists` custom annotation.
    - Validates the `UserDto` using the `@Validated(Update.class)` annotation, which is part of the `ValidationGroups` for update operations.
    - Returns the updated user if successful, otherwise returns a detailed error response.

## DTOs (Data Transfer Objects)

The project uses `UserDto` to transfer user-related data between the client and server. The DTO class incorporates various validation annotations to ensure the correctness of the data.

### UserDto Validation Annotations

- `@NotBlank`: Ensures that fields like name and email are not blank.
- `@Email`: Validates the email format.
- `@Min` and `@Max`: Enforces age constraints.
- `@Pattern`: Ensures that the degree follows a specific pattern.
- `@Size`: Specifies the maximum size for the URL.
- `@UniqueElements`: Validates uniqueness for book titles.
- Custom annotation `@NotBlankIfAnotherFieldHasValue`: Ensures that the URL is not blank if the degree is "DOCTORATE."

## Models

The project includes several models like `User`, `Book`, `StudentUrl`, and a base entity `BaseEntity`. These entities are used to represent data in the database.

## Services

The `UserService` class contains methods for creating, updating, and retrieving users. It integrates validation logic and performs the necessary checks before interacting with the database.

### User Service Validation

The `validateUserDto` method contains the validation logic for `UserDto`. It checks various conditions such as blank fields, email format, age limits, and more.

## Exception Handling

The project employs a global exception handler (`GlobalExceptionHandler`) to handle different types of exceptions, providing consistent and detailed error responses.

### Exception Handling Features

- Catches `MethodArgumentNotValidException` for method argument validation errors.
- Catches `ConstraintViolationException` for constraint violations.
- Handles general runtime exceptions with a detailed error map.

## Validation Groups

The project uses validation groups, such as `Update.class`, to differentiate between validation rules for creating and updating entities.

## Custom Validators

A custom validator (`NotBlankIfAnotherFieldHasValueValidator`) is implemented to handle conditional validation based on the value of another field.

## Conclusion

This project serves as a comprehensive example of how to use Spring's annotation-based validation to enhance the robustness of your application by ensuring data correctness and adherence to business rules.
For more information check out my article about [SpringBoot DTO Validation](https://medium.com/paysafe-bulgaria/springboot-dto-validation-good-practices-and-breakdown-fee69277b3b0)
