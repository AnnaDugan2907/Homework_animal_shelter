CREATE TABLE InteractionLogs
(
    id               SERIAL PRIMARY KEY,
    user_id          INT,
    interaction_type VARCHAR(50),
    message          TEXT,
    timestamp        TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES Users (id)
);