package com.Homework.AnimalShelter.repositor;

import com.Homework.AnimalShelter.model.PetAdopter;
import com.Homework.AnimalShelter.model.Shelter;
import com.pengrad.telegrambot.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface PetAdopterRepository extends JpaRepository<PetAdopter, Long> {
    List<PetAdopter> findByAdoptionStatus(String adoptionStatus);

    List<PetAdopter> findByUserId(Integer userId);

    List<PetAdopter> findByShelterId(Integer shelterId);

    List<PetAdopter> findByPetType(String petType);

    Optional<PetAdopter> findByIdAndShelterId(Long id, Integer shelterId);

    List<PetAdopter> findByTrialEndDateBefore(LocalDate date);
}
