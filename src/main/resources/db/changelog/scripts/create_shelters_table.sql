CREATE TABLE Shelters
(
    id                SERIAL PRIMARY KEY,
    name              VARCHAR(255) NOT NULL,
    type              VARCHAR(10)  NOT NULL CHECK (type IN ('кошки', 'собаки')),
    address           VARCHAR(255),
    schedule          TEXT,
    contact_security  VARCHAR(50),
    access_rules      TEXT,
    safety_guidelines TEXT,
    location_map      TEXT
);