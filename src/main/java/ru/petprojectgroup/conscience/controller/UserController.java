package ru.petprojectgroup.conscience.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import ru.petprojectgroup.conscience.model.user.UserDto;
import ru.petprojectgroup.conscience.service.UserService;

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

    @DeleteMapping
    @Operation(summary = "Удаление пользователя")
    public void deleteUser(@RequestHeader("X-Conscience-User-Id") @Parameter(description = "id пользователя, отправляющего запрос") long userId) {
        userService.deleteUser(userId);
    }

    @GetMapping
    @Operation(summary = "Запрос всех пользователей")
    public Collection<UserDto> findAll() {
        return userService.findAll();
    }

    @PutMapping
    @Operation(summary = "Редактирование пользователя")
    public UserDto updateUser(@RequestBody @Valid UserDto user, @RequestHeader("X-Conscience-User-Id") @Parameter(description = "id пользователя, отправляющего запрос") long userId) {
        return userService.updateUser(user, userId);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Запрос пользователя")
    public UserDto findUser(@PathVariable long id) {
        return userService.findUser(id);
    }
}
