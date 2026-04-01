CREATE TABLE PetAdopters
(
    id                         SERIAL PRIMARY KEY,
    user_id                    INT,
    shelter_id                 INT,
    name                       VARCHAR(255),
    contact_details            VARCHAR(255),
    pet_type                   VARCHAR(10) CHECK (pet_type IN ('кошки', 'собаки')),
    documents_list             TEXT,
    transportation_tips        TEXT,
    home_setup_recommendations TEXT,
    reasons_for_rejection      TEXT,
    adoption_status            VARCHAR(20) DEFAULT 'подготовка' CHECK (adoption_status IN ('подготовка', 'одобрено', 'отказано', 'продление')),
    trial_period_days          INT         DEFAULT 30,
    additional_days            INT         DEFAULT 0,
    trial_start_date           DATE,
    trial_end_date             DATE,
    created_at                 TIMESTAMP   DEFAULT CURRENT_TIMESTAMP,
    updated_at                 TIMESTAMP   DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES Users (id),
    FOREIGN KEY (shelter_id) REFERENCES Shelters (id)
);

-- Создание функции для автоматического обновления updated_at
CREATE OR REPLACE FUNCTION update_updated_at_column()
    RETURNS TRIGGER AS
$$
BEGIN
    NEW.updated_at = NOW();
    RETURN NEW;
END;
$$ LANGUAGE 'plpgsql';


-- Создание триггера для обновления updated_at при каждом обновлении записи
CREATE TRIGGER update_petadopters_updated_at
    BEFORE UPDATE
    ON PetAdopters
    FOR EACH ROW
EXECUTE PROCEDURE update_updated_at_column();