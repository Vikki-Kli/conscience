package ru.petprojectgroup.conscience.model.comment;

import ru.petprojectgroup.conscience.model.post.Post;
import ru.petprojectgroup.conscience.model.user.User;

public class CommentMapper {
    public static Comment dtoToPojo(CommentDto dto, User user, Post post) {
        return Comment.builder()
                .text(dto.getText())
                .creationDate(dto.getCreationDate())
                .user(user)
                .post(post)
                .photoUrl(dto.getPhotoUrl())
                .build();
    }

    public static CommentDto pojoToDto(Comment comment) {
        return CommentDto.builder()
                .text(comment.getText())
                .creationDate(comment.getCreationDate())
                .userId(comment.getUser().getId())
                .postId(comment.getPost().getId())
                .photoUrl(comment.getPhotoUrl())
                .build();
    }
}
