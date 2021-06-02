package es.iespuertolacruz.pokemon.api;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Clase que testea la api de Provoca
 */
public class ProvocaTest {

    // Variables de la clase

    Provoca provoca1;
    Provoca provoca2;
    Provoca provoca3;

    // BeforeEach y AfterEach

    @BeforeEach
    public void SetUp() {
        provoca1 = crearProvoca();
        provoca2 = new Provoca(15, 1);
        provoca3 = new Provoca("15'1");
    }

    // Test

    @Test
    public void toStringTest() {
        assertEquals("15'1", provoca1.toString(), "El texto recibido no era el esperado");
    }

    @Test
    public void EqualsTest() {
        assertEquals(provoca2, provoca3, "Los objetos son iguales y equals no lo reconoce");
    }

    // Funciones y metodos

    private Provoca crearProvoca() {
        Provoca provoca;
        provoca = new Provoca();
        provoca.setIdMovimiento(15);
        provoca.setIdEstado(1);
        return provoca;
    }
}
