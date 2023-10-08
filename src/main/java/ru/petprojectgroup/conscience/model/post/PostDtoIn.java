package ru.petprojectgroup.conscience.model.post;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
@Schema(description = "Сущность поста на вход")
public class PostDtoIn {
    @NotBlank(message = "Post content cannot be blank")
    @Schema(description = "Тело поста")
    private String postContent;
    private String photoUrl;
}
