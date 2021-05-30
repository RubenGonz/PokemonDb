CREATE TABLE IF NOT EXISTS PROVOCA (
    id_movimiento INT,
    id_estado INT,    
    PRIMARY KEY (id_estado,id_movimiento),
    FOREIGN KEY (id_estado) REFERENCES ESTADO (id_estado),
    FOREIGN KEY (id_movimiento) REFERENCES MOVIMIENTO (id_movimiento)
);