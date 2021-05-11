CREATE TABLE POKEBALL (
    id_objeto INT CHECK (id_objeto > 0),
    ratio FLOAT CHECK (ratio > 0),
    PRIMARY KEY (id_objeto),
    FOREIGN KEY (id_objeto) REFERENCES OBJETO (id_objeto)
);