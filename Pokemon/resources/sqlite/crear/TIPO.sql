CREATE TABLE IF NOT EXISTS TIPO (
    nombre VARCHAR (15) CHECK (nombre IN ('Agua', 'Bicho', 'Dragon', 'Electrico', 'Fantasma', 'Fuego', 'Hielo', 'Lucha', 'Normal', 'Planta', 'Psiquico', 'Roca', 'Tierra', 'Veneno', 'Pajaro')),
    color VARCHAR (20),
    PRIMARY KEY (nombre)
);