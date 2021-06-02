package es.iespuertolacruz.pokemon.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import es.iespuertolacruz.pokemon.api.CampeonLiga;
import es.iespuertolacruz.pokemon.excepciones.PersistenciaException;
import es.iespuertolacruz.pokemon.excepciones.PokemonException;

/**
 * Clase controller de CampeonLiga
 */
public class CampeonLigaControllerTest {

    // Variables de clase

    static CampeonLigaController campeonLigaController;
    CampeonLiga campeonLiga;

    // BeforeEach y AfterEach

    @BeforeAll
    public static void beforeAll() {
        if (campeonLigaController == null) {
            try {
                campeonLigaController = new CampeonLigaController();
            } catch (Exception e) {
                fail("Se ha producido un error iniciando campeonLigaController");
            }
        }
    }

    @BeforeEach
    public void crearCampeonLiga() {
        insertarCampeonLigaTest();
    }

    @AfterEach
    public void eliminarCampeonLiga() {
        if (campeonLiga != null) {
            eliminarCampeonLigaTest();
        }
    }

    // Test

    @Test
    public void insertarCampeonLigaTest() {
        campeonLiga = new CampeonLiga(1, "Johto");
        try {
            campeonLigaController.insertar(campeonLiga);
        } catch (PokemonException | PersistenciaException e) {
            assertTrue(e.getMessage().contains("El campeonLiga indicado ya existe"),
                    "No se recibio el mensaje esperado");
        }
    }

    @Test
    public void validarTest() {
        CampeonLiga campeonLigaInvalido = new CampeonLiga(0, "");
        try {
            campeonLigaController.validar(campeonLigaInvalido);
        } catch (PokemonException e) {
            assertTrue(e.getMessage().contains(""));
        }
    }

    @Test
    public void validarNuloTest() {
        CampeonLiga campeonLigaInvalido = null;
        try {
            campeonLigaController.validar(campeonLigaInvalido);
        } catch (PokemonException e) {
            assertTrue(e.getMessage().contains("Se esta validando un objeto nulo"),
                    "No se recibio el mensaje esperado");
        }
    }

    @Test
    public void eliminarCampeonLigaTest() {
        try {
            campeonLigaController.eliminar(campeonLiga);
        } catch (PokemonException | PersistenciaException e) {
            assertTrue(e.getMessage().contains("El campeonLiga indicado no existe"),
                    "No se recibio el mensaje esperado");
        }
    }

    @Test
    public void eliminarPorIdEntrenadorTest() {
        try {
            campeonLigaController.eliminar(1);
        } catch (PokemonException | PersistenciaException e) {
            assertTrue(e.getMessage().contains("El campeonLiga indicado no existe"),
                    "No se recibio el mensaje esperado");
        }
    }

    @Test
    public void buscarcampeonLigaTest() {
        try {
            CampeonLiga campeonLigaEncontrado = campeonLigaController.buscar(campeonLiga.getIdEntrenador());
            assertNotNull(campeonLigaEncontrado, "No se debe de obtener un elemento nulo");
            assertEquals(campeonLiga, campeonLigaEncontrado, "No se ha encontrado lo esperado");
        } catch (PersistenciaException e) {
            fail("Se ha producido un error en la consulta del pokemon ,e:" + e.getMessage());
        }
    }

    @Test
    public void modificarcampeonLigaExistenteTest() {
        try {
            campeonLigaController.modificar(campeonLiga);
        } catch (PokemonException | PersistenciaException e) {
            fail("No deberia llgar ningun mensaje de error y llega");
        }
    }

    @Test
    public void modificarcampeonLigaInexistenteTest() {
        CampeonLiga campeonLigaInexistente = new CampeonLiga(2, "Sinnoh");
        try {
            campeonLigaController.modificar(campeonLigaInexistente);
        } catch (PokemonException | PersistenciaException e) {
            assertEquals("El campeonLiga indicado no existe", e.getMessage(), "El mensaje recibido no es el esperado");
        }
    }

}
