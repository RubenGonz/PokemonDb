package es.iespuertolacruz.pokemon.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import es.iespuertolacruz.pokemon.api.EntrenadorEquipa;
import es.iespuertolacruz.pokemon.excepciones.PersistenciaException;
import es.iespuertolacruz.pokemon.excepciones.PokemonException;

public class EntrenadorEquipaControllerTest {

    // Variables de clase

    static EntrenadorEquipaController entrenadorEquipaController;
    EntrenadorEquipa entrenadorEquipa = null;

    @BeforeAll
    public static void beforeAll() {
        if (entrenadorEquipaController == null) {
            try {
                entrenadorEquipaController = new EntrenadorEquipaController();
            } catch (Exception e) {
                fail("Se ha producido un error iniciando entrenadorEquipaController");
            }
        }
    }

    @BeforeEach
    public void crearEntrenadorEquipa() {
        insertarEntrenadorEquipaTest();
    }

    @AfterEach
    public void eliminarEntrenadorEquipa() {
        if (entrenadorEquipa != null) {
            eliminarentrenadorEquipaTest();
        }
    }

    // Test

    @Test
    public void insertarEntrenadorEquipaTest() {
        entrenadorEquipa = new EntrenadorEquipa(1, 1, 1);
        try {
            entrenadorEquipaController.insertar(entrenadorEquipa);
        } catch (PokemonException | PersistenciaException e) {
            assertTrue(e.getMessage().contains("El entrenadorEquipa indicado ya existe"), "No se recibio el mensaje esperado");
        }
    }

    @Test
    public void validarTest() {
        EntrenadorEquipa entrenadorEquipaInvalido = new EntrenadorEquipa(0, 0, 0);
        try {
            entrenadorEquipaController.validar(entrenadorEquipaInvalido);
        } catch (PokemonException e) {
            assertTrue(e.getMessage().contains(""));
        }
    }

    @Test
    public void validarNuloTest() {
        EntrenadorEquipa entrenadorEquipaInvalido = null;
        try {
            entrenadorEquipaController.validar(entrenadorEquipaInvalido);
        } catch (PokemonException e) {
            assertTrue(e.getMessage().contains("Se esta validando un objeto nulo"),
                    "No se recibio el mensaje esperado");
        }
    }

    @Test
    public void eliminarentrenadorEquipaTest() {
        try {
            entrenadorEquipaController.eliminar(entrenadorEquipa);
        } catch (PokemonException | PersistenciaException e) {
            assertTrue(e.getMessage().contains("El entrenadorEquipa indicado no existe"),
                    "No se recibio el mensaje esperado");
        }
    }

    @Test
    public void eliminarPorIdEntrenadorTest() {
        try {
            entrenadorEquipaController.eliminar(1, 1);
        } catch (PokemonException | PersistenciaException e) {
            assertTrue(e.getMessage().contains("El entrenadorEquipa indicado no existe"),
                    "No se recibio el mensaje esperado");
        }
    }

    @Test
    public void buscarentrenadorEquipaTest() {
        try {
            EntrenadorEquipa entrenadorEquipaEncontrado = entrenadorEquipaController
                    .buscar(entrenadorEquipa.getIdEntrenador(), entrenadorEquipa.getIdObjeto());
            assertNotNull(entrenadorEquipaEncontrado, "No se debe de obtener un elemento nulo");
            assertEquals(entrenadorEquipa, entrenadorEquipaEncontrado, "No se ha encontrado lo esperado");
        } catch (PersistenciaException e) {
            fail("Se ha producido un error en la consulta del pokemon ,e:" + e.getMessage());
        }
    }

    @Test
    public void modificarentrenadorEquipaExistenteTest() {
        try {
            entrenadorEquipaController.modificar(entrenadorEquipa);
        } catch (PokemonException | PersistenciaException e) {
            fail("No deberia llegar ningun mensaje de error y llega");
        }
    }

    @Test
    public void modificarentrenadorEquipaInexistenteTest() {
        EntrenadorEquipa entrenadorEquipaInexistente = new EntrenadorEquipa(5000, 5000, 5000);
        try {
            entrenadorEquipaController.modificar(entrenadorEquipaInexistente);
        } catch (PokemonException | PersistenciaException e) {
            assertEquals("El entrenadorEquipa indicado no existe", e.getMessage(),
                    "El mensaje recibido no es el esperado");
        }
    }

}
