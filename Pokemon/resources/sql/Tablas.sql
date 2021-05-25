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
    modo_evoluciona VARCHAR (20) CHECK (modo_evoluciona IN ("Nivel", "Intercambio", "Piedra")),
    PRIMARY KEY (numero_pokedex_origen, numero_pokedex_destino),
    FOREIGN KEY (numero_pokedex_origen) REFERENCES POKEMON (numero_pokedex),
    FOREIGN KEY (numero_pokedex_destino) REFERENCES POKEMON (numero_pokedex)
);

/**
 * Tabla donde se ven los tipos
*/
CREATE TABLE TIPO (
    nombre VARCHAR (15) CHECK (nombre IN ("Agua", "Bicho", "Dragon", "Electrico", "Fantasma", "Fuego", "Hielo", "Lucha", "Normal", "Planta", "Psiquico", "Roca", "Tierra", "Veneno", "Pajaro")),
    color VARCHAR (20),
    PRIMARY KEY (nombre)
);

/**
 * Tabla donde se ven los tipos de los pokemon
*/
CREATE TABLE PERTENECE (
    numero_pokedex INT,
    tipo VARCHAR (15),
    PRIMARY KEY (numero_pokedex,tipo),
    FOREIGN KEY (tipo) REFERENCES TIPO (nombre),
    FOREIGN KEY (numero_pokedex) REFERENCES POKEMON (numero_pokedex)
);

/**
 * Tabla donde se ven los posibles estados de un pokemon
*/
CREATE TABLE ESTADO (
    id_estado INT CHECK (id_estado > 0),
    nombre VARCHAR (15),
    persistencia BOOLEAN,
    efecto VARCHAR (75),
    PRIMARY KEY (id_estado)
);

/**
 * Tabla donde se ven las movimientos que pude hacer un pokemon
*/
CREATE TABLE MOVIMIENTO (
    id_movimiento INT CHECK (id_movimiento > 0),
    nombre VARCHAR (25),
    tipo VARCHAR(10),
    categoria VARCHAR (10) CHECK (categoria IN ("Fisico", "Especial", "Estado")),
    pp INT CHECK (pp > 0),
    potencia INT CHECK (potencia >= 0),
    certeza INT CHECK (certeza > 0),
    PRIMARY KEY (id_movimiento),
    FOREIGN KEY (tipo) REFERENCES TIPO (nombre)
);

/**
 * Tabla donde se ven los estados que provoca un movimiento
*/
CREATE TABLE PROVOCA (
    id_movimiento INT,
    id_estado INT,    
    PRIMARY KEY (id_estado,id_movimiento),
    FOREIGN KEY (id_estado) REFERENCES ESTADO (id_estado),
    FOREIGN KEY (id_movimiento) REFERENCES MOVIMIENTO (id_movimiento)
);

/**
 * Tabla donde se ve que movimientos tiene un pokemon
*/
CREATE TABLE CONOCE (
    numero_pokedex INT CHECK (numero_pokedex > 0),
    id_movimiento INT CHECK (id_movimiento > 0),
    PRIMARY KEY (numero_pokedex, id_movimiento),
    FOREIGN KEY (numero_pokedex) REFERENCES POKEMON (numero_pokedex),
    FOREIGN KEY (id_movimiento) REFERENCES MOVIMIENTO (id_movimiento)
);

/**
 * Tabla donde se ven los objetos del juego
*/
CREATE TABLE OBJETO (
    id_objeto INT CHECK (id_objeto > 0),
    nombre VARCHAR (25),
    modo_obtencion VARCHAR (20) CHECK (modo_obtencion IN ("Comprado", "Recogido", "Entregado")),
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
 * Tabla donde se ve  objeto que lleva un pokemonn
*/
CREATE TABLE POKEMON_EQUIPA (
    numero_pokedex INT CHECK (numero_pokedex > 0),
    id_objeto INT CHECK (id_objeto > 0),
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
    proposito VARCHAR (75),
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
    tipo_principal VARCHAR (10), 
    PRIMARY KEY (id_entrenador),
    FOREIGN KEY (id_entrenador) REFERENCES ENTRENADOR (id_entrenador),
    FOREIGN KEY (tipo_principal) REFERENCES TIPO (nombre)
);

/**
 * Tabla donde se ven los objetos que equipa un entrenador
*/
CREATE TABLE ENTRENADOR_EQUIPA (
    id_entrenador INT CHECK (id_entrenador > 0),
    id_objeto INT CHECK (id_objeto > 0),
    cantidad INT CHECK (cantidad > 0),    
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