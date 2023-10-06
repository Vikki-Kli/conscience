package ru.petprojectgroup.conscience.storage.comment;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.petprojectgroup.conscience.model.comment.Comment;

import java.util.Collection;
import java.util.NoSuchElementException;

public interface CommentStorage extends JpaRepository<Comment, Long> {
    Collection<Comment> findAllByPostId(long postId);

    default boolean existingCheck(long commentId) {
        if (findById(commentId).isPresent()) {
            return true;
        } else {
            throw new NoSuchElementException("Комментарий " + commentId + " не найден");
        }
    }
}
