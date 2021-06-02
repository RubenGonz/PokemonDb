package es.iespuertolacruz.pokemon.api;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Clase que testea la api de Pertenece
 */
public class PerteneceTest {

    // Variables de la clase

    Pertenece pertenece1;
    Pertenece pertenece2;
    Pertenece pertenece3;

    // BeforeEach y AfterEach

    @BeforeEach
    public void SetUp() {
        pertenece1 = crearPertenece();
        pertenece2 = new Pertenece(1, "Planta");
        pertenece3 = new Pertenece("1'Planta");
    }

    // Test

    @Test
    public void toStringTest() {
        assertEquals("1'Planta", pertenece1.toString(), "El texto recibido no era el esperado");
    }

    @Test
    public void EqualsTest() {
        assertEquals(pertenece2, pertenece3, "Los objetos son iguales y equals no lo reconoce");
    }

    // Funciones y metodos

    private Pertenece crearPertenece() {
        Pertenece pertenece;
        pertenece = new Pertenece();
        pertenece.setNumeroPokedex(1);
        pertenece.setTipo("Planta");
        return pertenece;
    }
}
