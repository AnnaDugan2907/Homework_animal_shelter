package com.Homework.AnimalShelter.repositor;

import com.Homework.AnimalShelter.model.PetAdopter;
import com.Homework.AnimalShelter.model.Reminder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ReminderRepository extends JpaRepository<Reminder, Long> {

    List<Reminder> findByAdopterId(Long adopterId);

    List<Reminder> findByReminderDate(LocalDate reminderDate);

    List<Reminder> findBySentFalse();

    List<Reminder> findByProcessedFalse();

    List<Reminder> findByReminderType(String reminderType);

    List<Reminder> findByAdopterIdAndReminderDateAfter(Long adopterId, LocalDate date);
}
