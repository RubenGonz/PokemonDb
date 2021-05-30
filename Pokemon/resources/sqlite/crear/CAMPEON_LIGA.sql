CREATE TABLE IF NOT EXISTS CAMPEON_LIGA (
    id_entrenador INT CHECK (id_entrenador > 0),
    region VARCHAR (10) UNIQUE,
    PRIMARY KEY (id_entrenador),
    FOREIGN KEY (id_entrenador) REFERENCES ENTRENADOR (id_entrenador)
);