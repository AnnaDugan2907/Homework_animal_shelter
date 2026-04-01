package com.Homework.AnimalShelter.service;

import com.Homework.AnimalShelter.model.PetAdopter;
import com.Homework.AnimalShelter.repositor.PetAdopterRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class PetAdopterService {

    private final PetAdopterRepository petAdopterRepository;

    public PetAdopterService(PetAdopterRepository petAdopterRepository) {
        this.petAdopterRepository = petAdopterRepository;
    }

    /**
     * Получение всех кандидатов на усыновление
     * @return Список всех кандидатов
     */
    public List<PetAdopter> getAllPetAdopters() {
        return petAdopterRepository.findAll();
    }

    /**
     * Поиск кандидата по ID
     * @param id ID кандидата
     * @return Optional с кандидатом, если найден
     */
    public Optional<PetAdopter> getPetAdopterById(Long id) {
        return petAdopterRepository.findById(id);
    }

    /**
     * Создание нового кандидата на усыновление
     * @param petAdopter Объект кандидата для сохранения
     * @return Сохранённый кандидат с присвоенным ID
     */
    public PetAdopter createPetAdopter(PetAdopter petAdopter) {
        return petAdopterRepository.save(petAdopter);
    }

    /**
     * Поиск кандидатов по статусу усыновления
     * @param status Статус усыновления (например, "подготовка", "одобрено")
     * @return Список кандидатов с указанным статусом
     */
    public List<PetAdopter> findByAdoptionStatus(String status) {
        return petAdopterRepository.findByAdoptionStatus(status);
    }

    /**
     * Поиск кандидатов по ID пользователя
     * @param userId ID пользователя
     * @return Список кандидатов, связанных с пользователем
     */
    public List<PetAdopter> findByUserId(Integer userId) {
        return petAdopterRepository.findByUserId(userId);
    }

    /**
     * Поиск кандидатов по ID приюта
     * @param shelterId ID приюта
     * @return Список кандидатов, связанных с приютом
     */
    public List<PetAdopter> findByShelterId(Integer shelterId) {
        return petAdopterRepository.findByShelterId(shelterId);
    }

    /**
     * Поиск кандидатов по типу питомца
     * @param petType Тип питомца ("кошки" или "собаки")
     * @return Список кандидатов для указанного типа питомца
     */
    public List<PetAdopter> findByPetType(String petType) {
        return petAdopterRepository.findByPetType(petType);
    }

    /**
     * Поиск кандидата по ID и приюту
     * @param id ID кандидата
     * @param shelterId ID приюта
     * @return Optional с кандидатом, если найден в указанном приюте
     */
    public Optional<PetAdopter> findByIdAndShelterId(Long id, Integer shelterId) {
        return petAdopterRepository.findByIdAndShelterId(id, shelterId);
    }

    /**
     * Поиск кандидатов с датой окончания пробного периода до указанной даты
     * @param date Дата, до которой должен закончиться пробный период
     * @return Список кандидатов
     */
    public List<PetAdopter> findByTrialEndDateBefore(LocalDate date) {
        return petAdopterRepository.findByTrialEndDateBefore(date);
    }


    /**
     * Удаление кандидата на усыновление по ID
     * @param id ID кандидата для удаления
     */
    public void deletePetAdopter(Long id) {
        petAdopterRepository.deleteById(id);
    }
}
