CREATE TABLE CARACTERISTICAS (
    id_caracteristica INT CHECK (id_caracteristica > 0),
    peso FLOAT CHECK (peso > 0),
    altura FLOAT CHECK (altura > 0),
    especie VARCHAR (20),
    habilidad VARCHAR (20),
    categoria VARCHAR (20) CHECK (categoria IN ("Normal", "Starter", "Semi-legendario", "Legendario")),
    PRIMARY KEY (id_caracteristica)
);