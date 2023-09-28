package ru.petrojectgroup.conscience.storage.user;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.petrojectgroup.conscience.model.post.Post;
import ru.petrojectgroup.conscience.model.user.User;

import java.util.NoSuchElementException;

public interface UserStorage extends JpaRepository<User, Long> {
    default User existingCheck(long id) {
        return findById(id)
                .orElseThrow(() -> new NoSuchElementException("Пользователь " + id + " не найден"));
    }
}
