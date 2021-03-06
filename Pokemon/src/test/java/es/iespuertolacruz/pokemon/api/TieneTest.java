package es.iespuertolacruz.pokemon.api;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Clase que testea la api de Tiene
 */
public class TieneTest {

    // Variables de la clase

    Tiene tiene1;
    Tiene tiene2;
    Tiene tiene3;

    // BeforeEach y AfterEach

    @BeforeEach
    public void SetUp() {
        tiene1 = crearTiene();
        tiene2 = new Tiene(1, 18, 1);
        tiene3 = new Tiene("1'18'1");
    }

    // Test

    @Test
    public void toStringTest() {
        assertEquals("1'18'1", tiene1.toString(), "El texto recibido no era el esperado");
    }

    @Test
    public void EqualsTest() {
        assertEquals(tiene2, tiene3, "Los objetos son iguales y equals no lo reconoce");
    }

    // Funciones y metodos

    private Tiene crearTiene() {
        Tiene tiene;
        tiene = new Tiene();
        tiene.setIdEntrenador(1);
        tiene.setNumeroPokedex(18);
        tiene.setCantidad(1);
        return tiene;
    }
}
