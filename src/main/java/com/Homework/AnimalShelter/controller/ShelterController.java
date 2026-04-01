package com.Homework.AnimalShelter.controller;

import com.Homework.AnimalShelter.model.Shelter;
import com.Homework.AnimalShelter.service.ShelterService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

/**
 * Контроллер для управления приютами животных
 * Обрабатывает HTTP‑запросы, связанные с приютами
 */
@RestController
@RequestMapping("/api/shelters")
@Tag(name = "Управление приютами", description = "API для управления приютами животных")
public class ShelterController {

    @Autowired
    private ShelterService shelterService;

    /**
     * Получение списка всех приютов
     * HTTP GET /api/shelters
     *
     * @return Список всех приютов в системе
     */
    @GetMapping
    @Operation(description = "Получить все приюты")
    public List<Shelter> getAllShelters() {
        return shelterService.getAllShelters();
    }

    /**
     * Добавление нового приюта
     * HTTP POST /api/shelters
     *
     * @param shelter Объект приюта для создания
     * @return Созданный приют с HTTP-статусом 201 (CREATED)
     */
    @PostMapping
    @Operation(description = "Добавить новый приют")
    @ResponseStatus(HttpStatus.CREATED)
    public Shelter createShelter(@RequestBody Shelter shelter) {
        return shelterService.createShelter(shelter);
    }

    /**
     * Получение приюта по ID
     * HTTP GET /api/shelters/{id}
     *
     * @param id ID приюта
     * @return Приют с указанным ID или 404, если не найден
     */
    public Shelter getShelterById(@PathVariable Long id) {
        return shelterService.getShelterById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Приют не найден"));
    }

    /**
     * Поиск приютов по типу
     * HTTP GET /api/shelters/type/{type}
     *
     * @param type Тип приюта ('кошки' или 'собаки')
     * @return Список приютов указанного типа
     */
    @GetMapping("/type/{type}")
    @Operation(description = "Найти приюты по типу")
    public List<Shelter> findSheltersByType(@PathVariable String type) {
        return shelterService.findSheltersByType(type);
    }

    /**
     * Поиск приютов по части имени
     * HTTP GET /api/shelters/search?name={name}
     *
     * @param name Часть имени для поиска
     * @return Список приютов с совпадающими именами
     */
    @GetMapping("/search")
    @Operation(description = "Поиск приютов по имени")
    public List<Shelter> searchSheltersByName(@RequestParam String name) {
        return shelterService.findSheltersByNameContaining(name);
    }

    /**
     * Обновление приюта
     * HTTP PUT /api/shelters/{id}
     *
     * @param id      ID приюта для обновления
     * @param shelter Обновлённые данные приюта
     * @return Обновлённый приют
     */
    @PutMapping("/{id}")
    @Operation(description = "Обновить приют")
    public Shelter updateShelter(@PathVariable Long id, @RequestBody Shelter shelter) {
        return shelterService.updateShelter(id, shelter);
    }

    /**
     * Удаление приюта
     * HTTP DELETE /api/shelters/{id}
     *
     * @param id ID приюта для удаления
     * @return HTTP-статус 204 (NO_CONTENT)
     */
    @DeleteMapping("/{id}")
    @Operation(description = "Удалить приют")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteShelter(@PathVariable Long id) {
        shelterService.deleteShelter(id);
    }
}
