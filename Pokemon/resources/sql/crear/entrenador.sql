CREATE TABLE ENTRENADOR (
    id_entrenador INT CHECK (id_entrenador > 0),
    nombre VARCHAR (25),
    PRIMARY KEY (id_entrenador)
);