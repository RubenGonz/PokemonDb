CREATE TABLE IF NOT EXISTS POKEMON_EQUIPA (
    numero_pokedex INT CHECK (numero_pokedex > 0),
    id_objeto INT CHECK (id_objeto > 0),
    PRIMARY KEY (numero_pokedex, id_objeto),
    FOREIGN KEY (numero_pokedex) REFERENCES POKEMON (numero_pokedex),
    FOREIGN KEY (id_objeto) REFERENCES OBJETO (id_objeto)
);