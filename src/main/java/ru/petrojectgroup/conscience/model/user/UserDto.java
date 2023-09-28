package ru.petrojectgroup.conscience.model.user;

import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDate;

@Data
@NoArgsConstructor
public class UserDto {
    @NotBlank(message="Email can not be empty")
    @Email
    @Size(min = 6, max = 254, message = "Email must be between 6 and 254 characters long")
    private String email;
    @NotBlank(message = "Login can not be blank")
    @Size(min = 3, max = 32, message = "Login must be between 3 and 32 characters long")
    private String login;
    private String name;
    @NotNull(message = "Birthday cannot be null")
    @Past(message = "Birthday cannot be in future")
    private LocalDate birthday;
    private String photoUrl; // опционально

    public UserDto(String email, String login, String name, LocalDate birthday) {
        this.email = email;
        this.login = login;
        this.name = name == null || name.trim().isEmpty() ? login : name;
        this.birthday = birthday;
    }
}
