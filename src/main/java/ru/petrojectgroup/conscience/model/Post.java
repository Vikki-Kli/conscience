package ru.petrojectgroup.conscience.model;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Data
@NonNull
public class Post {
    private long postId;
    @NotBlank(message = "Post content cannot be blank")
    private String postContent;
    private final Instant creationDate = Instant.now(); // дата создания
    private final long userId;
    private String photoUrl; // опционально

    // поле postId присваивается БД
    public Post(String postContent, long userId) {
        this.postContent = postContent;
        this.userId = userId;
    }
}
