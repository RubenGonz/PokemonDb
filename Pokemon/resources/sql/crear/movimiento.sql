CREATE TABLE MOVIMIENTO (
    id_movimiento INT CHECK (id_movimiento > 0),
    nombre VARCHAR (25),
    tipo VARCHAR (10) CHECK (tipo IN ("Agua", "Bicho", "Dragon", "Electrico", "Fantasma", "Fuego", "Hielo", "Lucha", "Normal", "Planta", "Psiquico", "Roca", "Tierra", "Veneno", "Pajaro")),
    categoria VARCHAR (10) CHECK (categoria IN ("Fisico", "Especial", "Estado")),
    pp INT CHECK (pp > 0),
    potencia INT CHECK (potencia >= 0),
    certeza INT CHECK (certeza > 0),
    id_estado INT CHECK (id_estado > 0),
    PRIMARY KEY (id_movimiento),
    FOREIGN KEY (id_estado) REFERENCES ESTADO (id_estado)
);