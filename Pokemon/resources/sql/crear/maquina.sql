CREATE TABLE MAQUINA (
    id_objeto INT CHECK (id_objeto > 0),
    id_movimiento INT CHECK (id_movimiento > 0),
    PRIMARY KEY (id_objeto),
    FOREIGN KEY (id_objeto) REFERENCES OBJETO (id_objeto),
    FOREIGN KEY (id_movimiento) REFERENCES MOVIMIENTO (id_movimiento)
);