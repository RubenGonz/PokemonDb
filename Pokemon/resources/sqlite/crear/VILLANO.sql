CREATE TABLE IF NOT EXISTS VILLANO (
    id_entrenador INT CHECK (id_entrenador > 0),
    proposito VARCHAR (75),
    PRIMARY KEY (id_entrenador),
    FOREIGN KEY (id_entrenador) REFERENCES ENTRENADOR (id_entrenador)
);