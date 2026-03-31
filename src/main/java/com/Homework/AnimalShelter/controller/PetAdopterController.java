package com.Homework.AnimalShelter.controller;

import com.Homework.AnimalShelter.exception.ResourceNotFoundException;
import com.Homework.AnimalShelter.model.PetAdopter;
import com.Homework.AnimalShelter.service.PetAdopterService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

/**
 * Контроллер для управления кандидатами на усыновление
 * Обрабатывает HTTP‑запросы, связанные с кандидатами на усыновление питомцев
 */
@RestController
@RequestMapping("/api/pet-adopters")
@Tag(name = "Управление кандидатами на усыновление", description = "API для управления кандидатами на усыновление питомцев")
public class PetAdopterController {

    @Autowired
    private PetAdopterService petAdopterService;

    /**
     * Получение списка всех кандидатов на усыновление
     * HTTP GET /api/pet-adopters
     * @return Список всех кандидатов
     */
    @GetMapping
    @Operation(description = "Получить всех кандидатов на усыновление")
    public ResponseEntity<List<PetAdopter>> getAllPetAdopters() {
        return ResponseEntity.ok(petAdopterService.getAllPetAdopters());
    }

    /**
     * Получение кандидата по ID
     * HTTP GET /api/pet-adopters/{id}
     * @param id ID кандидата для поиска
     * @return Найденный кандидат или исключение, если не найден
     */
    @GetMapping("/{id}")
    @Operation(description = "Получить кандидата по ID")
    public ResponseEntity<PetAdopter> getPetAdopterById(@PathVariable Long id) {
        PetAdopter petAdopter = petAdopterService.getPetAdopterById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Кандидат не найден"));
        return ResponseEntity.ok(petAdopter);
    }

    /**
     * Добавление нового кандидата на усыновление
     * HTTP POST /api/pet-adopters
     * @param petAdopter Объект кандидата для создания
     * @return Созданный кандидат с HTTP-статусом 201 (CREATED)
     */
    @PostMapping
    @Operation(description = "Добавить нового кандидата на усыновление")
    public ResponseEntity<PetAdopter> createPetAdopter(@RequestBody PetAdopter petAdopter) {
        PetAdopter createdPetAdopter = petAdopterService.createPetAdopter(petAdopter);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdPetAdopter);
    }

    /**
     * Получение кандидатов по статусу усыновления
     * HTTP GET /api/pet-adopters/status/{status}
     * @param status Статус усыновления (например, "подготовка", "одобрено", "отказано", "продление")
     * @return Список кандидатов с указанным статусом
     */
    @GetMapping("/status/{status}")
    @Operation(description = "Получить кандидатов по статусу усыновления")
    public ResponseEntity<List<PetAdopter>> getPetAdoptersByStatus(@PathVariable String status) {
        return ResponseEntity.ok(petAdopterService.findByAdoptionStatus(status));
    }

    /**
     * Получение кандидатов по ID пользователя
     * HTTP GET /api/pet-adopters/user/{userId}
     * @param userId ID пользователя
     * @return Список кандидатов, связанных с пользователем
     */
    @GetMapping("/user/{userId}")
    @Operation(description = "Получить кандидатов по ID пользователя")
    public ResponseEntity<List<PetAdopter>> getPetAdoptersByUserId(@PathVariable Integer userId) {
        return ResponseEntity.ok(petAdopterService.findByUserId(userId));
    }

    /**
     * Получение кандидатов по ID приюта
     * HTTP GET /api/pet-adopters/shelter/{shelterId}
     * @param shelterId ID приюта
     * @return Список кандидатов, связанных с приютом
     */
    @GetMapping("/shelter/{shelterId}")
    @Operation(description = "Получить кандидатов по ID приюта")
    public ResponseEntity<List<PetAdopter>> getPetAdoptersByShelterId(@PathVariable Integer shelterId) {
        return ResponseEntity.ok(petAdopterService.findByShelterId(shelterId));
    }

    /**
     * Получение кандидатов по типу питомца
     * HTTP GET /api/pet-adopters/pet-type/{petType}
     * @param petType Тип питомца ("кошки" или "собаки")
     * @return Список кандидатов для указанного типа питомца
     */
    @GetMapping("/pet-type/{petType}")
    @Operation(description = "Получить кандидатов по типу питомца")
    public ResponseEntity<List<PetAdopter>> getPetAdoptersByPetType(@PathVariable String petType) {
        return ResponseEntity.ok(petAdopterService.findByPetType(petType));
    }

    /**
     * Получение кандидата по ID и приюту
     * HTTP GET /api/pet-adopters/{id}/shelter/{shelterId}
     * @param id ID кандидата
     * @param shelterId ID приюта
     * @return Найденный кандидат или исключение, если не найден
     */
    @GetMapping("/{id}/shelter/{shelterId}")
    @Operation(description = "Получить кандидата по ID и приюту")
    public ResponseEntity<PetAdopter> getPetAdopterByIdAndShelter(
            @PathVariable Long id,
            @PathVariable Integer shelterId) {
        PetAdopter petAdopter = petAdopterService.findByIdAndShelterId(id, shelterId)
                .orElseThrow(() -> new ResourceNotFoundException("Кандидат не найден в указанном приюте"));
        return ResponseEntity.ok(petAdopter);
    }

    /**
     * Получение кандидатов с датой окончания пробного периода до указанной даты
     * HTTP GET /api/pet-adopters/trial-end-before/{date}
     * @param date Дата в формате YYYY-MM-DD, до которой должен закончиться пробный период
     * @return Список кандидатов
     */
    @GetMapping("/trial-end-before/{date}")
    @Operation(description = "Получить кандидатов с датой окончания пробного периода до указанной даты")
    public ResponseEntity<List<PetAdopter>> getPetAdoptersByTrialEndDateBefore(
            @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        return ResponseEntity.ok(petAdopterService.findByTrialEndDateBefore(date));
    }

    /**
     * Обновление информации о кандидате
     * HTTP PUT /api/pet-adopters/{id}
     * @param id ID кандидата для обновления
     * @param updatedPetAdopter Обновлённые данные кандидата
     * @return Обновлённый кандидат
     */
    @PutMapping("/{id}")
    @Operation(description = "Обновить информацию о кандидате на усыновление")
    public ResponseEntity<PetAdopter> updatePetAdopter(
            @PathVariable Long id,
            @RequestBody PetAdopter updatedPetAdopter) {
        // Проверяем существование кандидата
        petAdopterService.getPetAdopterById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Кандидат не найден"));

        updatedPetAdopter.setId(id);
        PetAdopter savedPetAdopter = petAdopterService.createPetAdopter(updatedPetAdopter);
        return ResponseEntity.ok(savedPetAdopter);
    }

    /**
     * Удаление кандидата на усыновление
     * HTTP DELETE /api/pet-adopters/{id}
     * @param id ID кандидата для удаления
     * @return HTTP-статус 204 (NO_CONTENT) при успешном удалении
     */
    @DeleteMapping("/{id}")
    @Operation(description = "Удалить кандидата на усыновление")
    public ResponseEntity<Void> deletePetAdopter(@PathVariable Long id) {
        petAdopterService.getPetAdopterById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Кандидат не найден"));
        petAdopterService.deletePetAdopter(id);
        return ResponseEntity.noContent().build();
    }
}