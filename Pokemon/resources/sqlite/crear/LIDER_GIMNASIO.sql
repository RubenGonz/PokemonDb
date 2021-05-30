CREATE TABLE IF NOT EXISTS LIDER_GIMNASIO (
    id_entrenador INT CHECK (id_entrenador > 0),
    medalla INT CHECK (medalla < 9),
    PRIMARY KEY (id_entrenador),
    FOREIGN KEY (id_entrenador) REFERENCES ENTRENADOR (id_entrenador)
);