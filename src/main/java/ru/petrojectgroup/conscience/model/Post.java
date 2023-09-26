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
    @NotNull(message = "Post content cannot be null")
    private String postContent;
    @NotNull(message = "User id cannot be null")
    private long userId;
    private String photoUrl; // опционально

    // поле postId присваивается БД
    public Post(String postContent, long userId) {
        this.postContent = postContent;
        this.userId = userId;
    }
}
