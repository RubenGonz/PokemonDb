CREATE TABLE ENTRENADOR_CASUAL (
    id_entrenador INT CHECK (id_entrenador > 0),
    cantidad_medalla INT CHECK (cantidad_medalla < 9),
    PRIMARY KEY (id_entrenador),
    FOREIGN KEY (id_entrenador) REFERENCES ENTRENADOR (id_entrenador)
);