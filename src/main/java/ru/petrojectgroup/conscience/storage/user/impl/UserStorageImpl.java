package ru.petrojectgroup.conscience.storage.user.impl;

import ru.petrojectgroup.conscience.model.User;
import ru.petrojectgroup.conscience.storage.user.dao.UserStorage;

import java.util.Collection;

public class UserStorageImpl implements UserStorage {

    @Override
    public Collection<User> findAll() {
       return null;
    }

    @Override
    public void deleteUser(long id) {

    }


    @Override
    public User createUser(User user) {
        return null;
    }

    @Override
    public User updateUser(User user) {
        return null;
    }

    @Override
    public User findUser(long userId) {
        return null;
    }
}
