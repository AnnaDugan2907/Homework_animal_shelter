package com.Homework.AnimalShelter.repositor;

import com.Homework.AnimalShelter.model.AdoptionStage;
import com.Homework.AnimalShelter.model.PetAdopter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdoptionStageRepository extends JpaRepository<AdoptionStage, Long> {
    List<AdoptionStage> findByAdopter(PetAdopter adopter);

    List<AdoptionStage> findByStage(String stage);

    List<AdoptionStage> findByAdopterAndStage(PetAdopter adopter, String stage);

    List<AdoptionStage> findByStartDateBetween(
            java.util.Date startDate, java.util.Date endDate);
}
