package ru.petrojectgroup.conscience.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.petrojectgroup.conscience.model.User;
import ru.petrojectgroup.conscience.storage.user.dao.UserStorage;

import java.util.Collection;

@Service
public class UserService {
    private final UserStorage userStorage;

    @Autowired
    public UserService(UserStorage userStorage) {
        this.userStorage = userStorage;
    }

    public void createUser(User user) {
        userStorage.createUser(user);
    }

    public void deleteUser(long id) {
        userStorage.deleteUser(id);
    }

    public Collection<User> findAll() {
        return userStorage.findAll();
    }

    public void updateUser(User user) {
        userStorage.updateUser(user);
    }

    public User findUser(long id) {
        return userStorage.findUser(id);
    }
}