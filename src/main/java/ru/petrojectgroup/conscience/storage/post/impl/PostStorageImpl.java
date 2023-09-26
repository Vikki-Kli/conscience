package ru.petrojectgroup.conscience.storage.post.impl;

import ru.petrojectgroup.conscience.model.Post;
import ru.petrojectgroup.conscience.storage.post.dao.PostStorage;

import java.util.Collection;


public class PostStorageImpl implements PostStorage {

    @Override
    public Collection<Post> findAll() {
        return null;
    }

    @Override
    public void deletePost(long postId) {

    }

    @Override
    public Post createPost(Post post) {
        return null;
    }

    @Override
    public Post updatePost(Post post) {
        return null;
    }

    @Override
    public Post findPost(long postId) {
        return null;
    }

    @Override
    public Collection<Post> findAllPostsOfUser(long userId) {
        return null;
    }
}
