package ru.petrojectgroup.conscience.storage.post;

import ru.petrojectgroup.conscience.model.Post;

import java.util.Collection;

public interface PostStorage {
    Post createPost(Post post);
    void deletePost(long postId);
    Collection<Post> findAll();
    Post updatePost(Post post);
    Post findPost(long postId);
    Collection<Post> findAllPostsOfUser(long userId);
}
