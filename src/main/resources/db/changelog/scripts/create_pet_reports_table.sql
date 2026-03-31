CREATE TABLE PetReports
(
    id               SERIAL PRIMARY KEY,
    adopter_id       INT,
    report_date      DATE,
    photo_url        VARCHAR(255),
    diet             TEXT,
    well_being       TEXT,
    behavior_changes TEXT,
    report_status    VARCHAR(20) DEFAULT 'отправлено' CHECK (report_status IN ('нормальный', 'плохой', 'отправлено')),
    is_complaint     BOOLEAN     DEFAULT FALSE,
    comments         TEXT,
    FOREIGN KEY (adopter_id) REFERENCES PetAdopters (id)
);