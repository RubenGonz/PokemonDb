/**
 * Esta de aqui es la base de datos donde creamos la base de datos,
 * las tablas y sus restricciones.
*/

-- Creacion de la base de datos

DROP DATABASE IF EXISTS PokemonDb;
CREATE DATABASE IF NOT EXISTS PokemonDb;
USE PokemonDb;

-- Creacion de las tablas

CREATE TABLE CARACTERISTICAS (
    id_caracteristica INT,
    peso FLOAT,
    altura FLOAT,
    especie VARCHAR (20),
    habilidad VARCHAR (20),
    categoria VARCHAR (20)
);

CREATE TABLE ESTADISTICAS_BASE (
    id_estadisticas_base INT,
    ps_base INT,
    ataque_base INT,
    defensa_base INT,
    ataque_especial_base INT,
    defensa_especial_base INT,
    velocidad_base INT
);
CREATE TABLE POKEMON (
    numero_pokedex INT,
    nombre VARCHAR (15),
    tipo_principal VARCHAR (10),
    tipo_secundario VARCHAR (10)
    id_caracteristica INT,
    id_estadisticas_base INT
);

CREATE TABLE EVOLUCIONA (
    numero_pokedex INT,
    numero_pokedex INT,
    modo_evoluciona VARCHAR (20)
);

CREATE TABLE MOVIMIENTO (
    id_movimiento INT,
    nombre VARCHAR (25),
    tipo VARCHAR (10),
    categoria VARCHAR (10),
    pp INT,
    potencia INT,
    precision INT
);

CREATE TABLE APRENDE_MOVIMIENTO (
    numero_pokedex INT,
    id_movimiento INT,
    modo_aprendizaje VARCHAR (20)
);

CREATE TABLE ESTADO (
    id_estado INT,
    nombre VARCHAR (15),
    persistencia BOOLEAN,
    efecto VARCHAR (75),
    descripcion VARCHAR (75),
    id_movimiento INT
);
