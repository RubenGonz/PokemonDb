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
    tipo_secundario VARCHAR (10),
    id_caracteristica INT,
    id_estadisticas_base INT
);

CREATE TABLE EVOLUCIONA (
    numero_pokedex_origen INT,
    numero_pokedex_destino INT,
    modo_evoluciona VARCHAR (20)
);

CREATE TABLE MOVIMIENTO (
    id_movimiento INT,
    nombre VARCHAR (25),
    tipo VARCHAR (10),
    categoria VARCHAR (10),
    pp INT,
    potencia INT,
    certeza INT
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

CREATE TABLE OBJETO (
    id_objeto INT,
    nombre VARCHAR (25),
    modo_obtencion VARCHAR (20),
    precio_venta INT
);

CREATE TABLE POKEBALL (
    id_objeto INT,
    ratio FLOAT
);

CREATE TABLE OBJETO_COMUN (
    id_objeto INT,
    efecto VARCHAR (75)
);

CREATE TABLE MAQUINA (
    id_objeto INT,
    id_movimiento INT
);

CREATE TABLE AFECTA_POKEMON (
    numero_pokedex INT,
    id_objeto INT,
    modo_afecta VARCHAR (75)
);

CREATE TABLE ENTRENADOR (
    id_entrenador INT,
    nombre VARCHAR (25),
    pokemon1 INT,
    pokemon2 INT,
    pokemon3 INT
);

CREATE TABLE VILLANO (
    id_entrenador INT,
    proposito VARCHAR (25)
);

CREATE TABLE LIDER_GIMNASIO (
    id_entrenador INT,
    medalla INT
);

CREATE TABLE ENTRENADOR_CASUAL (
    id_entrenador INT,
    cantidad_medalla INT
);

CREATE TABLE CAMPEON_LIGA (
    id_entrenador INT,
    region VARCHAR (10)
);

CREATE TABLE ALTO_MANDO (
    id_entrenador INT,
    tipo_principal VARCHAR (10)
);

CREATE TABLE AFECTA_ENTRENADOR (
    id_entrenador INT,
    id_objeto INT,
    modo_afecta VARCHAR (75)
);

CREATE TABLE TIENE (
    id_entrenador INT,
    numero_pokedex INT
);

-- Declaracion de las primary keys

ALTER TABLE CARACTERISTICAS ADD CONSTRAINT pk_caracteristicas PRIMARY KEY (id_caracteristica);
ALTER TABLE ESTADISTICAS_BASE ADD CONSTRAINT pk_estadisticas_base PRIMARY KEY (id_estadisticas_base);
ALTER TABLE POKEMON ADD CONSTRAINT pk_pokemon PRIMARY KEY (numero_pokedex);
ALTER TABLE EVOLUCIONA ADD CONSTRAINT pk_evoluciona PRIMARY KEY (numero_pokedex_origen, numero_pokedex_destino);
ALTER TABLE MOVIMIENTO ADD CONSTRAINT pk_movimiento PRIMARY KEY (id_movimiento);
ALTER TABLE APRENDE_MOVIMIENTO ADD CONSTRAINT pk_aprende_movimiento PRIMARY KEY (numero_pokedex, id_movimiento);
ALTER TABLE ESTADO ADD CONSTRAINT pk_estado PRIMARY KEY (id_estado);
ALTER TABLE OBJETO ADD CONSTRAINT pk_objeto PRIMARY KEY (id_objeto);
ALTER TABLE POKEBALL ADD CONSTRAINT pk_pokeball PRIMARY KEY (id_objeto);
ALTER TABLE OBJETO_COMUN ADD CONSTRAINT pk_objeto_comun PRIMARY KEY (id_objeto);
ALTER TABLE MAQUINA ADD CONSTRAINT pk_maquina PRIMARY KEY (id_objeto);
ALTER TABLE AFECTA_POKEMON ADD CONSTRAINT pk_afecta_pokemon PRIMARY KEY (numero_pokedex, id_objeto);
ALTER TABLE ENTRENADOR ADD CONSTRAINT pk_entrenador PRIMARY KEY (id_entrenador);
ALTER TABLE VILLANO ADD CONSTRAINT pk_villano PRIMARY KEY (id_entrenador);
ALTER TABLE LIDER_GIMNASIO ADD CONSTRAINT pk_lider_gimnasio PRIMARY KEY (id_entrenador);
ALTER TABLE ENTRENADOR_CASUAL ADD CONSTRAINT pk_entrenador_casual PRIMARY KEY (id_entrenador);
ALTER TABLE CAMPEON_LIGA ADD CONSTRAINT pk_campeon_liga PRIMARY KEY (id_entrenador);
ALTER TABLE ALTO_MANDO ADD CONSTRAINT pk_alto_mando PRIMARY KEY (id_entrenador);
ALTER TABLE AFECTA_ENTRENADOR ADD CONSTRAINT pk_afecta_entrenador PRIMARY KEY (id_entrenador, id_objeto);
ALTER TABLE TIENE ADD CONSTRAINT pk_tiene PRIMARY KEY (id_entrenador, numero_pokedex);
