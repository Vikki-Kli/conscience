package ru.petrojectgroup.conscience.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.petrojectgroup.conscience.model.user.User;
import ru.petrojectgroup.conscience.model.user.UserDto;
import ru.petrojectgroup.conscience.model.user.UserMapper;
import ru.petrojectgroup.conscience.storage.user.UserStorage;

import java.util.Collection;
import java.util.NoSuchElementException;

@Service
@Slf4j
public class UserService {
    private final UserStorage userStorage;

    @Autowired
    public UserService(UserStorage userStorage) {
        this.userStorage = userStorage;
    }

    public UserDto createUser(UserDto dto) {
        User user = userStorage.save(UserMapper.dtoToPojo(dto));
        log.info("Создан пользователь " + user);
        return UserMapper.pojoToDto(user);
    }

    public void deleteUser(long id) {
        findUser(id);
        userStorage.deleteById(id);
        log.info("Удален пользователь " + id);
    }

    public Collection<UserDto> findAll() {
        return userStorage.findAll().stream().map(UserMapper::pojoToDto).toList();
    }

    public UserDto updateUser(UserDto userDto, long id) {
        userStorage.existingCheck(id);
        User user = UserMapper.dtoToPojo(userDto);
        user.setId(id);

        User savedUser = userStorage.save(user);
        log.info("Изменен пользователь " + savedUser);
        return UserMapper.pojoToDto(savedUser);
    }

    public UserDto findUser(long id) {
        return UserMapper.pojoToDto(userStorage.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Пользователь " + id + " не найден")));
    }
}
