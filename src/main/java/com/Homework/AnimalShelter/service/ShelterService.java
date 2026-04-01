package com.Homework.AnimalShelter.service;

import com.Homework.AnimalShelter.model.Shelter;
import com.Homework.AnimalShelter.repositor.ShelterRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ShelterService {
    private final ShelterRepository shelterRepository;

    public ShelterService(ShelterRepository shelterRepository) {
        this.shelterRepository = shelterRepository;
    }

    /**
     * Получение всех приютов
     *
     * @return Список всех приютов в системе
     */
    public List<Shelter> getAllShelters() {
        return shelterRepository.findAll();
    }

    /**
     * Создание нового приюта
     *
     * @param shelter Объект приюта для сохранения
     * @return Сохранённый приют с присвоенным ID
     */
    public Shelter createShelter(Shelter shelter) {
        return shelterRepository.save(shelter);
    }

    /**
     * Поиск приюта по ID
     *
     * @param id ID приюта
     * @return Optional с приютом, если найден
     */
    public Optional<Shelter> getShelterById(Long id) {
        return shelterRepository.findById(id);
    }

    /**
     * Поиск приютов по типу
     *
     * @param type Тип приюта ('кошки' или 'собаки')
     * @return Список приютов указанного типа
     */
    public List<Shelter> findSheltersByType(String type) {
        return shelterRepository.findByType(type);
    }

    /**
     * Поиск приютов по имени (частичное совпадение)
     *
     * @param name Часть имени приюта для поиска
     * @return Список приютов с совпадающими именами
     */
    public List<Shelter> findSheltersByNameContaining(String name) {
        return shelterRepository.findByNameContaining(name);
    }

    /**
     * Поиск приютов по адресу
     *
     * @param address Адрес приюта
     * @return Список приютов по указанному адресу
     */
    public List<Shelter> findSheltersByAddress(String address) {
        return shelterRepository.findByAddress(address);
    }

    /**
     * Обновление существующего приюта
     *
     * @param id             ID приюта для обновления
     * @param updatedShelter Обновлённые данные приюта
     * @return Обновлённый приют
     */
    public Shelter updateShelter(Long id, Shelter updatedShelter) {
        Shelter existingShelter = shelterRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Приют с ID " + id + " не найден"));

        existingShelter.setName(updatedShelter.getName());
        existingShelter.setType(updatedShelter.getType());
        existingShelter.setAddress(updatedShelter.getAddress());
        existingShelter.setSchedule(updatedShelter.getSchedule());
        existingShelter.setContactSecurity(updatedShelter.getContactSecurity());
        existingShelter.setAccessRules(updatedShelter.getAccessRules());
        existingShelter.setSafetyGuidelines(updatedShelter.getSafetyGuidelines());
        existingShelter.setLocationMap(updatedShelter.getLocationMap());
        existingShelter.setPhone(updatedShelter.getPhone());
        existingShelter.setEmail(updatedShelter.getEmail());
        existingShelter.setDescription(updatedShelter.getDescription());

        return shelterRepository.save(existingShelter);
    }

    /**
     * Удаление приюта по ID
     *
     * @param id ID приюта для удаления
     */
    public void deleteShelter(Long id) {
        shelterRepository.deleteById(id);
    }
}
