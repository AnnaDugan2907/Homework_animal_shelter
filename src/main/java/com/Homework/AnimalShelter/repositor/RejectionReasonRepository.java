package com.Homework.AnimalShelter.repositor;


import com.Homework.AnimalShelter.model.RejectionReason;
import com.Homework.AnimalShelter.model.Shelter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RejectionReasonRepository extends JpaRepository<RejectionReason, Long> {
    List<RejectionReason> findByShelter(Shelter shelter);

    List<RejectionReason> findByCategory(String category);

    List<RejectionReason> findByReasonContainingIgnoreCase(String reason);
}
