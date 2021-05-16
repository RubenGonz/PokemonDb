package es.iespuertolacruz.pokemon.api;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CaracteristicasTest {
    
    // Variables de la clase

    Caracteristicas caracteristicas1;
    Caracteristicas caracteristicas2;
    Caracteristicas caracteristicas3;

    //BeforeEach y AfterEach

    @BeforeEach
    public void SetUp() {
        caracteristicas1 = crearCaracteristicas();
        caracteristicas2 = new Caracteristicas(1,6.9f, 0.7f, "Semilla", "Espesura", "Starter");
        caracteristicas3 = new Caracteristicas("1'6.9'0.7'Semilla'Espesura'Starter");
    }

    //Test

    @Test
    public void toStringTest() {
        assertEquals("1'6.9'0.7'Semilla'Espesura'Starter",caracteristicas1.toString(), "El texto recibido no era el esperado");
        assertEquals("1'6.9'0.7'Semilla'Espesura'Starter",caracteristicas2.toString(), "El texto recibido no era el esperado");
        assertEquals("1'6.9'0.7'Semilla'Espesura'Starter",caracteristicas3.toString(), "El texto recibido no era el esperado");
    }

    //Funciones y metodos

    private Caracteristicas crearCaracteristicas() {
        Caracteristicas caracteristicas;
        caracteristicas = new Caracteristicas();
        caracteristicas.setId(1);
        caracteristicas.setPeso(6.9f);
        caracteristicas.setAltura(0.7f);
        caracteristicas.setEspecie("Semilla");
        caracteristicas.setHabilidad("Espesura");
        caracteristicas.setCategoria("Starter");
        return caracteristicas;
    }

}
