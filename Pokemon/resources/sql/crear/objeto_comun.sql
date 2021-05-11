CREATE TABLE OBJETO_COMUN (
    id_objeto INT CHECK (id_objeto > 0),
    efecto VARCHAR (75),
    PRIMARY KEY (id_objeto),
    FOREIGN KEY (id_objeto) REFERENCES OBJETO (id_objeto)
);