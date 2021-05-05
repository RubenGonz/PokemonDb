/**
 * Esta de aqui es la base de datos donde creamos la base de datos,
 * las tablas y sus restricciones.
*/

-- Creacion de la base de datos

DROP DATABASE IF EXISTS PokemonDb;
CREATE DATABASE IF NOT EXISTS PokemonDb;
USE PokemonDb;

-- Creacion de las tablas, primary keys y foreign keys

/**
 * Tabla donde se ven las caracteristicas de un pokemon
*/
CREATE TABLE CARACTERISTICAS (
    id_caracteristica INT CHECK (id_caracteristica > 0),
    peso FLOAT CHECK (peso > 0),
    altura FLOAT CHECK (altura > 0),
    especie VARCHAR (20),
    habilidad VARCHAR (20),
    categoria VARCHAR (20) CHECK (categoria IN ("Normal", "Starter", "Semi-legendario", "Legendario")),
    PRIMARY KEY (id_caracteristica)
);

/**
 * Tabla donde se ven las estadisticas base de un pokemon
*/
CREATE TABLE ESTADISTICAS_BASE (
    id_estadisticas_base INT CHECK (id_estadisticas_base > 0),
    ps_base INT CHECK (ps_base > 0),
    ataque_base INT CHECK (ataque_base > 0),
    defensa_base INT CHECK (defensa_base > 0),
    ataque_especial_base INT CHECK (ataque_especial_base > 0),
    defensa_especial_base INT CHECK (defensa_especial_base > 0),
    velocidad_base INT CHECK (velocidad_base > 0),
    PRIMARY KEY (id_estadisticas_base)
);

/**
 * Tabla principal donde estan los pokemon
*/
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

/**
 * Tabla donde se ve las evoluciones de un pokemon
*/
CREATE TABLE EVOLUCIONA (
    numero_pokedex_origen INT CHECK (numero_pokedex_origen > 0),
    numero_pokedex_destino INT CHECK (numero_pokedex_destino > 0),
    modo_evoluciona VARCHAR (20) CHECK (modo_evoluciona IN ("Nivel", "Intercambio", "Objeto")),
    PRIMARY KEY (numero_pokedex_origen, numero_pokedex_destino),
    FOREIGN KEY (numero_pokedex_origen) REFERENCES POKEMON (numero_pokedex),
    FOREIGN KEY (numero_pokedex_destino) REFERENCES POKEMON (numero_pokedex)
);

/**
 * Tabla donde se ven las movimientos que pude hacer un pokemon
*/
CREATE TABLE MOVIMIENTO (
    id_movimiento INT CHECK (id_movimiento > 0),
    nombre VARCHAR (25),
    tipo VARCHAR (10) CHECK (tipo IN ("Agua", "Bicho", "Dragon", "Electrico", "Fantasma", "Fuego", "Hielo", "Lucha", "Normal", "Planta", "Psiquico", "Roca", "Tierra", "Veneno", "Pajaro")),
    categoria VARCHAR (10) CHECK (categoria IN ("Fisico", "Especial", "Estado")),
    pp INT CHECK (pp > 0),
    potencia INT CHECK (potencia > 0),
    certeza INT CHECK (certeza > 0),
    PRIMARY KEY (id_movimiento)
);

/**
 * Tabla donde se ve como un pokemon puede aprender un movimiento
*/
CREATE TABLE APRENDE_MOVIMIENTO (
    numero_pokedex INT CHECK (numero_pokedex > 0),
    id_movimiento INT CHECK (id_movimiento > 0),
    modo_aprendizaje VARCHAR (20) CHECK (modo_aprendizaje IN ("Nivel", "Evolucion", "Tutor")),
    PRIMARY KEY (numero_pokedex, id_movimiento),
    FOREIGN KEY (numero_pokedex) REFERENCES POKEMON (numero_pokedex),
    FOREIGN KEY (id_movimiento) REFERENCES MOVIMIENTO (id_movimiento)
);

/**
 * Tabla donde se ven los posibles estados que puede provocar un movimiento
*/
CREATE TABLE ESTADO (
    id_estado INT CHECK (id_estado > 0),
    nombre VARCHAR (15),
    persistencia BOOLEAN,
    efecto VARCHAR (75),
    descripcion VARCHAR (75),
    id_movimiento INT CHECK (id_movimiento > 0),
    PRIMARY KEY (id_estado),
    FOREIGN KEY (id_movimiento) REFERENCES MOVIMIENTO (id_movimiento)
);

/**
 * Tabla donde se ven los objetos del juego
*/
CREATE TABLE OBJETO (
    id_objeto INT CHECK (id_objeto > 0),
    nombre VARCHAR (25),
    modo_obtencion VARCHAR (20) CHECK (modo_obtencion IN ("Compra", "Suelo", "Persona", "Pokemon")),
    precio_venta INT CHECK (precio_venta > 0),
    PRIMARY KEY (id_objeto)
);

/**
 * Tabla donde se ven los objetos de tipo pokeball
*/
CREATE TABLE POKEBALL (
    id_objeto INT CHECK (id_objeto > 0),
    ratio FLOAT CHECK (ratio > 0),
    PRIMARY KEY (id_objeto),
    FOREIGN KEY (id_objeto) REFERENCES OBJETO (id_objeto)
);

