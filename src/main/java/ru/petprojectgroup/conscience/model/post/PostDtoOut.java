package ru.petprojectgroup.conscience.model.post;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.time.Instant;

@Data
@Schema(description = "Сущность поста на вывод")
public class PostDtoOut {
    @NotBlank(message = "Post content cannot be blank")
    @Schema(description = "Тело поста")
    private String postContent;
    @Schema(description = "Дата и время создания поста, при создании поста присваивается автоматически", example = "2023-10-01T09:26:05.690500600Z")
    private Instant creationDate;
    private long userId;
    private String photoUrl;
    private long amnesties;
    private long blames;
    private boolean blamedByMe;
    private boolean amnestiedByMe;
}