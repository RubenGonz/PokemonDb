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
    id_caracteristica INT,
    peso FLOAT,
    altura FLOAT,
    especie VARCHAR (20),
    habilidad VARCHAR (20),
    categoria VARCHAR (20),
    PRIMARY KEY (id_caracteristica)
);

/**
 * Tabla donde se ven las estadisticas base de un pokemon
*/
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

/**
 * Tabla principal donde estan los pokemon
*/
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

/**
 * Tabla donde se ve las evoluciones de un pokemon
*/
CREATE TABLE EVOLUCIONA (
    numero_pokedex_origen INT,
    numero_pokedex_destino INT,
    modo_evoluciona VARCHAR (20),
    PRIMARY KEY (numero_pokedex_origen, numero_pokedex_destino),
    FOREIGN KEY (numero_pokedex_origen) REFERENCES POKEMON (numero_pokedex),
    FOREIGN KEY (numero_pokedex_destino) REFERENCES POKEMON (numero_pokedex)
);

/**
 * Tabla donde se ven las movimientos que pude hacer un pokemon
*/
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

/**
 * Tabla donde se ve como un pokemon puede aprender un movimiento
*/
CREATE TABLE APRENDE_MOVIMIENTO (
    numero_pokedex INT,
    id_movimiento INT,
    modo_aprendizaje VARCHAR (20),
    PRIMARY KEY (numero_pokedex, id_movimiento),
    FOREIGN KEY (numero_pokedex) REFERENCES POKEMON (numero_pokedex),
    FOREIGN KEY (id_movimiento) REFERENCES MOVIMIENTO (id_movimiento)
);

/**
 * Tabla donde se ven los posibles estados que puede provocar un movimiento
*/
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

/**
 * Tabla donde se ven los objetos del juego
*/
CREATE TABLE OBJETO (
    id_objeto INT,
    nombre VARCHAR (25),
    modo_obtencion VARCHAR (20),
    precio_venta INT,
    PRIMARY KEY (id_objeto)
);

/**
 * Tabla donde se ven los objetos de tipo pokeball
*/
CREATE TABLE POKEBALL (
    id_objeto INT,
    ratio FLOAT,
    PRIMARY KEY (id_objeto),
    FOREIGN KEY (id_objeto) REFERENCES OBJETO (id_objeto)

);

/**
 * Tabla donde se ven los objetos comunes
*/
CREATE TABLE OBJETO_COMUN (
    id_objeto INT,
    efecto VARCHAR (75),
    PRIMARY KEY (id_objeto),
    FOREIGN KEY (id_objeto) REFERENCES OBJETO (id_objeto)
);

/**
 * Tabla donde se ven los objetos de tipo maquina
*/
CREATE TABLE MAQUINA (
    id_objeto INT,
    id_movimiento INT,
    PRIMARY KEY (id_objeto),
    FOREIGN KEY (id_objeto) REFERENCES OBJETO (id_objeto),
    FOREIGN KEY (id_movimiento) REFERENCES MOVIMIENTO (id_movimiento)
);

/**
 * Tabla donde se ven los efectos de un objeto en un pokemon
*/
CREATE TABLE AFECTA_POKEMON (
    numero_pokedex INT,
    id_objeto INT,
    modo_afecta VARCHAR (75),
    PRIMARY KEY (numero_pokedex, id_objeto),
    FOREIGN KEY (numero_pokedex) REFERENCES POKEMON (numero_pokedex),
    FOREIGN KEY (id_objeto) REFERENCES OBJETO (id_objeto)
);

/**
 * Tabla donde se ven los entrenadores del juego
*/
CREATE TABLE ENTRENADOR (
    id_entrenador INT,
    nombre VARCHAR (25),
    pokemon1 INT,
    pokemon2 INT,
    pokemon3 INT,
    PRIMARY KEY (id_entrenador)
);

/**
 * Tabla donde se ven los entrenadores de tipo villano
*/
CREATE TABLE VILLANO (
    id_entrenador INT,
    proposito VARCHAR (25),
    PRIMARY KEY (id_entrenador),
    FOREIGN KEY (id_entrenador) REFERENCES ENTRENADOR (id_entrenador)
);

/**
 * Tabla donde se ven los entrenadores de tipo lider de gimnasio
*/
CREATE TABLE LIDER_GIMNASIO (
    id_entrenador INT,
    medalla INT,
    PRIMARY KEY (id_entrenador),
    FOREIGN KEY (id_entrenador) REFERENCES ENTRENADOR (id_entrenador)
);

/**
 * Tabla donde se ven los entrenadores casuales
*/
CREATE TABLE ENTRENADOR_CASUAL (
    id_entrenador INT,
    cantidad_medalla INT,
    PRIMARY KEY (id_entrenador),
    FOREIGN KEY (id_entrenador) REFERENCES ENTRENADOR (id_entrenador)
);

/**
 * Tabla donde se ven los entrenadores de tipo campeon de liga
*/
CREATE TABLE CAMPEON_LIGA (
    id_entrenador INT,
    region VARCHAR (10),
    PRIMARY KEY (id_entrenador),
    FOREIGN KEY (id_entrenador) REFERENCES ENTRENADOR (id_entrenador)
);

