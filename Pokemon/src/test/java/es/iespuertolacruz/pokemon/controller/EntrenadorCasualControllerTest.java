package es.iespuertolacruz.pokemon.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import es.iespuertolacruz.pokemon.api.EntrenadorCasual;
import es.iespuertolacruz.pokemon.excepciones.PersistenciaException;
import es.iespuertolacruz.pokemon.excepciones.PokemonException;

/**
 * Clase controller de EntrenadorCasual
 */
public class EntrenadorCasualControllerTest {

    // Variables de clase

    static EntrenadorCasualController entrenadorCasualController;
    EntrenadorCasual entrenadorCasual = null;

    //BeforeEach y AfterEach

    @BeforeAll
    public static void beforeAll() {
        if (entrenadorCasualController == null) {
            try {
                entrenadorCasualController = new EntrenadorCasualController();
            } catch (Exception e) {
                fail("Se ha producido un error iniciando entrenadorCasualController");
            }
        }
    }

    @BeforeEach
    public void crearEntrenadorCasual() {
        insertarEntrenadorCasualTest();
    }

    @AfterEach
    public void eliminarEntrenadorCasual() {
        if (entrenadorCasual != null) {
            eliminarentrenadorCasualTest();
        }
    }

    // Test

    @Test
    public void insertarEntrenadorCasualTest() {
        entrenadorCasual = new EntrenadorCasual(1, 1);
        try {
            entrenadorCasualController.insertar(entrenadorCasual);
        } catch (PokemonException | PersistenciaException e) {
            assertTrue(e.getMessage().contains("El entrenadorCasual indicado ya existe"), "No se recibio el mensaje esperado");
        }
    }

    @Test
    public void validarTest() {
        EntrenadorCasual entrenadorCasualInvalido = new EntrenadorCasual(0, 0);
        try {
            entrenadorCasualController.validar(entrenadorCasualInvalido);
        } catch (PokemonException e) {
            assertTrue(e.getMessage().contains(""));
        }
    }

    @Test
    public void validarNuloTest() {
        EntrenadorCasual entrenadorCasualInvalido = null;
        try {
            entrenadorCasualController.validar(entrenadorCasualInvalido);
        } catch (PokemonException e) {
            assertTrue(e.getMessage().contains("Se esta validando un objeto nulo"),
                    "No se recibio el mensaje esperado");
        }
    }

    @Test
    public void eliminarentrenadorCasualTest() {
        try {
            entrenadorCasualController.eliminar(entrenadorCasual);
        } catch (PokemonException | PersistenciaException e) {
            assertTrue(e.getMessage().contains("El entrenadorCasual indicado no existe"),
                    "No se recibio el mensaje esperado");
        }
    }

    @Test
    public void eliminarPorIdEntrenadorTest() {
        try {
            entrenadorCasualController.eliminar(1);
        } catch (PokemonException | PersistenciaException e) {
            assertTrue(e.getMessage().contains("El entrenadorCasual indicado no existe"),
                    "No se recibio el mensaje esperado");
        }
    }

    @Test
    public void buscarentrenadorCasualTest() {
        try {
            EntrenadorCasual entrenadorCasualEncontrado = entrenadorCasualController
                    .buscar(entrenadorCasual.getIdEntrenador());
            assertNotNull(entrenadorCasualEncontrado, "No se debe de obtener un elemento nulo");
            assertEquals(entrenadorCasual, entrenadorCasualEncontrado, "No se ha encontrado lo esperado");
        } catch (PersistenciaException e) {
            fail("Se ha producido un error en la consulta del pokemon ,e:" + e.getMessage());
        }
    }

    @Test
    public void modificarentrenadorCasualExistenteTest() {
        try {
            entrenadorCasualController.modificar(entrenadorCasual);
        } catch (PokemonException | PersistenciaException e) {
            fail("No deberia llgar ningun mensaje de error y llega");
        }
    }

    @Test
    public void modificarentrenadorCasualInexistenteTest() {
        EntrenadorCasual entrenadorCasualInexistente = new EntrenadorCasual(5000, 5000);
        try {
            entrenadorCasualController.modificar(entrenadorCasualInexistente);
        } catch (PokemonException | PersistenciaException e) {
            assertEquals("El entrenador indicado no existe", e.getMessage(),
                    "El mensaje recibido no es el esperado");
        }
    }

}
