package ru.petrojectgroup.conscience.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.petrojectgroup.conscience.exception.ValidationException;
import ru.petrojectgroup.conscience.model.Post;
import ru.petrojectgroup.conscience.storage.post.PostStorage;

import java.util.Collection;

@Service
public class PostService {
    private final PostStorage postStorage;

    @Autowired
    public PostService(PostStorage postStorage) {
        this.postStorage = postStorage;
    }

    public Post createPost(Post post) {
        return postStorage.createPost(post);
    }

    public void deletePost(long postId, long userId) {
        findPost(postId); //при неверном id поста выбросит исключение
        if (checkPostAuthor(findPost(postId).getUserId(), userId)) {
            postStorage.deletePost(postId);
        } else {
            throw new ValidationException("User is not author of the post");
        }
    }

    public Collection<Post> findAll() {
        return postStorage.findAll();
    }

    public Post updatePost(Post post, long userId) {
        findPost(post.getPostId()); //при неверном id поста выбросит исключение
        if (checkPostAuthor(post.getUserId(), userId)) {
            return postStorage.updatePost(post);
        } else {
            throw new ValidationException("User is not author of the post");
        }
    }

    public Post findPost(long postId) {
        return postStorage.findPost(postId);
    }

    public Collection<Post> findAllPostsOfUser(long userId) {
        return postStorage.findAllPostsOfUser(userId);
    }

    private boolean checkPostAuthor(long userIdFromPost, long userId) {
        return userIdFromPost == userId;
    }
    //TODO + проверка на юзеера
}
