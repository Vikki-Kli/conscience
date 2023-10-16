package ru.petprojectgroup.conscience.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import ru.petprojectgroup.conscience.model.comment.CommentDto;
import ru.petprojectgroup.conscience.service.CommentService;

import java.util.Collection;

@RestController
@RequestMapping("/posts")
@Tag(name = "Комментарии", description = "Взаимодействие с комментариями")
@CrossOrigin
public class CommentController {
    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping("/{postId}/comments")
    @Operation(summary = "Создание поста")
    public CommentDto createComment(@RequestBody @Valid CommentDto comment, @PathVariable long postId,
                                    @RequestHeader("X-Conscience-User-Id") @Parameter(description = "id пользователя, отправляющего запрос") long userId) {
        return commentService.createComment(comment, postId, userId);
    }

    @DeleteMapping("/{postId}/comments/{commentId}")
    @Operation(summary = "Удаление комментария")
    public void deleteComment(@PathVariable long postId, @PathVariable long commentId,
                           @RequestHeader("X-Conscience-User-Id") @Parameter(description = "id пользователя, отправляющего запрос") long userId) {
        commentService.deleteComment(postId, commentId, userId);
    }

    @GetMapping("/{postId}/comments")
    @Operation(summary = "Запрос всех комментариев к посту")
    public Collection<CommentDto> findAll(@PathVariable long postId) {
        return commentService.findAll(postId);
    }

    @PutMapping("/{postId}/comments/{commentId}")
    @Operation(summary = "Редактирование комментария")
    public CommentDto updateComment(@RequestBody @Valid CommentDto comment,
                              @PathVariable long postId, @PathVariable long commentId,
                              @RequestHeader("X-Conscience-User-Id") @Parameter(description = "id пользователя, отправляющего запрос") long userId) {
        return commentService.updateComment(comment, postId, commentId, userId);
    }

    @GetMapping("/{postId}/comments/{commentId}")
    @Operation(summary = "Запрос комментария")
    public CommentDto findComment(@PathVariable long postId, @PathVariable long commentId) {
        return commentService.findComment(commentId);
    }
}
