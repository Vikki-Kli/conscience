package ru.petprojectgroup.conscience.storage.comment;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.petprojectgroup.conscience.model.comment.Comment;

import java.util.Collection;
import java.util.NoSuchElementException;

public interface CommentStorage extends JpaRepository<Comment, Long> {
    Collection<Comment> findAllByPostId(long postId);

    default Comment existingCheck(long commentId) {
        return findById(commentId)
                .orElseThrow(() -> new NoSuchElementException("Комментарий " + commentId + " не найден"));
    }
    // TODO: разнести метод на два, один для проверки, другой для возврата значения из БД.
    //  Использовать стандартный метод JPA репозитория existsById(). Аналогично для UserStorage
}
