package ru.petrojectgroup.conscience.model;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class User {
    private long userId;
    @NotBlank(message="Email can not be empty")
    @Email
    @Size(min = 6, max = 80, message = "Email must be between 6 and 80 characters long")
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
    }
}
