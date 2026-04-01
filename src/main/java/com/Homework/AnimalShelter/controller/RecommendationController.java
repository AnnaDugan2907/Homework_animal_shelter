package com.Homework.AnimalShelter.controller;

import com.Homework.AnimalShelter.info.ApplicableFor;
import com.Homework.AnimalShelter.info.RecommendationType;
import com.Homework.AnimalShelter.model.Recommendation;
import com.Homework.AnimalShelter.service.RecommendationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

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
    public List<Recommendation> getAllRecommendations() {
        return recommendationService.getAllRecommendations();
    }

    /**
     * Создание новой рекомендации
     *
     * @param recommendation Объект рекомендации для сохранения
     * @return Сохранённая рекомендация с присвоенным ID
     */
    @PostMapping
    @Operation(description = "Создать новую рекомендацию")
    public Recommendation createRecommendation(@RequestBody Recommendation recommendation) {
        return recommendationService.createRecommendation(recommendation);
    }

    /**
     * Поиск рекомендации по ID
     *
     * @param id ID рекомендации
     * @return Рекомендация, если найдена, или статус 404
     */
    @GetMapping("/{id}")
    @Operation(description = "Получить рекомендацию по ID")
    public Recommendation getRecommendationById(@PathVariable Long id) {
        return recommendationService.getRecommendationById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Recommendation not found"));
    }

    /**
     * Поиск рекомендаций по ID приюта
     *
     * @param shelterId ID приюта
     * @return Список рекомендаций для указанного приюта
     */
    @GetMapping("/shelter/{shelterId}")
    public List<Recommendation> getRecommendationsByShelter(@PathVariable Long shelterId) {
        return recommendationService.findByShelterId(shelterId);
    }

    /**
     * Поиск рекомендаций по типу
     *
     * @param type Тип рекомендаций (в виде строки)
     * @return Список рекомендаций указанного типа
     */
    @GetMapping("/type/{type}")
    public List<Recommendation> getRecommendationsByType(@PathVariable String type) {
        RecommendationType recommendationType = RecommendationType.valueOf(type.toUpperCase());
        return recommendationService.findByType(recommendationType);
    }


    /**
     * Поиск рекомендаций по категории животных
     *
     * @param applicableFor Категория животных (в виде строки: «КОШКИ», «СОБАКИ», «ОБЩИЕ»)
     * @return Список рекомендаций для указанной категории животных
     */
    @GetMapping("/applicable-for/{applicableFor}")
    public List<Recommendation> getRecommendationsByApplicableFor(@PathVariable String applicableFor) {
        ApplicableFor applicableForEnum = ApplicableFor.valueOf(applicableFor.toUpperCase());
        return recommendationService.findByApplicableFor(applicableForEnum);
    }


    /**
     * Обновление существующей рекомендации
     *
     * @param id             ID рекомендации для обновления
     * @param recommendation Обновлённые данные рекомендации
     * @return Обновлённая рекомендация
     */
    @PutMapping("/{id}")
    public Recommendation updateRecommendation(@PathVariable Long id, @RequestBody Recommendation recommendation) {
        return recommendationService.updateRecommendation(id, recommendation);
    }


    /**
     * Удаление рекомендации по ID
     *
     * @param id ID рекомендации для удаления
     * @return Статус 204 No Content при успешном удалении
     */
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteRecommendation(@PathVariable Long id) {
        recommendationService.deleteRecommendation(id);
    }
}
