package ru.petrojectgroup.conscience.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.petrojectgroup.conscience.model.Post;
import ru.petrojectgroup.conscience.storage.post.dao.PostStorage;

import java.util.Collection;

@Service
public class PostService implements PostStorage{
    private final PostStorage postStorage;

    @Autowired
    public PostService(PostStorage postStorage) {
        this.postStorage = postStorage;
    }

    public Post createPost(Post post) {
        return postStorage.createPost(post);
    }

    public void deletePost(long postId) {
        postStorage.deletePost(postId);
    }

    public Collection<Post> findAll() {
        return postStorage.findAll();
    }

    public Post updatePost(Post post) {
        return postStorage.updatePost(post);
    }

    public Post findPost(long postId) {
        return postStorage.findPost(postId);
    }

    public Collection<Post> findAllPostsOfUser(long userId) {
        return postStorage.findAllPostsOfUser(userId);
    }
}
