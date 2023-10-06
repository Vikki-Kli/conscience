package ru.petprojectgroup.conscience.storage.reaction;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.petprojectgroup.conscience.model.post.reaction.Reaction;

import java.util.Optional;


public interface ReactionStorage extends JpaRepository<Reaction, Long> {
    void deleteByUserIdAndPostId(long userId, long postId);
    Optional<Reaction> findByUserIdAndPostId(long userId, long postId);
}
