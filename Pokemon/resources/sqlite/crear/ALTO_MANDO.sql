CREATE TABLE IF NOT EXISTS ALTO_MANDO (
    id_entrenador INT CHECK (id_entrenador > 0),
    tipo_principal VARCHAR (10), 
    PRIMARY KEY (id_entrenador),
    FOREIGN KEY (id_entrenador) REFERENCES ENTRENADOR (id_entrenador),
    FOREIGN KEY (tipo_principal) REFERENCES TIPO (nombre)
);