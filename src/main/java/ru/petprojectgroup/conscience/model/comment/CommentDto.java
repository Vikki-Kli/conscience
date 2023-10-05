package ru.petprojectgroup.conscience.model.comment;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;

import java.time.Instant;

@Data
@Builder
@Schema(description = "Сущность коммента")
public class CommentDto {
    @NotBlank(message = "Comment content cannot be blank")
    @Schema(description = "Тело коммента")
    private final String text;
    @Schema(description = "Дата и время создания коммента, при создании коммента присваивается автоматически", example = "2023-10-01T09:26:05.690500600Z")
    private Instant creationDate;
    private long userId;
    private long postId;
    private String photoUrl;
}
