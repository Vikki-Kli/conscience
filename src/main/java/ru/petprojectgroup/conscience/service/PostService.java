package ru.petprojectgroup.conscience.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.petprojectgroup.conscience.exception.AccessException;
import ru.petprojectgroup.conscience.model.post.Post;
import ru.petprojectgroup.conscience.model.post.PostDtoIn;
import ru.petprojectgroup.conscience.model.post.PostDtoOut;
import ru.petprojectgroup.conscience.model.post.PostMapper;
import ru.petprojectgroup.conscience.model.post.reaction.Reaction;
import ru.petprojectgroup.conscience.model.post.reaction.ReactionType;
import ru.petprojectgroup.conscience.model.user.User;
import ru.petprojectgroup.conscience.storage.post.PostStorage;
import ru.petprojectgroup.conscience.storage.reaction.ReactionStorage;
import ru.petprojectgroup.conscience.storage.user.UserStorage;

import java.time.Instant;
import java.util.Collection;
import java.util.Optional;

@Service
@Slf4j
public class PostService {
    private final PostStorage postStorage;
    private final UserStorage userStorage;
    private final ReactionStorage reactionStorage;

    public PostService(PostStorage postStorage, UserStorage userStorage, ReactionStorage reactionStorage) {
        this.postStorage = postStorage;
        this.userStorage = userStorage;
        this.reactionStorage = reactionStorage;
    }

    public PostDtoOut createPost(PostDtoIn dto, long userId) {
        Post post = PostMapper.dtoToPojo(dto);
        post.setCreationDate(Instant.now());
        userStorage.existingCheck(userId);
        User user = userStorage.findById(userId).get();
        post.setUser(user);

        Post savedPost = postStorage.save(post);
        log.info("Создано " + savedPost);
        return PostMapper.pojoToDto(savedPost);
    }

    public void deletePost(long postId, long userId) {
        postStorage.existingCheck(postId);
        Post post = postStorage.findById(postId).get();

        if (post.getUser().getId() == userId) {
            postStorage.deleteById(postId);
            log.info("Удален пост " + postId);
        } else {
            throw new AccessException("User is not author of the post");
        }
    }

    public Collection<PostDtoOut> findAll(Long authorId, Long userId) {
        Collection<Post> posts;
        if (authorId == null) posts = postStorage.findAll();
        else {
            userStorage.existingCheck(authorId);
            posts = postStorage.findAllByUser_id(authorId);
        }

        if (userId != null) return posts.stream().map(s -> addReactionToPostIfExists(s, userId)).toList();
        else return posts.stream().map(PostMapper::pojoToDto).toList();
    }
    //TODO: Реализовать постраничный вывод постов

    private PostDtoOut addReactionToPostIfExists(Post post, Long userId) {
        PostDtoOut postDto = PostMapper.pojoToDto(post);
        if (userId != null) {
            Optional<Reaction> reaction = reactionStorage.findByUserIdAndPostId(userId, post.getId());
            reaction.ifPresent(
                    (value) -> {
                        if (value.getReactionType() == ReactionType.BLAME) postDto.setBlamedByMe(true);
                        else if (value.getReactionType() == ReactionType.AMNESTY) postDto.setAmnestiedByMe(true);
                    });
        }
        return postDto;
    }

    public PostDtoOut updatePost(PostDtoIn dto, long postId, long userId) {
        userStorage.existingCheck(userId);
        postStorage.existingCheck(postId);
        Post post = postStorage.findById(postId).get();

        if (post.getUser().getId() == userId) {
            post.setPostContent(dto.getPostContent());
            post.setPhotoUrl(dto.getPhotoUrl());
            post = postStorage.save(post);
            log.info("Обновлен пост " + postId);
            return PostMapper.pojoToDto(post);
        } else {
            throw new AccessException("User is not author of the post");
        }
    }

    public PostDtoOut findPost(long postId, Long userId) {
        postStorage.existingCheck(postId);
        Post post = postStorage.findById(postId).get();

        return addReactionToPostIfExists(post, userId);
    }

    public void addReaction(long postId, long userId, ReactionType type) {
        removeReaction(postId, userId);

        userStorage.existingCheck(userId);
        postStorage.existingCheck(postId);
        User user = userStorage.findById(userId).get();
        Post post = postStorage.findById(postId).get();

        Reaction reaction = new Reaction();
        reaction.setReactionType(type);
        reaction.setPost(post);
        reaction.setUser(user);
        reactionStorage.save(reaction);
    }

    public void removeReaction(long postId, long userId) {
        reactionStorage.findByUserIdAndPostId(userId, postId).ifPresent(reactionStorage::delete);
    }
}
