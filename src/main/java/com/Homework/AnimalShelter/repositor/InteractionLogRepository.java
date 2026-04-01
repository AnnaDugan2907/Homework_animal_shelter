package com.Homework.AnimalShelter.repositor;


import com.Homework.AnimalShelter.model.InteractionLog;
import com.pengrad.telegrambot.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface InteractionLogRepository extends JpaRepository<InteractionLog, Long> {
    List<InteractionLog> findByUserId(Long userId);

    List<InteractionLog> findByInteractionType(String interactionType);

    List<InteractionLog> findByTimestampBetween(Date startDate, Date endDate);

    List<InteractionLog> findByTimestampAfter(Date timestamp);

    List<InteractionLog> findByUserIdAndTimestampBetween(
            Long userId, Date startDate, Date endDate);
}
