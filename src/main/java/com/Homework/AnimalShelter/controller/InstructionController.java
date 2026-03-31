package com.Homework.AnimalShelter.controller;

import com.Homework.AnimalShelter.exception.ResourceNotFoundException;
import com.Homework.AnimalShelter.model.Instruction;
import com.Homework.AnimalShelter.service.InstructionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Контроллер для управления инструкциями
 * Обрабатывает HTTP‑запросы, связанные с инструкциями для приютов
 */
@RestController
@RequestMapping("/api/instructions")
@Tag(name = "Управление инструкциями", description = "API для управления инструкциями для приютов")
public class InstructionController {

    @Autowired
    private InstructionService instructionService;

    /**
     * Получение всех инструкций
     * HTTP GET /api/instructions
     * @return Список всех инструкций в системе
     */
    @GetMapping
    @Operation(description = "Получить все инструкции")
    public ResponseEntity<List<Instruction>> getAllInstructions() {
        return ResponseEntity.ok(instructionService.getAllInstructions());
    }

    /**
     * Добавление новой инструкции
     * HTTP POST /api/instructions
     * @param instruction Объект инструкции для создания
     * @return Созданная инструкция с HTTP‑статусом 201 (CREATED)
     */
    @PostMapping
    @Operation(description = "Добавить новую инструкцию")
    public ResponseEntity<Instruction> createInstruction(@RequestBody Instruction instruction) {
        Instruction createdInstruction = instructionService.createInstruction(instruction);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdInstruction);
    }

    /**
     * Получение инструкции по ID
     * HTTP GET /api/instructions/{id}
     * @param id ID инструкции для поиска
     * @return Найденная инструкция или исключение, если не найдена
     */
    @GetMapping("/{id}")
    @Operation(description = "Получить инструкцию по ID")
    public ResponseEntity<Instruction> getInstructionById(@PathVariable Long id) {
        Instruction instruction = instructionService.getInstructionById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Инструкция не найдена"));
        return ResponseEntity.ok(instruction);
    }

    /**
     * Обновление существующей инструкции
     * HTTP PUT /api/instructions/{id}
     * @param id ID инструкции для обновления
     * @param instruction Обновлённые данные инструкции
     * @return Обновлённая инструкция
     */
    @PutMapping("/{id}")
    @Operation(description = "Обновить инструкцию")
    public ResponseEntity<Instruction> updateInstruction(@PathVariable Long id, @RequestBody Instruction instruction) {
        Instruction updatedInstruction = instructionService.updateInstruction(id, instruction);
        return ResponseEntity.ok(updatedInstruction);
    }

    /**
     * Удаление инструкции по ID
     * HTTP DELETE /api/instructions/{id}
     * @param id ID инструкции для удаления
     * @return HTTP‑статус 204 (NO_CONTENT) при успешном удалении
     */
    @DeleteMapping("/{id}")
    @Operation(description = "Удалить инструкцию")
    public ResponseEntity<Void> deleteInstruction(@PathVariable Long id) {
        instructionService.deleteInstruction(id);
        return ResponseEntity.noContent().build();
    }
}
