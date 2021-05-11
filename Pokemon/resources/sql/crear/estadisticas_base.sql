CREATE TABLE ESTADISTICAS_BASE (
    id_estadisticas_base INT CHECK (id_estadisticas_base > 0),
    ps_base INT CHECK (ps_base > 0),
    ataque_base INT CHECK (ataque_base > 0),
    defensa_base INT CHECK (defensa_base > 0),
    ataque_especial_base INT CHECK (ataque_especial_base > 0),
    defensa_especial_base INT CHECK (defensa_especial_base > 0),
    velocidad_base INT CHECK (velocidad_base > 0),
    PRIMARY KEY (id_estadisticas_base)
);