package com.Homework.AnimalShelter.service;

import com.Homework.AnimalShelter.model.ShelterUser;
import com.Homework.AnimalShelter.repositor.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Получение всех пользователей
     * @return Список всех пользователей в системе
     */
    public List<ShelterUser> getAllUsers() {
        return userRepository.findAll();
    }

    /**
     * Поиск пользователя по ID
     * @param id ID пользователя
     * @return Optional с пользователем, если найден, или пустой Optional
     */
    public Optional<ShelterUser> getUserById(Long id) {
        return userRepository.findById(id);
    }

    /**
     * Создание нового пользователя
     * @param shelterUser Объект пользователя для сохранения
     * @return Сохранённый пользователь с присвоенным ID
     */
    public ShelterUser createUser(ShelterUser shelterUser) {
        // Устанавливаем время взаимодействия при создании
        shelterUser.setLastInteraction(LocalDateTime.now());
        return userRepository.save(shelterUser);
    }

    /**
     * Поиск пользователя по Telegram ID
     * @param telegramId Telegram ID пользователя
     * @return Optional с пользователем, если найден
     */
    public Optional<ShelterUser> findByTelegramId(String telegramId) {
        return userRepository.findByTelegramId(telegramId);
    }

    /**
     * Удаление пользователя по ID
     * @param id ID пользователя для удаления
     * @return true, если пользователь был удалён, false, если не найден
     */
    @Transactional
    public boolean deleteUser(Long id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
            return true;
        }
        return false;
    }
}

