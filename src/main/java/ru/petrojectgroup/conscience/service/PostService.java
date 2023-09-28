package ru.petrojectgroup.conscience.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.petrojectgroup.conscience.exception.AccessException;
import ru.petrojectgroup.conscience.exception.ValidationException;
import ru.petrojectgroup.conscience.model.post.Post;
import ru.petrojectgroup.conscience.model.post.PostDto;
import ru.petrojectgroup.conscience.model.post.PostMapper;
import ru.petrojectgroup.conscience.model.user.User;
import ru.petrojectgroup.conscience.storage.post.PostStorage;
import ru.petrojectgroup.conscience.storage.user.UserStorage;

import java.util.Collection;
import java.util.NoSuchElementException;

@Service
@Slf4j
public class PostService {
    private final PostStorage postStorage;
    private final UserStorage userStorage;

    public PostService(PostStorage postStorage, UserStorage userStorage) {
        this.postStorage = postStorage;
        this.userStorage = userStorage;
    }

    public PostDto createPost(PostDto dto, long userId) {
        Post post = PostMapper.dtoToPojo(dto);
        User user = userStorage.existingCheck(userId);
        post.setUser(user);

        Post savedPost = postStorage.save(post);
        log.info("Создано " + savedPost);
        return PostMapper.pojoToDto(savedPost);
    }

    public void deletePost(long postId, long userId) {
        userStorage.existingCheck(userId);
        PostDto post = findPost(postId); //при неверном id поста выбросит исключение

        if (post.getUserId() == userId) {
            postStorage.deleteById(postId);
            log.info("Удален пост " + postId);
        } else {
            throw new AccessException("User is not author of the post");
        }
    }

    public Collection<PostDto> findAll(Long userId) {
        if (userId == null) return postStorage.findAll().stream().map(PostMapper::pojoToDto).toList();
        else {
            userStorage.existingCheck(userId);
            return postStorage.findAllByUser_id(userId).stream().map(PostMapper::pojoToDto).toList();
        }
    }

    public PostDto updatePost(PostDto dto, long id, long userId) {
        userStorage.existingCheck(userId);
        Post post = postStorage.existingCheck(id);
        if (post.getUser().getId() == userId) {
            post = postStorage.save(post);
            log.info("Обновлен пост " + id);
            return PostMapper.pojoToDto(post);
        } else {
            throw new AccessException("User is not author of the post");
        }
    }

    public PostDto findPost(long postId) {
        return PostMapper.pojoToDto(postStorage.existingCheck(postId));
    }
}
