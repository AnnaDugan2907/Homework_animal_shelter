CREATE TABLE Reminders
(
    id            SERIAL PRIMARY KEY,
    adopter_id    INT,
    reminder_date DATE,
    reminder_type VARCHAR(20) DEFAULT 'ежедневный' CHECK (reminder_type IN
                                                          ('ежедневный', 'через 2 дня', 'после 30 дней', 'продление')),
    sent          BOOLEAN     DEFAULT FALSE,
    processed     BOOLEAN     DEFAULT FALSE,
    FOREIGN KEY (adopter_id) REFERENCES PetAdopters (id)
);