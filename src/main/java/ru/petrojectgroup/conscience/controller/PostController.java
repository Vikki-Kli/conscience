package ru.petrojectgroup.conscience.controller;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.petrojectgroup.conscience.model.post.PostDto;
import ru.petrojectgroup.conscience.service.PostService;

import java.util.Collection;

@RestController
@RequestMapping("/posts")
public class PostController {
    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping
    public PostDto createPost(@RequestBody @Valid PostDto post, @RequestHeader("X-ShareIt-User-Id") long userId) {
        return postService.createPost(post, userId);
    }

    @DeleteMapping("/{id}")
    public void deletePost(@PathVariable long postId, @RequestHeader("X-ShareIt-User-Id") long userId) {
        postService.deletePost(postId, userId);
    }

    @GetMapping
    public Collection<PostDto> findAll(@RequestParam("user") Long userId) {
        return postService.findAll(userId);
    }

    @PutMapping("/{id}")
    public PostDto updatePost(@RequestBody @Valid PostDto post,
                              @PathVariable long id,
                              @RequestHeader("X-ShareIt-User-Id") long userId) {
        return postService.updatePost(post, id, userId);
    }

    @GetMapping("/{id}")
    public PostDto findPost(@PathVariable long postId) {
        return postService.findPost(postId);
    }
}
