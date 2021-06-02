CREATE TABLE IF NOT EXISTS POKEMON (
    numero_pokedex INT CHECK (numero_pokedex > 0),
    nombre VARCHAR (15),
    id_caracteristica INT CHECK (id_caracteristica > 0),
    id_estadisticas_base INT CHECK (id_estadisticas_base > 0),
    PRIMARY KEY (numero_pokedex),
    FOREIGN KEY (id_caracteristica) REFERENCES CARACTERISTICAS (id_caracteristica),
    FOREIGN KEY (id_estadisticas_base) REFERENCES ESTADISTICAS_BASE (id_estadisticas_base)
);