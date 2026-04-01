package com.Homework.AnimalShelter.repositor;


import com.Homework.AnimalShelter.model.Instruction;
import com.Homework.AnimalShelter.model.Shelter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InstructionRepository extends JpaRepository<Instruction, Long> {
    List<Instruction> findByShelter(Shelter shelter);

    List<Instruction> findByCategory(String category);

    List<Instruction> findByApplicableFor(String applicableFor);

    List<Instruction> findByTitleContainingIgnoreCase(String title);

    List<Instruction> findByShelterAndCategory(Shelter shelter, String category);
}
