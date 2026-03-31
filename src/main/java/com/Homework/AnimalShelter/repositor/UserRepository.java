package com.Homework.AnimalShelter.repositor;


import com.Homework.AnimalShelter.model.ShelterUser;
import com.pengrad.telegrambot.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<ShelterUser, Long> {
    Optional<ShelterUser> findByTelegramId(String telegramId);

    List<User> findByIsNewTrue();

    List<User> findByShelterId(Long shelterId);
}

