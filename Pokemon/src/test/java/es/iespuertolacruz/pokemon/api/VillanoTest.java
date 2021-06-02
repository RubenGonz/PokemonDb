package es.iespuertolacruz.pokemon.api;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Clase que testea la api de Villano
 */
public class VillanoTest {

    // Variables de la clase

    Villano villano1;
    Villano villano2;
    Villano villano3;

    // BeforeEach y AfterEach

    @BeforeEach
    public void SetUp() {
        villano1 = crearVillano();
        villano2 = new Villano(13, "Dominar el universo usando a todos los Pokemon");
        villano3 = new Villano("13'Dominar el universo usando a todos los Pokemon");
    }

    // Test

    @Test
    public void toStringTest() {
        assertEquals("13'Dominar el universo usando a todos los Pokemon", villano1.toString(),
                "El texto recibido no era el esperado");
    }

    @Test
    public void EqualsTest() {
        assertEquals(villano2, villano3, "Los objetos son iguales y equals no lo reconoce");
    }

    // Funciones y metodos

    private Villano crearVillano() {
        Villano villano;
        villano = new Villano();
        villano.setIdEntrenador(13);
        villano.setProposito("Dominar el universo usando a todos los Pokemon");
        return villano;
    }
}
