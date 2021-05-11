CREATE TABLE EVOLUCIONA (
    numero_pokedex_origen INT CHECK (numero_pokedex_origen > 0),
    numero_pokedex_destino INT CHECK (numero_pokedex_destino > 0),
    modo_evoluciona VARCHAR (20) CHECK (modo_evoluciona IN ("Nivel", "Intercambio", "Piedra")),
    PRIMARY KEY (numero_pokedex_origen, numero_pokedex_destino),
    FOREIGN KEY (numero_pokedex_origen) REFERENCES POKEMON (numero_pokedex),
    FOREIGN KEY (numero_pokedex_destino) REFERENCES POKEMON (numero_pokedex)
);