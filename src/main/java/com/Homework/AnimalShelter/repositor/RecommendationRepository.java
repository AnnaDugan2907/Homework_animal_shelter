package com.Homework.AnimalShelter.repositor;


import com.Homework.AnimalShelter.info.ApplicableFor;
import com.Homework.AnimalShelter.info.RecommendationType;
import com.Homework.AnimalShelter.model.Recommendation;
import com.Homework.AnimalShelter.model.Shelter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecommendationRepository extends JpaRepository<Recommendation, Long> {
    List<Recommendation> findByShelterId(Long shelterId);

    List<Recommendation> findByType(RecommendationType type);

    List<Recommendation> findByApplicableFor(ApplicableFor applicableFor);

    List<Recommendation> findByShelterIdAndType(Long shelterId, RecommendationType type);

    List<Recommendation> findByShelterIdAndApplicableFor(Long shelterId, ApplicableFor applicableFor);
}
