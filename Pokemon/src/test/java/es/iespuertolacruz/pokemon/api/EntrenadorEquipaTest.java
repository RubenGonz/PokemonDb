package es.iespuertolacruz.pokemon.api;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Clase que testea la api de EntrenadorEquipa
 */
public class EntrenadorEquipaTest {

    // Variables de la clase

    EntrenadorEquipa entrenadorEquipa1;
    EntrenadorEquipa entrenadorEquipa2;
    EntrenadorEquipa entrenadorEquipa3;

    // BeforeEach y AfterEach

    @BeforeEach
    public void SetUp() {
        entrenadorEquipa1 = crearEntrenadorEquipa();
        entrenadorEquipa2 = new EntrenadorEquipa(1, 1, 1);
        entrenadorEquipa3 = new EntrenadorEquipa("1'1'1");
    }

    // Test

    @Test
    public void toStringTest() {
        assertEquals("1'1'1", entrenadorEquipa1.toString(), "El texto recibido no era el esperado");
    }

    @Test
    public void EqualsTest() {
        assertEquals(entrenadorEquipa2, entrenadorEquipa3, "Los objetos son iguales y equals no lo reconoce");
    }

    // Funciones y metodos

    private EntrenadorEquipa crearEntrenadorEquipa() {
        EntrenadorEquipa entrenadorEquipa;
        entrenadorEquipa = new EntrenadorEquipa();
        entrenadorEquipa.setIdEntrenador(1);
        entrenadorEquipa.setIdObjeto(1);
        entrenadorEquipa.setCantidad(1);
        return entrenadorEquipa;
    }
}
