package com.Homework.AnimalShelter.info;

public enum RecommendationType {
    ПРАВИЛА("правила"),
    РЕКОМЕНДАЦИИ("рекомендации"),
    ИНСТРУКЦИИ("инструкции"),
    ДОКУМЕНТЫ("документы");

    private final String value;

    RecommendationType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    // Метод для преобразования строки в enum (с учётом возможных вариантов написания)
    public static RecommendationType fromValue(String value) {
        for (RecommendationType type : values()) {
            if (type.value.equalsIgnoreCase(value)) {
                return type;
            }
        }
        throw new IllegalArgumentException("Неизвестный тип рекомендации: " + value);
    }
}
