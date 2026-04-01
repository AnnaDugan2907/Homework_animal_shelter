CREATE TABLE Instructions
(
    id             SERIAL PRIMARY KEY,
    shelter_id     INT,
    title          VARCHAR(255),
    content        TEXT,
    category       VARCHAR(20) CHECK (category IN
                                      ('транспортировка', 'обустройство дома', 'первичный уход', 'кинолог')),
    applicable_for VARCHAR(10) CHECK (applicable_for IN ('кошки', 'собаки', 'общие')),
    created_at     TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (shelter_id) REFERENCES Shelters (id)
);