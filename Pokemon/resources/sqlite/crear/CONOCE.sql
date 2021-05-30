CREATE TABLE IF NOT EXISTS CONOCE (
    numero_pokedex INT CHECK (numero_pokedex > 0),
    id_movimiento INT CHECK (id_movimiento > 0),
    PRIMARY KEY (numero_pokedex, id_movimiento),
    FOREIGN KEY (numero_pokedex) REFERENCES POKEMON (numero_pokedex),
    FOREIGN KEY (id_movimiento) REFERENCES MOVIMIENTO (id_movimiento)
);