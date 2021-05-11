CREATE TABLE POKEMON (
    numero_pokedex INT CHECK (numero_pokedex > 0),
    nombre VARCHAR (15),
    tipo_principal VARCHAR (10) CHECK (tipo_principal IN ("Agua", "Bicho", "Dragon", "Electrico", "Fantasma", "Fuego", "Hielo", "Lucha", "Normal", "Planta", "Psiquico", "Roca", "Tierra", "Veneno", "Pajaro")),
    tipo_secundario VARCHAR (10) CHECK (tipo_secundario IN ("Agua", "Bicho", "Dragon", "Electrico", "Fantasma", "Fuego", "Hielo", "Lucha", "Normal", "Planta", "Psiquico", "Roca", "Tierra", "Veneno", "Pajaro", NULL)),
    id_caracteristica INT CHECK (id_caracteristica > 0),
    id_estadisticas_base INT CHECK (id_estadisticas_base > 0),
    PRIMARY KEY (numero_pokedex),
    FOREIGN KEY (id_caracteristica) REFERENCES CARACTERISTICAS (id_caracteristica),
    FOREIGN KEY (id_estadisticas_base) REFERENCES ESTADISTICAS_BASE (id_estadisticas_base)
);