package com.Homework.AnimalShelter.controller;

import com.Homework.AnimalShelter.info.ApplicableFor;
import com.Homework.AnimalShelter.info.RecommendationType;
import com.Homework.AnimalShelter.model.Recommendation;
import com.Homework.AnimalShelter.service.RecommendationService;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/recommendations")
public class RecommendationController {

    private final RecommendationService recommendationService;

    public RecommendationController(RecommendationService recommendationService) {
        this.recommendationService = recommendationService;
    }

    /**
     * Получение всех рекомендаций
     *
     * @return Список всех рекомендаций в системе
     */
    @GetMapping
    public ResponseEntity<List<Recommendation>> getAllRecommendations() {
        return ResponseEntity.ok(recommendationService.getAllRecommendations());
    }

    /**
     * Создание новой рекомендации
     *
     * @param recommendation Объект рекомендации для сохранения
     * @return Сохранённая рекомендация с присвоенным ID
     */
    @PostMapping
    public ResponseEntity<Recommendation> createRecommendation(@RequestBody Recommendation recommendation) {
        Recommendation createdRecommendation = recommendationService.createRecommendation(recommendation);
        return ResponseEntity.status(201).body(createdRecommendation);
    }

    /**
     * Поиск рекомендации по ID
     *
     * @param id ID рекомендации
     * @return Рекомендация, если найдена, или статус 404
     */
    @GetMapping("/{id}")
    public ResponseEntity<Recommendation> getRecommendationById(@PathVariable Long id) {
        Optional<Recommendation> recommendation = recommendationService.getRecommendationById(id);
        return recommendation.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * Поиск рекомендаций по ID приюта
     *
     * @param shelterId ID приюта
     * @return Список рекомендаций для указанного приюта
     */
    @GetMapping("/shelter/{shelterId}")
    public ResponseEntity<List<Recommendation>> getRecommendationsByShelter(@PathVariable Long shelterId) {
        List<Recommendation> recommendations = recommendationService.findByShelterId(shelterId);
        return ResponseEntity.ok(recommendations);
    }

    /**
     * Поиск рекомендаций по типу
     *
     * @param type Тип рекомендаций (в виде строки)
     * @return Список рекомендаций указанного типа
     */
    @GetMapping("/type/{type}")
    public ResponseEntity<List<Recommendation>> getRecommendationsByType(@PathVariable String type) {
        try {
            RecommendationType recommendationType = RecommendationType.valueOf(type.toUpperCase());
            List<Recommendation> recommendations = recommendationService.findByType(recommendationType);
            return ResponseEntity.ok(recommendations);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    /**
     * Поиск рекомендаций по категории животных
     *
     * @param applicableFor Категория животных (в виде строки: «КОШКИ», «СОБАКИ», «ОБЩИЕ»)
     * @return Список рекомендаций для указанной категории животных
     */
    @GetMapping("/applicable-for/{applicableFor}")
    public ResponseEntity<List<Recommendation>> getRecommendationsByApplicableFor(@PathVariable String applicableFor) {
        try {
            ApplicableFor applicableForEnum = ApplicableFor.valueOf(applicableFor.toUpperCase());
            List<Recommendation> recommendations = recommendationService.findByApplicableFor(applicableForEnum);
            return ResponseEntity.ok(recommendations);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    /**
     * Обновление существующей рекомендации
     *
     * @param id             ID рекомендации для обновления
     * @param recommendation Обновлённые данные рекомендации
     * @return Обновлённая рекомендация
     */
    @PutMapping("/{id}")
    public ResponseEntity<Recommendation> updateRecommendation(@PathVariable Long id, @RequestBody Recommendation recommendation) {
        Recommendation updatedRecommendation = recommendationService.updateRecommendation(id, recommendation);
        return ResponseEntity.ok(updatedRecommendation);
    }

    /**
     * Удаление рекомендации по ID
     *
     * @param id ID рекомендации для удаления
     * @return Статус 204 No Content при успешном удалении
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRecommendation(@PathVariable Long id) {
        recommendationService.deleteRecommendation(id);
        return ResponseEntity.noContent().build();
    }
}
