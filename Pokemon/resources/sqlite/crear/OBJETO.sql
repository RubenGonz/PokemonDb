CREATE TABLE IF NOT EXISTS OBJETO (
    id_objeto INT CHECK (id_objeto > 0),
    nombre VARCHAR (25),
    modo_obtencion VARCHAR (20) CHECK (modo_obtencion IN ('Comprado', 'Recogido', 'Entregado')),
    PRIMARY KEY (id_objeto)
);