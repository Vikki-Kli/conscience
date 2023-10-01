package ru.petrojectgroup.conscience.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.petrojectgroup.conscience.model.user.UserDto;
import ru.petrojectgroup.conscience.service.UserService;

import java.util.Collection;

@RestController
@RequestMapping("/users")
@Tag(name = "Пользователи", description = "Взаимодействие с пользователями")
//@CrossOrigin(origins = "http://192.168.0.103")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    @Operation(summary = "Создание пользователя")
    public UserDto createUser(@RequestBody @Valid UserDto user) {
        return userService.createUser(user);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Удаление пользователя")
    public void deleteUser(@PathVariable long id) {
        userService.deleteUser(id);
    }

    @GetMapping
    @Operation(summary = "Запрос всех пользователей")
    public Collection<UserDto> findAll() {
        return userService.findAll();
    }

    @PutMapping("/{id}")
    @Operation(summary = "Редактирование пользователя")
    public UserDto updateUser(@RequestBody @Valid UserDto user, @PathVariable long id) {
        return userService.updateUser(user, id);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Запрос пользователя")
    public UserDto findUser(@PathVariable long id) {
        return userService.findUser(id);
    }
}
