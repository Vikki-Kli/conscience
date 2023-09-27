package ru.petrojectgroup.conscience.controller;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.petrojectgroup.conscience.model.Post;
import ru.petrojectgroup.conscience.service.PostService;

import java.util.Collection;

@RestController
@Slf4j
@RequestMapping("/posts")
public class PostController {
    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping
    public Post createPost(@RequestBody @Valid Post post) {
        postService.createPost(post);
        log.info("Пользователь с id {} добавил новый пост", post.getUserId());
        return post;
    }

    @DeleteMapping("/{id}")
    public void deletePost(@PathVariable long postId) {
        postService.deletePost(postId);
        log.info("Пост с id {} был удален", postId);
    }

    @GetMapping
    public Collection<Post> findAll() {
        return postService.findAll();
    }

    @PutMapping
    public Post updatePost(@RequestBody @Valid Post post) {
        postService.updatePost(post);
        log.info("Пост с id {} был обновлен", postId);
        return post;
    }

    @GetMapping("/{id}")
    public Post findPost(@PathVariable long postId) {
        return postService.findPost(postId);
    }

    @GetMapping("/{id}")
    public Collection<Post> findAllPostsOfUser(@PathVariable long userId) {
        return postService.findAllPostsOfUser(userId);
    }
}
