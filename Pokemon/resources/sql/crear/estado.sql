CREATE TABLE ESTADO (
    id_estado INT CHECK (id_estado > 0),
    nombre VARCHAR (15),
    persistencia BOOLEAN,
    efecto VARCHAR (75),
    PRIMARY KEY (id_estado)
);