/**
 * Tabla donde se ven los entrenadores de tipo alto mando
*/
CREATE TABLE ALTO_MANDO (
    id_entrenador INT,
    tipo_principal VARCHAR (10),
    PRIMARY KEY (id_entrenador),
    FOREIGN KEY (id_entrenador) REFERENCES ENTRENADOR (id_entrenador)
);

/**
 * Tabla donde se ven los efectos de un objeto en un entrenador
*/
CREATE TABLE AFECTA_ENTRENADOR (
    id_entrenador INT,
    id_objeto INT,
    modo_afecta VARCHAR (75),
    PRIMARY KEY (id_entrenador, id_objeto),
    FOREIGN KEY (id_entrenador) REFERENCES ENTRENADOR (id_entrenador),
    FOREIGN KEY (id_objeto) REFERENCES OBJETO (id_objeto)
);

-- Restricciones

/**
 * Un pokemon solo puede ser de una de estas categorias:
*/
ALTER TABLE CARACTERISTICAS ADD CONSTRAINT ck_caracteristicas_categoria 
CHECK (categoria IN ("Normal", "Starter", "Semi-legendario", "Legendario"));

/**
 * Un pokemon puede tener 1 o dos tipos
 * Sus tipos solo pueden corresponder a:
*/
ALTER TABLE POKEMON ADD CONSTRAINT ck_pokemon_tipo_principal 
CHECK (tipo_principal IN ("Agua", "Bicho", "Dragon", "Electrico", "Fantasma", "Fuego", "Hielo", "Lucha", "Normal", "Planta", "Psiquico", "Roca", "Tierra", "Veneno", "Pajaro"));
ALTER TABLE POKEMON ADD CONSTRAINT ck_pokemon_tipo_secundario 
CHECK (tipo_secundario IN ("Agua", "Bicho", "Dragon", "Electrico", "Fantasma", "Fuego", "Hielo", "Lucha", "Normal", "Planta", "Psiquico", "Roca", "Tierra", "Veneno", "Pajaro", NULL));

/**
 * Un pokemon puede evolucionar de varias maneras 
 * Esta tiene que corresponder con:
*/
ALTER TABLE EVOLUCIONA ADD CONSTRAINT ck_evoluciona_modo_evoluciona
CHECK (modo_evoluciona IN ("Nivel", "Intercambio", "Objeto"));

/**
 * Un movimiento solo puede tener 1 tipo
 * Su tipo solo puede corresponder a:
*/
ALTER TABLE MOVIMIENTO ADD CONSTRAINT ck_movimiento_tipo
CHECK (tipo IN ("Agua", "Bicho", "Dragon", "Electrico", "Fantasma", "Fuego", "Hielo", "Lucha", "Normal", "Planta", "Psiquico", "Roca", "Tierra", "Veneno", "Pajaro"));

/**
 * Un movimiento solo puede ser de una categoria
 * Su categoria solo puede corresponder a:
*/
ALTER TABLE MOVIMIENTO ADD CONSTRAINT ck_movimiento_categoria
CHECK (categoria IN ("Fisico", "Especial", "Estado"));

/**
 * Un pokemon puede aprender un movimiento de estas maneras:
*/
ALTER TABLE APRENDE_MOVIMIENTO ADD CONSTRAINT ck_aprende_movimiento_modo_aprendizaje
CHECK (modo_aprendizaje IN ("Nivel", "Evolucion", "Tutor"));

/**
 * Un objeto puede ser obtenido de estas cuatro formas:
*/
ALTER TABLE OBJETO ADD CONSTRAINT ck_objeto_modo_obtencion
CHECK (modo_obtencion IN ("Compra", "Suelo", "Persona", "Pokemon"));

/**
 * Un objeto puede afectar a un pokemon de una de estas maneras:
*/
ALTER TABLE AFECTA_POKEMON ADD CONSTRAINT ck_afecta_pokemon_modo_afecta
CHECK (modo_afecta IN ("Cura", "Captura", "Evoluciona", "Potencia"));

/**
 * Un lider de gimnasio solo puede tener una de las 8 medallas
*/
ALTER TABLE LIDER_GIMNASIO ADD CONSTRAINT ck_lider_gimnasio_medalla
CHECK (0 < medalla < 9);

/**
 * Un entrenador no puede tener mas de 8 medallas
*/
ALTER TABLE ENTRENADOR_CASUAL ADD CONSTRAINT ck_entrenador_casual_cantidad_medalla
CHECK (-1 < cantidad_medalla < 9);

/**
 * Un entrenador del alto mando solo puede tener 1 tipo principal
 * Su tipo solo puede corresponder a:
*/
ALTER TABLE ALTO_MANDO ADD CONSTRAINT ck_alto_mando_tipo_principal
CHECK (tipo_principal IN ("Agua", "Bicho", "Dragon", "Electrico", "Fantasma", "Fuego", "Hielo", "Lucha", "Normal", "Planta", "Psiquico", "Roca", "Tierra", "Veneno", "Pajaro"));


/**
 * Un objeto puede afectar a un entrenador de una de estas maneras:
*/
ALTER TABLE AFECTA_ENTRENADOR ADD CONSTRAINT ck_afecta_entrenador_modo_afecta
CHECK (modo_afecta IN ("Informa", "Ayuda"));