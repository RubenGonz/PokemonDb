CREATE TABLE TIENE (
    id_entrenador INT CHECK (id_entrenador > 0),
    numero_pokedex INT CHECK (numero_pokedex > 0),
    cantidad INT CHECK (cantidad > 0),
    PRIMARY KEY (id_entrenador, numero_pokedex),
    FOREIGN KEY (id_entrenador) REFERENCES ENTRENADOR (id_entrenador),
    FOREIGN KEY (numero_pokedex) REFERENCES POKEMON (numero_pokedex)
);