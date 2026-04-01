CREATE TABLE Recommendations
(
    id             SERIAL PRIMARY KEY,
    shelter_id     INT,
    title          VARCHAR(255),
    description    TEXT,
    type           VARCHAR(20) CHECK (type IN ('правила', 'рекомендации', 'инструкции', 'документы')),
    applicable_for VARCHAR(20) CHECK (applicable_for IN ('кошки', 'собаки', 'общие')),
    created_at     TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (shelter_id) REFERENCES Shelters (id)
);