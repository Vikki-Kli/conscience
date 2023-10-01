package ru.petrojectgroup.conscience.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import ru.petrojectgroup.conscience.model.post.PostDto;
import ru.petrojectgroup.conscience.service.PostService;

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
    public PostDto createPost(@RequestBody @Valid PostDto post,
                                 @RequestHeader("X-ShareIt-User-Id") @Parameter(description = "id пользователя, отправляющего запрос") long userId) {
        return postService.createPost(post, userId);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Удаление поста")
    public void deletePost(@PathVariable long postId,
                           @RequestHeader("X-ShareIt-User-Id") @Parameter(description = "id пользователя, отправляющего запрос") long userId) {
        postService.deletePost(postId, userId);
    }

    @GetMapping
    @Operation(summary = "Запрос всех постов", description = "При передаче id пользователя выводит только посты этого пользователя")
    public Collection<PostDto> findAll(@RequestParam("user") Long userId) {
        return postService.findAll(userId);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Редактирование поста")
    public PostDto updatePost(@RequestBody @Valid PostDto post,
                                @PathVariable long postId,
                                @RequestHeader("X-ShareIt-User-Id") @Parameter(description = "id пользователя, отправляющего запрос") long userId) {
        return postService.updatePost(post, postId, userId);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Запрос поста")
    public PostDto findPost(@PathVariable long postId) {
        return postService.findPost(postId);
    }
}
