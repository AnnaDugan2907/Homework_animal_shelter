CREATE TABLE AdoptionStages
(
    id         SERIAL PRIMARY KEY,
    adopter_id INT,
    stage      VARCHAR(20) CHECK (stage IN ('подготовка', 'одобрено', 'отказ', 'продление', 'завершено')),
    start_date DATE,
    end_date   DATE,
    notes      TEXT,
    FOREIGN KEY (adopter_id) REFERENCES PetAdopters (id)
);