CREATE TABLE RejectionReasons
(
    id         SERIAL PRIMARY KEY,
    shelter_id INT,
    reason     TEXT,
    category   VARCHAR(20) CHECK (category IN
                                  ('личные обстоятельства', 'состояние здоровья', 'жилищные условия', 'другие')),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (shelter_id) REFERENCES Shelters (id)
);