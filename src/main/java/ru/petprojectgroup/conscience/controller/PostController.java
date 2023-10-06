package ru.petprojectgroup.conscience.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import ru.petprojectgroup.conscience.model.post.PostDtoIn;
import ru.petprojectgroup.conscience.model.post.PostDtoOut;
import ru.petprojectgroup.conscience.model.post.reaction.ReactionType;
import ru.petprojectgroup.conscience.service.PostService;

import java.util.Collection;

@RestController
@RequestMapping("/posts")
@Tag(name = "Посты", description = "Взаимодействие с постами")
//@CrossOrigin(origins = "http://192.168.0.103")
public class PostController {
    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping
    @Operation(summary = "Создание поста")
    public PostDtoOut createPost(@RequestBody @Valid PostDtoIn post,
                                 @RequestHeader("X-Conscience-User-Id") @Parameter(description = "id пользователя, отправляющего запрос") long userId) {
        return postService.createPost(post, userId);
    }

    @DeleteMapping("/{postId}")
    @Operation(summary = "Удаление поста")
    public void deletePost(@PathVariable long postId,
                           @RequestHeader("X-Conscience-User-Id") @Parameter(description = "id пользователя, отправляющего запрос") long userId) {
        postService.deletePost(postId, userId);
    }

    @GetMapping
    @Operation(summary = "Запрос всех постов", description = "При передаче id пользователя выводит только посты этого пользователя")
    public Collection<PostDtoOut> findAll(@RequestParam(value = "user", required = false) Long authorId,
                                          @RequestHeader(value = "X-Conscience-User-Id", required = false)
                                                @Parameter(description = "id пользователя, отправляющего запрос") Long userId) {
        return postService.findAll(authorId, userId);
    }

    @PutMapping("/{postId}")
    @Operation(summary = "Редактирование поста")
    public PostDtoOut updatePost(@RequestBody @Valid PostDtoIn post,
                                 @PathVariable long postId,
                                 @RequestHeader("X-Conscience-User-Id") @Parameter(description = "id пользователя, отправляющего запрос") long userId) {
        return postService.updatePost(post, postId, userId);
    }

    @GetMapping("/{postId}")
    @Operation(summary = "Запрос поста")
    public PostDtoOut findPost(@PathVariable long postId,
                               @RequestHeader(value = "X-Conscience-User-Id", required = false)
                                    @Parameter(description = "id пользователя, отправляющего запрос") Long userId) {
        return postService.findPost(postId, userId);
    }

    @PutMapping("/{postId}/reaction")
    @Operation(summary = "Отправить реакцию на пост")
    public void addReaction(@PathVariable long postId,
                            @RequestHeader("X-Conscience-User-Id") @Parameter(description = "id пользователя, отправляющего запрос") long userId,
                            @RequestHeader("X-Conscience-Reaction-Type") @Parameter(description = "тип реакции") String reaction) {
        postService.addReaction(postId, userId, ReactionType.valueOf(reaction));
    }

    @DeleteMapping("/{postId}/reaction")
    @Operation(summary = "Снять реакцию на пост")
    public void removeReaction(@PathVariable long postId,
                            @RequestHeader("X-Conscience-User-Id") @Parameter(description = "id пользователя, отправляющего запрос") long userId) {
        postService.removeReaction(postId, userId);
    }
}
