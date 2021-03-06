package es.iespuertolacruz.pokemon.api;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Clase que testea la api de Estado
 */
public class EstadoTest {

    // Variables de la clase

    Estado estado1;
    Estado estado2;
    Estado estado3;

    // BeforeEach y AfterEach

    @BeforeEach
    public void SetUp() {
        estado1 = crearEstado();
        estado2 = new Estado(1, "Paralizado", 1, "Puede evitar que el pokemon ataque");
        estado3 = new Estado("1'Paralizado'1'Puede evitar que el pokemon ataque");
    }

    // Test

    @Test
    public void toStringTest() {
        assertEquals("1'Paralizado'1'Puede evitar que el pokemon ataque", estado1.toString(),
                "El texto recibido no era el esperado");
    }

    @Test
    public void EqualsTest() {
        assertEquals(estado2, estado3, "Los objetos son iguales y equals no lo reconoce");
    }

    // Funciones y metodos

    private Estado crearEstado() {
        Estado estado;
        estado = new Estado();
        estado.setId(1);
        estado.setNombre("Paralizado");
        estado.setPersistencia(1);
        estado.setEfecto("Puede evitar que el pokemon ataque");
        return estado;
    }

}