/**
 * Tabla donde se ven los objetos comunes
*/
CREATE TABLE OBJETO_COMUN (
    id_objeto INT CHECK (id_objeto > 0),
    efecto VARCHAR (75),
    PRIMARY KEY (id_objeto),
    FOREIGN KEY (id_objeto) REFERENCES OBJETO (id_objeto)
);

/**
 * Tabla donde se ven los objetos de tipo maquina
*/
CREATE TABLE MAQUINA (
    id_objeto INT CHECK (id_objeto > 0),
    id_movimiento INT CHECK (id_movimiento > 0),
    PRIMARY KEY (id_objeto),
    FOREIGN KEY (id_objeto) REFERENCES OBJETO (id_objeto),
    FOREIGN KEY (id_movimiento) REFERENCES MOVIMIENTO (id_movimiento)
);

/**
 * Tabla donde se ven los efectos de un objeto en un pokemon
*/
CREATE TABLE AFECTA_POKEMON (
    numero_pokedex INT CHECK (numero_pokedex > 0),
    id_objeto INT CHECK (id_objeto > 0),
    modo_afecta VARCHAR (75) CHECK (modo_afecta IN ("Cura", "Captura", "Evoluciona", "Potencia")),
    PRIMARY KEY (numero_pokedex, id_objeto),
    FOREIGN KEY (numero_pokedex) REFERENCES POKEMON (numero_pokedex),
    FOREIGN KEY (id_objeto) REFERENCES OBJETO (id_objeto)
);

/**
 * Tabla donde se ven los entrenadores del juego
*/
CREATE TABLE ENTRENADOR (
    id_entrenador INT CHECK (id_entrenador > 0),
    nombre VARCHAR (25),
    PRIMARY KEY (id_entrenador)
);

/**
 * Tabla donde se ven los entrenadores de tipo villano
*/
CREATE TABLE VILLANO (
    id_entrenador INT CHECK (id_entrenador > 0),
    proposito VARCHAR (25),
    PRIMARY KEY (id_entrenador),
    FOREIGN KEY (id_entrenador) REFERENCES ENTRENADOR (id_entrenador)
);

/**
 * Tabla donde se ven los entrenadores de tipo lider de gimnasio
*/
CREATE TABLE LIDER_GIMNASIO (
    id_entrenador INT CHECK (id_entrenador > 0),
    medalla INT CHECK (medalla < 9),
    PRIMARY KEY (id_entrenador),
    FOREIGN KEY (id_entrenador) REFERENCES ENTRENADOR (id_entrenador)
);

/**
 * Tabla donde se ven los entrenadores casuales
*/
CREATE TABLE ENTRENADOR_CASUAL (
    id_entrenador INT CHECK (id_entrenador > 0),
    cantidad_medalla INT CHECK (cantidad_medalla < 9),
    PRIMARY KEY (id_entrenador),
    FOREIGN KEY (id_entrenador) REFERENCES ENTRENADOR (id_entrenador)
);

/**
 * Tabla donde se ven los entrenadores de tipo campeon de liga
*/
CREATE TABLE CAMPEON_LIGA (
    id_entrenador INT CHECK (id_entrenador > 0),
    region VARCHAR (10) UNIQUE,
    PRIMARY KEY (id_entrenador),
    FOREIGN KEY (id_entrenador) REFERENCES ENTRENADOR (id_entrenador)
);

/**
 * Tabla donde se ven los entrenadores de tipo alto mando
*/
CREATE TABLE ALTO_MANDO (
    id_entrenador INT CHECK (id_entrenador > 0),
    tipo_principal VARCHAR (10) CHECK (tipo_principal IN ("Agua", "Bicho", "Dragon", "Electrico", "Fantasma", "Fuego", "Hielo", "Lucha", "Normal", "Planta", "Psiquico", "Roca", "Tierra", "Veneno", "Pajaro")),
    PRIMARY KEY (id_entrenador),
    FOREIGN KEY (id_entrenador) REFERENCES ENTRENADOR (id_entrenador)
);

/**
 * Tabla donde se ven los efectos de un objeto en un entrenador
*/
CREATE TABLE AFECTA_ENTRENADOR (
    id_entrenador INT CHECK (id_entrenador > 0),
    id_objeto INT CHECK (id_objeto > 0),
    modo_afecta VARCHAR (75) CHECK (modo_afecta IN ("Informa", "Ayuda")),
    PRIMARY KEY (id_entrenador, id_objeto),
    FOREIGN KEY (id_entrenador) REFERENCES ENTRENADOR (id_entrenador),
    FOREIGN KEY (id_objeto) REFERENCES OBJETO (id_objeto)
);

/**
 * Tabla donde se ven los pokemons que tiene un entrenador
*/
CREATE TABLE TIENE (
    id_entrenador INT CHECK (id_entrenador > 0),
    numero_pokedex INT CHECK (numero_pokedex > 0),
    cantidad INT CHECK (cantidad > 0),
    PRIMARY KEY (id_entrenador, numero_pokedex),
    FOREIGN KEY (id_entrenador) REFERENCES ENTRENADOR (id_entrenador),
    FOREIGN KEY (numero_pokedex) REFERENCES POKEMON (numero_pokedex)
);
