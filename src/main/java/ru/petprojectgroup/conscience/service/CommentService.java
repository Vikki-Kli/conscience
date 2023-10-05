package ru.petprojectgroup.conscience.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.petprojectgroup.conscience.exception.AccessException;
import ru.petprojectgroup.conscience.model.comment.Comment;
import ru.petprojectgroup.conscience.model.comment.CommentDto;
import ru.petprojectgroup.conscience.model.comment.CommentMapper;
import ru.petprojectgroup.conscience.storage.comment.CommentStorage;
import ru.petprojectgroup.conscience.storage.post.PostStorage;
import ru.petprojectgroup.conscience.storage.user.UserStorage;

import java.time.Instant;
import java.util.Collection;

@Service
@Slf4j
public class CommentService {
    private final CommentStorage commentStorage;
    private final PostStorage postStorage;
    private final UserStorage userStorage;

    public CommentService(CommentStorage commentStorage, PostStorage postStorage, UserStorage userStorage) {
        this.commentStorage = commentStorage;
        this.postStorage = postStorage;
        this.userStorage = userStorage;
    }

    public CommentDto createComment(CommentDto dto, long postId, long userId) {
        dto.setCreationDate(Instant.now());
        dto.setUserId(userId);
        dto.setPostId(postId);

        Comment comment = CommentMapper.dtoToPojo(dto, userStorage.findById(userId).get(), postStorage.findById(postId).get());
        Comment savedComment = commentStorage.save(comment);
        log.info("Создано " + savedComment);
        return CommentMapper.pojoToDto(savedComment);
    }

    public void deleteComment(long postId, long commentId, long userId) {
        userStorage.existingCheck(userId);
        postStorage.existingCheck(postId);
        CommentDto dto = findComment(commentId); //при неверном id поста выбросит исключение

        if (dto.getUserId() == userId) {
            commentStorage.deleteById(commentId);
            log.info("Удален комментарий " + commentId);
        } else {
            throw new AccessException("User is not author of the comment");
        }
    }

    public Collection<CommentDto> findAll(Long postId) {
        return commentStorage.findAllByPostId(postId).stream().map(CommentMapper::pojoToDto).toList();
    }

    public CommentDto updateComment(CommentDto dto, long postId, long commentId, long userId) {
        userStorage.existingCheck(userId);
        postStorage.existingCheck(postId);
        Comment comment = commentStorage.existingCheck(commentId);
        dto.setCreationDate(comment.getCreationDate());

        if (comment.getUser().getId() == userId) {
            comment = commentStorage.save(comment);
            log.info("Обновлен комментарий " + commentId);
            return CommentMapper.pojoToDto(comment);
        } else {
            throw new AccessException("User is not author of the comment");
        }
    }

    public CommentDto findComment(long commentId) {
        return CommentMapper.pojoToDto(commentStorage.existingCheck(commentId));
    }
}
