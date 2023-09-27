package ru.petrojectgroup.conscience.model;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.Instant;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class Post {
    private long postId;
    @NotBlank(message = "Post content cannot be blank")
    private String postContent;
    private final Instant creationDate = Instant.now(); // дата создания

    private long userId;
    private String photoUrl; // опционально

    // поле postId присваивается БД
    public Post(String postContent, long userId) {
        this.postContent = postContent;
        this.userId = userId;
    }


}
