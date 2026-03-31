package com.Homework.AnimalShelter.repositor;

import com.Homework.AnimalShelter.model.Shelter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShelterRepository extends JpaRepository<Shelter, Long> {
    List<Shelter> findByName(String name);

    List<Shelter> findByType(String type);

    List<Shelter> findByNameContaining(String name);

    List<Shelter> findByAddress(String address);

    List<Shelter> findByPhone(String phone);

    List<Shelter> findByEmail(String email);
}
