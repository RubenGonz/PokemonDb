CREATE TABLE IF NOT EXISTS MOVIMIENTO (
    id_movimiento INT CHECK (id_movimiento > 0),
    nombre VARCHAR (25),
    tipo VARCHAR(10),
    categoria VARCHAR (10) CHECK (categoria IN ('Fisico', 'Especial', 'Estado')),
    pp INT CHECK (pp > 0),
    potencia INT CHECK (potencia >= 0),
    certeza INT CHECK (certeza > 0),
    PRIMARY KEY (id_movimiento),
    FOREIGN KEY (tipo) REFERENCES TIPO (nombre)
);