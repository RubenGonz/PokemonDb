package es.iespuertolacruz.pokemon;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import es.iespuertolacruz.pokemon.api.Caracteristicas;

public class CaracteristicasTest {
    
    // Variables de la clase

    Caracteristicas caracteristicas1;
    Caracteristicas caracteristicas2;

    //BeforeEach y AfterEach

    @BeforeEach
    public void SetUp() {
        caracteristicas1 = crearCaracteristicas();
        caracteristicas2 = new Caracteristicas(6.9f, 0.7f, "Semilla", "Espesura", "Verde", "Starter");
    }

    //Test

    @Test
    public void equalsTest() {
        assertTrue(caracteristicas1.equals(caracteristicas2), "Las caracteristicas son iguales pero el equals dice lo contrario");
    }

    @Test
    public void toStringTest() {
        assertTrue(caracteristicas1.toString().contains("Starter"), "El texto recibido no era el esperado");
    }

    //Funciones y metodos

    private Caracteristicas crearCaracteristicas() {
        Caracteristicas caracteristicas;
        caracteristicas = new Caracteristicas();
        caracteristicas.setPeso(6.9f);
        caracteristicas.setAltura(0.7f);
        caracteristicas.setEspecie("Semilla");
        caracteristicas.setHabilidad("Espesura");
        caracteristicas.setColor("Verde");
        caracteristicas.setCategoria("Starter");
        return caracteristicas;
    }

}
