package es.iespuertolacruz.pokemon.api;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Clase que testea la api de Tipo
 */
public class TipoTest {

    // Variables de la clase

    Tipo tipo1;
    Tipo tipo2;
    Tipo tipo3;

    // BeforeEach y AfterEach

    @BeforeEach
    public void SetUp() {
        tipo1 = crearTipo();
        tipo2 = new Tipo("Agua", "Azul");
        tipo3 = new Tipo("Agua'Azul");
    }

    // Test

    @Test
    public void toStringTest() {
        assertEquals("Agua'Azul", tipo1.toString(), "El texto recibido no era el esperado");
    }

    @Test
    public void EqualsTest() {
        assertEquals(tipo2, tipo3, "Los objetos son iguales y equals no lo reconoce");
    }

    // Funciones y metodos

    private Tipo crearTipo() {
        Tipo tipo;
        tipo = new Tipo();
        tipo.setNombre("Agua");
        tipo.setColor("Azul");
        return tipo;
    }
}
