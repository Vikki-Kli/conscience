package ru.petprojectgroup.conscience.storage.post;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.petprojectgroup.conscience.model.post.Post;

import java.util.Collection;
import java.util.NoSuchElementException;

public interface PostStorage extends JpaRepository<Post, Long> {
    Collection<Post> findAllByUser_id(long id);

    default void existingCheck(long id) {
        if (!existsById(id)) throw new NoSuchElementException("Пост " + id + " не найден");
    }
}
