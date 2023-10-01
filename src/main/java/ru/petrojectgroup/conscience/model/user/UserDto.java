package ru.petrojectgroup.conscience.model.user;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDate;

@Data
@Schema(description = "Сущность пользователя")
public class UserDto {
    @NotBlank(message="Email can not be empty")
    @Email
    @Size(min = 6, max = 254, message = "Email must be between 6 and 254 characters long")
    private final String email;
    @NotBlank(message = "Login can not be blank")
    @Size(min = 3, max = 32, message = "Login must be between 3 and 32 characters long")
    private final String login;
    private String name;
    @NotNull(message = "Birthday cannot be null")
    @Past(message = "Birthday cannot be in future")
    @Schema(description = "День Рождения пользователя", example = "2008-05-26")
    private final LocalDate birthday;
    private String photoUrl;

    public UserDto(String email, String login, LocalDate birthday, String name) {
        this.email = email;
        this.birthday = birthday;
        this.login = login;
        this.name = name == null || name.trim().isEmpty() ? login : name;
    }
}
