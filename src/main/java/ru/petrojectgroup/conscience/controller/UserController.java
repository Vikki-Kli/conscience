package ru.petrojectgroup.conscience.controller;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.petrojectgroup.conscience.model.User;
import ru.petrojectgroup.conscience.service.UserService;

import java.util.Collection;

@RestController
@Slf4j
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public User createUser(@RequestBody @Valid User user) {
        userService.createUser(user);
        return user;
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable long id) {
        userService.deleteUser(id);
        log.info("Пользователь с id {} был удален", id);
    }

    @GetMapping
    public Collection<User> findAll() {
        return userService.findAll();
    }

    @PutMapping
    public User updateUser(@RequestBody @Valid User user) {
        userService.updateUser(user);
        return user;
    }

    @GetMapping("/{id}")
    public User findUser(@PathVariable long id) {
        return userService.findUser(id);
    }
}
