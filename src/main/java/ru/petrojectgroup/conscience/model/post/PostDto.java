package ru.petrojectgroup.conscience.model.post;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.time.Instant;

@Data
@NoArgsConstructor
public class PostDto {
    @NotBlank(message = "Post content cannot be blank")
    private String postContent;
    private Instant creationDate = Instant.now(); // дата создания
    private long userId;
    private String photoUrl; // опционально
}

//В будущем сделать две DTO - одну на вход, где creationDate файнал и инициализируется при создании,
// а вторую на вывод, где она тоже файнал, но инициализируется через конструктор