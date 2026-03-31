package com.Homework.AnimalShelter.repositor;

import com.Homework.AnimalShelter.model.PetAdopter;
import com.Homework.AnimalShelter.model.PetReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface PetReportRepository extends JpaRepository<PetReport, Long> {
    List<PetReport> findByAdopter(PetAdopter adopter);


    List<PetReport> findByReportDate(LocalDate reportDate);

    List<PetReport> findByComplaintTrue();

    List<PetReport> findByReportStatus(String reportStatus);

    List<PetReport> findByAdopterId(Long adopterId);

    List<PetReport> findByAdopterAndReportDateBetween(
            PetAdopter adopter, LocalDate startDate, LocalDate endDate);
}
