package com.Homework.AnimalShelter.controller;

import com.Homework.AnimalShelter.model.Shelter;
import com.Homework.AnimalShelter.service.ShelterService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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
    public ResponseEntity<List<Shelter>> getAllShelters() {
        return ResponseEntity.ok(shelterService.getAllShelters());
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
    public ResponseEntity<Shelter> createShelter(@RequestBody Shelter shelter) {
        Shelter createdShelter = shelterService.createShelter(shelter);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdShelter);
    }

    /**
     * Получение приюта по ID
     * HTTP GET /api/shelters/{id}
     *
     * @param id ID приюта
     * @return Приют с указанным ID или 404, если не найден
     */
    @GetMapping("/{id}")
    @Operation(description = "Получить приют по ID")
    public ResponseEntity<Shelter> getShelterById(@PathVariable Long id) {
        Optional<Shelter> shelter = shelterService.getShelterById(id);
        if (shelter.isPresent()) {
            return ResponseEntity.ok(shelter.get());
        } else {
            return ResponseEntity.notFound().build();
        }
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
    public ResponseEntity<List<Shelter>> findSheltersByType(@PathVariable String type) {
        List<Shelter> shelters = shelterService.findSheltersByType(type);
        return ResponseEntity.ok(shelters);
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
    public ResponseEntity<List<Shelter>> searchSheltersByName(@RequestParam String name) {
        List<Shelter> shelters = shelterService.findSheltersByNameContaining(name);
        return ResponseEntity.ok(shelters);
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
    public ResponseEntity<Shelter> updateShelter(@PathVariable Long id, @RequestBody Shelter shelter) {
        Shelter updatedShelter = shelterService.updateShelter(id, shelter);
        return ResponseEntity.ok(updatedShelter);
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
    public ResponseEntity<Void> deleteShelter(@PathVariable Long id) {
        shelterService.deleteShelter(id);
        return ResponseEntity.noContent().build();
    }
}
