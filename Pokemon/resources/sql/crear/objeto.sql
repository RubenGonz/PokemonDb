CREATE TABLE OBJETO (
    id_objeto INT CHECK (id_objeto > 0),
    nombre VARCHAR (25),
    modo_obtencion VARCHAR (20) CHECK (modo_obtencion IN ("Comprado", "Recogido", "Entregado")),
    precio_venta INT CHECK (precio_venta > 0),
    PRIMARY KEY (id_objeto)
);