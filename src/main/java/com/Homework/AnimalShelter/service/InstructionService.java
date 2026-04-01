package com.Homework.AnimalShelter.service;


import com.Homework.AnimalShelter.model.Instruction;
import com.Homework.AnimalShelter.repositor.InstructionRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InstructionService {

    private final InstructionRepository instructionRepository;

    public InstructionService(InstructionRepository instructionRepository) {
        this.instructionRepository = instructionRepository;
    }

    /**
     * Получение всех инструкций
     * @return Список всех инструкций в системе
     */
    public List<Instruction> getAllInstructions() {
        return instructionRepository.findAll();
    }

    /**
     * Создание новой инструкции
     * @param instruction Объект инструкции для сохранения
     * @return Сохранённая инструкция с присвоенным ID
     */
    public Instruction createInstruction(Instruction instruction) {
        return instructionRepository.save(instruction);
    }

    /**
     * Поиск инструкции по ID
     * @param id ID инструкции
     * @return Optional с инструкцией, если найдена
     */
    public Optional<Instruction> getInstructionById(Long id) {
        return instructionRepository.findById(id);
    }

    /**
     * Обновление существующей инструкции
     * @param id ID инструкции для обновления
     * @param instruction Обновлённые данные инструкции
     * @return Обновлённая инструкция
     */
    public Instruction updateInstruction(Long id, Instruction instruction) {
        Instruction existingInstruction = instructionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Инструкция не найдена"));
        existingInstruction.setTitle(instruction.getTitle());
        existingInstruction.setContent(instruction.getContent());
        existingInstruction.setShelter(instruction.getShelter());
        return instructionRepository.save(existingInstruction);
    }

    /**
     * Удаление инструкции по ID
     * @param id ID инструкции для удаления
     */
    public void deleteInstruction(Long id) {
        instructionRepository.deleteById(id);
    }
}
