package com.Homework.AnimalShelter.info;

public enum ApplicableFor {
    КОШКИ("кошки"),
    СОБАКИ("собаки"),
    ОБЩИЕ("общие");

    private final String value;

    ApplicableFor(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    // Аналогичный метод для преобразования строки в enum
    public static ApplicableFor fromValue(String value) {
        for (ApplicableFor applicable : values()) {
            if (applicable.value.equalsIgnoreCase(value)) {
                return applicable;
            }
        }
        throw new IllegalArgumentException("Неизвестная категория: " + value);
    }
}
