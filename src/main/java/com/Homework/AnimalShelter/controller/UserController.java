package com.Homework.AnimalShelter.controller;

import com.Homework.AnimalShelter.exception.ResourceNotFoundException;
import com.Homework.AnimalShelter.model.ShelterUser;
import com.Homework.AnimalShelter.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

/**
 * Контроллер для управления пользователями системы
 * Обрабатывает HTTP‑запросы, связанные с пользователями
 */
@RestController
@RequestMapping("/api/users")
@Tag(name = "Управление пользователями", description = "API для управления пользователями системы")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * Получение списка всех пользователей
     * HTTP GET /api/users
     *
     * @return Список всех зарегистрированных пользователей
     */
    @GetMapping
    @Operation(description = "Получить всех пользователей")
    public List<ShelterUser> getAllUsers() {
        return userService.getAllUsers();
    }

    /**
     * Получение пользователя по ID
     * HTTP GET /api/users/{id}
     *
     * @param id ID пользователя для поиска
     * @return Найденный пользователь или исключение, если не найден
     */
    @GetMapping("/{id}")
    @Operation(description = "Получить пользователя по ID")
    public ShelterUser getUserById(@PathVariable Long id) {
        return userService.getUserById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Пользователь не найден"));
    }

    /**
     * Добавление нового пользователя
     * HTTP POST /api/users
     *
     * @param shelterUser Объект пользователя для создания
     * @return Созданный пользователь с HTTP-статусом 201 (CREATED)
     */
    @PostMapping
    @Operation(description = "Добавить нового пользователя")
    @ResponseStatus(HttpStatus.CREATED)
    public ShelterUser createUser(@RequestBody ShelterUser shelterUser) {
        return userService.createUser(shelterUser);
    }

    /**
     * Поиск пользователя по Telegram ID
     * HTTP GET /api/users/telegram/{telegramId}
     *
     * @param telegramId Telegram ID пользователя для поиска
     * @return Найденный пользователь или исключение, если не найден
     */
    @GetMapping("/telegram/{telegramId}")
    @Operation(description = "Найти пользователя по Telegram ID")
    public ShelterUser findByTelegramId(@PathVariable String telegramId) {
        return userService.findByTelegramId(telegramId)
                .orElseThrow(() -> new ResourceNotFoundException("Пользователь с указанным Telegram ID не найден"));
    }


    /**
     * Обновление существующего пользователя
     * HTTP PUT /api/users/{id}
     *
     * @param id          ID пользователя для обновления
     * @param shelterUser Обновлённые данные пользователя
     * @return Обновлённый пользователь
     */
    @PutMapping("/{id}")
    @Operation(description = "Обновить существующего пользователя")
    public ShelterUser updateUser(@PathVariable Long id, @RequestBody ShelterUser shelterUser) {
        return userService.getUserById(id)
                .map(existingShelterUser -> {
                    // Обновляем поля
                    existingShelterUser.setUsername(shelterUser.getUsername());
                    existingShelterUser.setFirstName(shelterUser.getFirstName());
                    existingShelterUser.setLastName(shelterUser.getLastName());
                    existingShelterUser.setEmail(shelterUser.getEmail());
                    existingShelterUser.setPhone(shelterUser.getPhone());
                    existingShelterUser.setContactInfo(shelterUser.getContactInfo());
                    existingShelterUser.setNew(shelterUser.isNew());
                    existingShelterUser.setTelegramId(shelterUser.getTelegramId());
                    existingShelterUser.setShelter(shelterUser.getShelter());

                    return userService.createUser(existingShelterUser); // используем тот же метод для сохранения
                })
                .orElseThrow(() -> new ResourceNotFoundException("Пользователь не найден"));
    }

    /**
     * Удаление пользователя по ID
     * HTTP DELETE /api/users/{id}
     *
     * @param id ID пользователя для удаления
     * @return HTTP-статус 204 (NO_CONTENT) при успешном удалении,
     * 404 (NOT_FOUND) если пользователь не найден
     */
    @DeleteMapping("/{id}")
    @Operation(description = "Удалить пользователя по ID")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable Long id) {
        boolean isDeleted = userService.deleteUser(id);
        if (!isDeleted) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }
}