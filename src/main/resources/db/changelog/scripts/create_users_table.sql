CREATE TABLE Users
(
    id               SERIAL PRIMARY KEY,
    username         VARCHAR(100) UNIQUE,
    contact_info     VARCHAR(255),
    shelter_id       INT,
    is_new           BOOLEAN   DEFAULT TRUE,
    last_interaction TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (shelter_id) REFERENCES Shelters (id)
);


