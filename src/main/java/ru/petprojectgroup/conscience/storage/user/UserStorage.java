package ru.petprojectgroup.conscience.storage.user;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.petprojectgroup.conscience.model.user.User;

import java.util.NoSuchElementException;

public interface UserStorage extends JpaRepository<User, Long> {
    default User existingCheck(long id) {
        return findById(id)
                .orElseThrow(() -> new NoSuchElementException("Пользователь " + id + " не найден"));
    }
}
