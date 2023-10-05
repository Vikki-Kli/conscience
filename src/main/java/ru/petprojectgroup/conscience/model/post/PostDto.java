package ru.petprojectgroup.conscience.model.post;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.time.Instant;

@Data
@Schema(description = "Сущность поста")
public class PostDto {
    @NotBlank(message = "Post content cannot be blank")
    @Schema(description = "Тело поста")
    private final String postContent;
    @Schema(description = "Дата и время создания поста, при создании поста присваивается автоматически", example = "2023-10-01T09:26:05.690500600Z")
    private Instant creationDate;
    private final long userId;
    private String photoUrl;
}