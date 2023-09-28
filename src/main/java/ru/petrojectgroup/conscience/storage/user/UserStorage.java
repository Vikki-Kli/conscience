package ru.petrojectgroup.conscience.storage.user;

import ru.petrojectgroup.conscience.model.User;

import java.util.Collection;

public interface UserStorage {
    Collection<User> findAll();

    void deleteUser(long id);

    User createUser(User user);

    User updateUser(User user);

    User findUser(long id);
}
