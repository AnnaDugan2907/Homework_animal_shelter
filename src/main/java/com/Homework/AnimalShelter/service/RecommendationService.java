package com.Homework.AnimalShelter.service;

import com.Homework.AnimalShelter.info.ApplicableFor;
import com.Homework.AnimalShelter.info.RecommendationType;
import com.Homework.AnimalShelter.model.Recommendation;
import com.Homework.AnimalShelter.repositor.RecommendationRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RecommendationService {

    private final RecommendationRepository recommendationRepository;

    public RecommendationService(RecommendationRepository recommendationRepository) {
        this.recommendationRepository = recommendationRepository;
    }

    /**
     * Получение всех рекомендаций
     *
     * @return Список всех рекомендаций в системе
     */
    public List<Recommendation> getAllRecommendations() {
        return recommendationRepository.findAll();
    }

    /**
     * Создание новой рекомендации
     *
     * @param recommendation Объект рекомендации для сохранения
     * @return Сохранённая рекомендация с присвоенным ID
     */
    public Recommendation createRecommendation(Recommendation recommendation) {
        return recommendationRepository.save(recommendation);
    }

    /**
     * Поиск рекомендации по ID
     *
     * @param id ID рекомендации
     * @return Optional с рекомендацией, если найдена
     */
    public Optional<Recommendation> getRecommendationById(Long id) {
        return recommendationRepository.findById(id);
    }

    /**
     * Поиск рекомендаций по ID приюта
     *
     * @param shelterId ID приюта
     * @return Список рекомендаций для указанного приюта
     */
    public List<Recommendation> findByShelterId(Long shelterId) {
        return recommendationRepository.findByShelterId(shelterId);
    }

    /**
     * Поиск рекомендаций по типу
     *
     * @param type Тип рекомендаций
     * @return Список рекомендаций указанного типа
     */
    public List<Recommendation> findByType(RecommendationType type) {
        return recommendationRepository.findByType(type);
    }

    /**
     * Поиск рекомендаций по применимости
     *
     * @param applicableFor Категория животных
     * @return Список рекомендаций для указанной категории животных
     */
    public List<Recommendation> findByApplicableFor(ApplicableFor applicableFor) {
        return recommendationRepository.findByApplicableFor(applicableFor);
    }

    /**
     * Обновление существующей рекомендации
     *
     * @param id             ID рекомендации для обновления
     * @param recommendation Обновлённые данные рекомендации
     * @return Обновлённая рекомендация
     */
    public Recommendation updateRecommendation(Long id, Recommendation recommendation) {
        Recommendation existingRecommendation = recommendationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Рекомендация не найдена"));
        existingRecommendation.setTitle(recommendation.getTitle());
        existingRecommendation.setDescription(recommendation.getDescription());
        existingRecommendation.setShelterId(recommendation.getShelterId());
        existingRecommendation.setType(recommendation.getType());
        existingRecommendation.setApplicableFor(recommendation.getApplicableFor());
        return recommendationRepository.save(existingRecommendation);
    }

    /**
     * Удаление рекомендации по ID
     *
     * @param id ID рекомендации для удаления
     */
    public void deleteRecommendation(Long id) {
        recommendationRepository.deleteById(id);
    }
}
