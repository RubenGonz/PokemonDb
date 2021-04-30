/**
 * Esta de aqui es la base de datos donde creamos la base de datos,
 * las tablas y sus restricciones.
*/

-- Creacion de la base de datos

DROP DATABASE IF EXISTS PokemonDb;
CREATE DATABASE IF NOT EXISTS PokemonDb;
USE PokemonDb;

-- Creacion de las tablas, primary keys y foreign keys

CREATE TABLE CARACTERISTICAS (
    id_caracteristica INT,
    peso FLOAT,
    altura FLOAT,
    especie VARCHAR (20),
    habilidad VARCHAR (20),
    categoria VARCHAR (20),
    PRIMARY KEY (id_caracteristica)
);

CREATE TABLE ESTADISTICAS_BASE (
    id_estadisticas_base INT,
    ps_base INT,
    ataque_base INT,
    defensa_base INT,
    ataque_especial_base INT,
    defensa_especial_base INT,
    velocidad_base INT,
    PRIMARY KEY (id_estadisticas_base)
);
CREATE TABLE POKEMON (
    numero_pokedex INT,
    nombre VARCHAR (15),
    tipo_principal VARCHAR (10),
    tipo_secundario VARCHAR (10),
    id_caracteristica INT,
    id_estadisticas_base INT,
    PRIMARY KEY (numero_pokedex),
    FOREIGN KEY (id_caracteristica) REFERENCES CARACTERISTICAS (id_caracteristica),
    FOREIGN KEY (id_estadisticas_base) REFERENCES ESTADISTICAS_BASE (id_estadisticas_base)
);

CREATE TABLE EVOLUCIONA (
    numero_pokedex_origen INT,
    numero_pokedex_destino INT,
    modo_evoluciona VARCHAR (20),
    PRIMARY KEY (numero_pokedex_origen, numero_pokedex_destino),
    FOREIGN KEY (numero_pokedex_origen) REFERENCES POKEMON (numero_pokedex),
    FOREIGN KEY (numero_pokedex_destino) REFERENCES POKEMON (numero_pokedex)
);

CREATE TABLE MOVIMIENTO (
    id_movimiento INT,
    nombre VARCHAR (25),
    tipo VARCHAR (10),
    categoria VARCHAR (10),
    pp INT,
    potencia INT,
    certeza INT,
    PRIMARY KEY (id_movimiento)
);

CREATE TABLE APRENDE_MOVIMIENTO (
    numero_pokedex INT,
    id_movimiento INT,
    modo_aprendizaje VARCHAR (20),
    PRIMARY KEY (numero_pokedex, id_movimiento),
    FOREIGN KEY (numero_pokedex) REFERENCES POKEMON (numero_pokedex),
    FOREIGN KEY (id_movimiento) REFERENCES MOVIMIENTO (id_movimiento)
);

CREATE TABLE ESTADO (
    id_estado INT,
    nombre VARCHAR (15),
    persistencia BOOLEAN,
    efecto VARCHAR (75),
    descripcion VARCHAR (75),
    id_movimiento INT,
    PRIMARY KEY (id_estado),
    FOREIGN KEY (id_movimiento) REFERENCES MOVIMIENTO (id_movimiento)
);

CREATE TABLE OBJETO (
    id_objeto INT,
    nombre VARCHAR (25),
    modo_obtencion VARCHAR (20),
    precio_venta INT,
    PRIMARY KEY (id_objeto)
);

CREATE TABLE POKEBALL (
    id_objeto INT,
    ratio FLOAT,
    PRIMARY KEY (id_objeto),
    FOREIGN KEY (id_objeto) REFERENCES OBJETO (id_objeto)

);

CREATE TABLE OBJETO_COMUN (
    id_objeto INT,
    efecto VARCHAR (75),
    PRIMARY KEY (id_objeto),
    FOREIGN KEY (id_objeto) REFERENCES OBJETO (id_objeto)
);

CREATE TABLE MAQUINA (
    id_objeto INT,
    id_movimiento INT,
    PRIMARY KEY (id_objeto),
    FOREIGN KEY (id_objeto) REFERENCES OBJETO (id_objeto),
    FOREIGN KEY (id_movimiento) REFERENCES MOVIMIENTO (id_movimiento)
);

CREATE TABLE AFECTA_POKEMON (
    numero_pokedex INT,
    id_objeto INT,
    modo_afecta VARCHAR (75),
    PRIMARY KEY (numero_pokedex, id_objeto),
    FOREIGN KEY (numero_pokedex) REFERENCES POKEMON (numero_pokedex),
    FOREIGN KEY (id_objeto) REFERENCES OBJETO (id_objeto)
);

