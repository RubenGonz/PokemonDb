CREATE TABLE ALTO_MANDO (
    id_entrenador INT CHECK (id_entrenador > 0),
    tipo_principal VARCHAR (10) CHECK (tipo_principal IN ("Agua", "Bicho", "Dragon", "Electrico", "Fantasma", "Fuego", "Hielo", "Lucha", "Normal", "Planta", "Psiquico", "Roca", "Tierra", "Veneno", "Pajaro")),
    PRIMARY KEY (id_entrenador),
    FOREIGN KEY (id_entrenador) REFERENCES ENTRENADOR (id_entrenador)
);