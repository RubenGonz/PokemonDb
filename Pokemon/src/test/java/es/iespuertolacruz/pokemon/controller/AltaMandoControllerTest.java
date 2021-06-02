package es.iespuertolacruz.pokemon.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import es.iespuertolacruz.pokemon.api.AltoMando;
import es.iespuertolacruz.pokemon.excepciones.PersistenciaException;
import es.iespuertolacruz.pokemon.excepciones.PokemonException;

/**
 * Clase controller de AltoMando
 */
public class AltaMandoControllerTest {

    // Variables de clase

    static AltoMandoController altoMandoController;
    AltoMando altoMando = null;

    // BeforeEach y AfterEach

    @BeforeAll
    public static void beforeAll() {
        if (altoMandoController == null) {
            try {
                altoMandoController = new AltoMandoController();
            } catch (Exception e) {
                fail("Se ha producido un error iniciando altoMandoController");
            }
        }
    }

    @BeforeEach
    public void crearAltoMando() {
        insertarAltoMandoTest();
    }

    @AfterEach
    public void eliminarAltoMando() {
        if (altoMando != null) {
            eliminarAltoMandoTest();
        }
    }

    // Test

    @Test
    public void insertarAltoMandoTest() {
        altoMando = new AltoMando(1, "Hielo");
        try {
            altoMandoController.insertar(altoMando);
        } catch (PokemonException | PersistenciaException e) {
            assertTrue(e.getMessage().contains("El altoMando indicado ya existe"), "No se recibio el mensaje esperado");
        }
    }

    @Test
    public void validarTest() {
        AltoMando altoMandoInvalida = new AltoMando(0, "");
        try {
            altoMandoController.validar(altoMandoInvalida);
        } catch (PokemonException e) {
            assertTrue(e.getMessage().contains(""));
        }
    }

    @Test
    public void validarNuloTest() {
        AltoMando altoMandoInvalido = null;
        try {
            altoMandoController.validar(altoMandoInvalido);
        } catch (PokemonException e) {
            assertTrue(e.getMessage().contains("Se esta validando un objeto nulo"),
                    "No se recibio el mensaje esperado");
        }
    }

    @Test
    public void eliminarAltoMandoTest() {
        try {
            altoMandoController.eliminar(altoMando);
        } catch (PokemonException | PersistenciaException e) {
            assertTrue(e.getMessage().contains("El altoMando indicado no existe"), "No se recibio el mensaje esperado");
        }
    }

    @Test
    public void eliminarPorNombreTest() {
        try {
            altoMandoController.eliminar(1);
        } catch (PokemonException | PersistenciaException e) {
            assertTrue(e.getMessage().contains("El altoMando indicado no existe"), "No se recibio el mensaje esperado");
        }
    }

    @Test
    public void buscarAltoMandoTest() {
        try {
            AltoMando altoMandoEncontradas = altoMandoController.buscar(altoMando.getIdEntrenador());
            assertNotNull(altoMandoEncontradas, "No se debe de obtener un elemento nulo");
            assertEquals(altoMando, altoMandoEncontradas, "No se ha encontrado lo esperado");
        } catch (PersistenciaException e) {
            fail("Se ha producido un error en la consulta del altoMando ,e:" + e.getMessage());
        }
    }

    @Test
    public void modificarAltoMandoExistenteTest() {
        try {
            altoMandoController.modificar(altoMando);
        } catch (PokemonException | PersistenciaException e) {
            fail("No deberia llgar ningun mensaje de error y llega");
        }
    }

    @Test
    public void modificarAltoMandoInexistenteTest() {
        AltoMando altoMandoInexistente = new AltoMando(5000, "AltoMando nulo");
        try {
            altoMandoController.modificar(altoMandoInexistente);
        } catch (PokemonException | PersistenciaException e) {
            assertEquals("El altoMando indicado no existe", e.getMessage(), "El mensaje recibido no es el esperado");
        }
    }

}
