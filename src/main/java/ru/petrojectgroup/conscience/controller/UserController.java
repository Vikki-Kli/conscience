package ru.petrojectgroup.conscience.controller;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.petrojectgroup.conscience.model.user.UserDto;
import ru.petrojectgroup.conscience.service.UserService;

import java.util.Collection;

@RestController
@Slf4j
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public UserDto createUser(@RequestBody @Valid UserDto user) {
        return userService.createUser(user);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable long id) {
        userService.deleteUser(id);
    }

    @GetMapping
    public Collection<UserDto> findAll() {
        return userService.findAll();
    }

    @PutMapping("/{id}")
    public UserDto updateUser(@RequestBody @Valid UserDto user, @PathVariable long id) {
        return userService.updateUser(user, id);
    }

    @GetMapping("/{id}")
    public UserDto findUser(@PathVariable long id) {
        return userService.findUser(id);
    }
}
