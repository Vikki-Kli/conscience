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
    @NotEmpty(message = "Login can not be empty")
    @NotNull(message = "Login cannot be null")
    private String login;
    private String name;
    @NotNull(message = "Birthday cannot be null")
    private LocalDate birthday;
    private boolean isAuthorized; //TODO: продумать момент, когда у пользователя меняется статус авторизации
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