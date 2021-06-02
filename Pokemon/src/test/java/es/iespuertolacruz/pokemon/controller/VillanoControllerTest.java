package es.iespuertolacruz.pokemon.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import es.iespuertolacruz.pokemon.api.Villano;
import es.iespuertolacruz.pokemon.excepciones.PersistenciaException;
import es.iespuertolacruz.pokemon.excepciones.PokemonException;

/**
 * Clase controller de Villano
 */
public class VillanoControllerTest {

    // Variables de clase

    static VillanoController villanoController;
    Villano villano = null;

    // BeforeEach y AfterEach

    @BeforeAll
    public static void beforeAll() {
        if (villanoController == null) {
            try {
                villanoController = new VillanoController();
            } catch (Exception e) {
                fail("Se ha producido un error iniciando villanoController");
            }
        }
    }

    @BeforeEach
    public void crearVillano() {
        insertarVillanoTest();
    }

    @AfterEach
    public void eliminarVillano() {
        if (villano != null) {
            eliminarVillanoTest();
        }
    }

    // Test

    @Test
    public void insertarVillanoTest() {
        villano = new Villano(1, "Giovanni");
        try {
            villanoController.insertar(villano);
        } catch (PokemonException | PersistenciaException e) {
            assertTrue(e.getMessage().contains("El villano indicado ya existe"), "No se recibio el mensaje esperado");
        }
    }

    @Test
    public void validarTest() {
        Villano villanoInvalido = new Villano(0, "");
        try {
            villanoController.validar(villanoInvalido);
        } catch (PokemonException e) {
            assertTrue(e.getMessage().contains(""));
        }
    }

    @Test
    public void validarNuloTest() {
        Villano villanoInvalido = null;
        try {
            villanoController.validar(villanoInvalido);
        } catch (PokemonException e) {
            assertTrue(e.getMessage().contains("Se esta validando un objeto nulo"),
                    "No se recibio el mensaje esperado");
        }
    }

    @Test
    public void eliminarVillanoTest() {
        try {
            villanoController.eliminar(villano);
        } catch (PokemonException | PersistenciaException e) {
            assertTrue(e.getMessage().contains("El villano indicado no existe"), "No se recibio el mensaje esperado");
        }
    }

    @Test
    public void eliminarPorIdEntrenadorTest() {
        try {
            villanoController.eliminar(1);
        } catch (PokemonException | PersistenciaException e) {
            assertTrue(e.getMessage().contains("El villano indicado no existe"), "No se recibio el mensaje esperado");
        }
    }

    @Test
    public void buscarvillanoTest() {
        try {
            Villano villanoEncontrado = villanoController.buscar(villano.getIdEntrenador());
            assertNotNull(villanoEncontrado, "No se debe de obtener un elemento nulo");
            assertEquals(villano, villanoEncontrado, "No se ha encontrado lo esperado");
        } catch (PersistenciaException e) {
            fail("Se ha producido un error en la consulta del pokemon ,e:" + e.getMessage());
        }
    }

    @Test
    public void modificarvillanoExistenteTest() {
        try {
            villanoController.modificar(villano);
        } catch (PokemonException | PersistenciaException e) {
            fail("No deberia llgar ningun mensaje de error y llega");
        }
    }

    @Test
    public void modificarvillanoInexistenteTest() {
        Villano villanoInexistente = new Villano(5000, "Villano Inexistente");
        try {
            villanoController.modificar(villanoInexistente);
        } catch (PokemonException | PersistenciaException e) {
            assertEquals("El villano indicado no existe", e.getMessage(), "El mensaje recibido no es el esperado");
        }
    }

}