CREATE TABLE ENTRENADOR (
    id_entrenador INT,
    nombre VARCHAR (25),
    pokemon1 INT,
    pokemon2 INT,
    pokemon3 INT,
    PRIMARY KEY (id_entrenador)
);

CREATE TABLE VILLANO (
    id_entrenador INT,
    proposito VARCHAR (25),
    PRIMARY KEY (id_entrenador),
    FOREIGN KEY (id_entrenador) REFERENCES ENTRENADOR (id_entrenador)
);

CREATE TABLE LIDER_GIMNASIO (
    id_entrenador INT,
    medalla INT,
    PRIMARY KEY (id_entrenador),
    FOREIGN KEY (id_entrenador) REFERENCES ENTRENADOR (id_entrenador)
);

CREATE TABLE ENTRENADOR_CASUAL (
    id_entrenador INT,
    cantidad_medalla INT,
    PRIMARY KEY (id_entrenador),
    FOREIGN KEY (id_entrenador) REFERENCES ENTRENADOR (id_entrenador)
);

CREATE TABLE CAMPEON_LIGA (
    id_entrenador INT,
    region VARCHAR (10),
    PRIMARY KEY (id_entrenador),
    FOREIGN KEY (id_entrenador) REFERENCES ENTRENADOR (id_entrenador)
);

CREATE TABLE ALTO_MANDO (
    id_entrenador INT,
    tipo_principal VARCHAR (10),
    PRIMARY KEY (id_entrenador),
    FOREIGN KEY (id_entrenador) REFERENCES ENTRENADOR (id_entrenador)
);

CREATE TABLE AFECTA_ENTRENADOR (
    id_entrenador INT,
    id_objeto INT,
    modo_afecta VARCHAR (75),
    PRIMARY KEY (id_entrenador, id_objeto),
    FOREIGN KEY (id_entrenador) REFERENCES ENTRENADOR (id_entrenador),
    FOREIGN KEY (id_objeto) REFERENCES OBJETO (id_objeto)
);

CREATE TABLE TIENE (
    id_entrenador INT,
    numero_pokedex INT,
    PRIMARY KEY (id_entrenador, numero_pokedex),
    FOREIGN KEY (id_entrenador) REFERENCES ENTRENADOR (id_entrenador),
    FOREIGN KEY (numero_pokedex) REFERENCES POKEMON (numero_pokedex)
);

-- Restricciones

ALTER TABLE CARACTERISTICAS ADD CONSTRAINT ck_caracteristicas_categoria 
CHECK (categoria IN ("Normal", "Starter", "Semi-legendario", "Legendario"));

ALTER TABLE POKEMON ADD CONSTRAINT ck_pokemon_tipo_principal 
CHECK (tipo_principal IN ("Agua", "Bicho", "Dragon", "Electrico", "Fantasma", "Fuego", "Hielo", "Lucha", "Normal", "Planta", "Psiquico", "Roca", "Tierra", "Veneno", "Pajaro"));
ALTER TABLE POKEMON ADD CONSTRAINT ck_pokemon_tipo_secundario 
CHECK (tipo_secundario IN ("Agua", "Bicho", "Dragon", "Electrico", "Fantasma", "Fuego", "Hielo", "Lucha", "Normal", "Planta", "Psiquico", "Roca", "Tierra", "Veneno", "Pajaro"));

ALTER TABLE EVOLUCIONA ADD CONSTRAINT ck_evoluciona_modo_evoluciona
CHECK (modo_evoluciona IN ("Nivel", "Intercambio", "Objeto"));

ALTER TABLE MOVIMIENTO ADD CONSTRAINT ck_movimiento_tipo
CHECK (tipo IN ("Agua", "Bicho", "Dragon", "Electrico", "Fantasma", "Fuego", "Hielo", "Lucha", "Normal", "Planta", "Psiquico", "Roca", "Tierra", "Veneno", "Pajaro"));
ALTER TABLE MOVIMIENTO ADD CONSTRAINT ck_movimiento_categoria
CHECK (categoria IN ("Fisico", "Especial", "Estado"));

ALTER TABLE APRENDE_MOVIMIENTO ADD CONSTRAINT ck_aprende_movimiento_modo_aprendizaje
CHECK (modo_aprendizaje IN ("Nivel", "Evolucion", "Tutor"));

ALTER TABLE OBJETO ADD CONSTRAINT ck_objeto_modo_obtencion
CHECK (modo_obtencion IN ("Compra", "Suelo", "Persona", "Pokemon"));

ALTER TABLE AFECTA_POKEMON ADD CONSTRAINT ck_afecta_pokemon_modo_afecta
CHECK (modo_afecta IN ("Sana", "Captura", "Evoluciona"));