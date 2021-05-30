CREATE TABLE IF NOT EXISTS PERTENECE (
    numero_pokedex INT,
    tipo VARCHAR (15),
    PRIMARY KEY (numero_pokedex,tipo),
    FOREIGN KEY (tipo) REFERENCES TIPO (nombre),
    FOREIGN KEY (numero_pokedex) REFERENCES POKEMON (numero_pokedex)
);