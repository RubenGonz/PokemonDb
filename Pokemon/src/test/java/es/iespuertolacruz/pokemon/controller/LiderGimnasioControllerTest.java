package es.iespuertolacruz.pokemon.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import es.iespuertolacruz.pokemon.api.LiderGimnasio;
import es.iespuertolacruz.pokemon.excepciones.PersistenciaException;
import es.iespuertolacruz.pokemon.excepciones.PokemonException;

/**
 * Clase controller de LiderGimnasio
 */
public class LiderGimnasioControllerTest {

    // Variables de clase

    static LiderGimnasioController liderGimnasioController;
    LiderGimnasio liderGimnasio = null;

    //BeforeEach y AfterEach

    @BeforeAll
    public static void beforeAll() {
        if (liderGimnasioController == null) {
            try {
                liderGimnasioController = new LiderGimnasioController();
            } catch (Exception e) {
                fail("Se ha producido un error iniciando liderGimnasioController");
            }
        }
    }

    @BeforeEach
    public void crearliderGimnasio() {
        insertarLiderGimnasioTest();
    }

    @AfterEach
    public void eliminarliderGimnasio() {
        if (liderGimnasio != null) {
            eliminarliderGimnasioTest();
        }
    }

    // Test

    @Test
    public void insertarLiderGimnasioTest() {
        liderGimnasio = new LiderGimnasio(1, 1);
        try {
            liderGimnasioController.insertar(liderGimnasio);
        } catch (PokemonException | PersistenciaException e) {
            assertTrue(e.getMessage().contains("El liderGimnasio indicado ya existe"),
                    "No se recibio el mensaje esperado");
        }
    }

    @Test
    public void validarTest() {
        LiderGimnasio liderGimnasioInvalido = new LiderGimnasio(0, 0);
        try {
            liderGimnasioController.validar(liderGimnasioInvalido);
        } catch (PokemonException e) {
            assertTrue(e.getMessage().contains(""));
        }
    }

    @Test
    public void validarNuloTest() {
        LiderGimnasio liderGimnasioInvalido = null;
        try {
            liderGimnasioController.validar(liderGimnasioInvalido);
        } catch (PokemonException e) {
            assertTrue(e.getMessage().contains("Se esta validando un objeto nulo"),
                    "No se recibio el mensaje esperado");
        }
    }

    @Test
    public void eliminarliderGimnasioTest() {
        try {
            liderGimnasioController.eliminar(liderGimnasio);
        } catch (PokemonException | PersistenciaException e) {
            assertTrue(e.getMessage().contains("El liderGimnasio indicado no existe"),
                    "No se recibio el mensaje esperado");
        }
    }

    @Test
    public void eliminarPorIdEntrenadorTest() {
        try {
            liderGimnasioController.eliminar(1);
        } catch (PokemonException | PersistenciaException e) {
            assertTrue(e.getMessage().contains("El liderGimnasio indicado no existe"),
                    "No se recibio el mensaje esperado");
        }
    }

    @Test
    public void buscarliderGimnasioTest() {
        try {
            LiderGimnasio liderGimnasioEncontrado = liderGimnasioController.buscar(liderGimnasio.getIdEntrenador());
            assertNotNull(liderGimnasioEncontrado, "No se debe de obtener un elemento nulo");
            assertEquals(liderGimnasio, liderGimnasioEncontrado, "No se ha encontrado lo esperado");
        } catch (PersistenciaException e) {
            fail("Se ha producido un error en la consulta del pokemon ,e:" + e.getMessage());
        }
    }

    @Test
    public void modificarliderGimnasioExistenteTest() {
        try {
            liderGimnasioController.modificar(liderGimnasio);
        } catch (PokemonException | PersistenciaException e) {
            fail("No deberia llgar ningun mensaje de error y llega");
        }
    }

    @Test
    public void modificarliderGimnasioInexistenteTest() {
        LiderGimnasio liderGimnasioInexistente = new LiderGimnasio(5000, 5000);
        try {
            liderGimnasioController.modificar(liderGimnasioInexistente);
        } catch (PokemonException | PersistenciaException e) {
            assertEquals("El liderGimnasio indicado no existe", e.getMessage(),
                    "El mensaje recibido no es el esperado");
        }
    }
}
