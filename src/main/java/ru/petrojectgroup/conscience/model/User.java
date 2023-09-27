package ru.petrojectgroup.conscience.model;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDate;


@Slf4j
@Data
@NonNull
public class User {
    private long userId;
    @NotEmpty(message = "Name can not be empty")
    private String email;
    @NotBlank(message = "Login can not be blank")
    @Size(min = 3, max = 32, message = "Login must be between 3 and 32 characters long") 
    private String login;
    private String name;
    @NotNull(message = "Birthday cannot be null")
    @Past(message = "Birthday cannot be in future")
    private LocalDate birthday;
    private String photoUrl; // опционально

    // поле userId присваивается БД
    public User(String email, String login, String name, LocalDate birthday) {
        this.email = email;
        this.login = login;
        this.name = name == null || name.trim().isEmpty() ? login : name;
        this.birthday = birthday;
        this.isAuthorized = false;
    }
}
