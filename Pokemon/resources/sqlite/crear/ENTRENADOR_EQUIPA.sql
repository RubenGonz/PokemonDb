CREATE TABLE IF NOT EXISTS ENTRENADOR_EQUIPA (
    id_entrenador INT CHECK (id_entrenador > 0),
    id_objeto INT CHECK (id_objeto > 0),
    cantidad INT CHECK (cantidad > 0),    
    PRIMARY KEY (id_entrenador, id_objeto),
    FOREIGN KEY (id_entrenador) REFERENCES ENTRENADOR (id_entrenador),
    FOREIGN KEY (id_objeto) REFERENCES OBJETO (id_objeto)
);