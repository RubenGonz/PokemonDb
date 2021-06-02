package es.iespuertolacruz.pokemon.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import es.iespuertolacruz.pokemon.api.Tiene;
import es.iespuertolacruz.pokemon.excepciones.PersistenciaException;
import es.iespuertolacruz.pokemon.excepciones.PokemonException;

public class TieneControllerTest {

    // Variables de clase

    static TieneController tieneController;
    Tiene tiene = null;

    @BeforeAll
    public static void beforeAll() {
        if (tieneController == null) {
            try {
                tieneController = new TieneController();
            } catch (Exception e) {
                fail("Se ha producido un error iniciando tieneController");
            }
        }
    }

    @BeforeEach
    public void crearTiene() {
        insertarTieneTest();
    }

    @AfterEach
    public void eliminarTiene() {
        if (tiene != null) {
            eliminarTieneTest();
        }
    }

    // Test

    @Test
    public void insertarTieneTest() {
        tiene = new Tiene(1,18,1); 
        try {
            tieneController.insertar(tiene);
        } catch (PokemonException | PersistenciaException e) {
            assertTrue(e.getMessage().contains("El tiene indicado ya existe"), "No se recibio el mensaje esperado");
        }
    }

    @Test
    public void validarTest() {
        Tiene tieneInvalido = new Tiene(0, 0, 0);
        try {
            tieneController.validar(tieneInvalido);
        } catch (PokemonException e) {
            assertTrue(e.getMessage().contains(""));
        }
    }

    @Test
    public void validarNuloTest() {
        Tiene tieneInvalido = null;
        try {
            tieneController.validar(tieneInvalido);
        } catch (PokemonException e) {
            assertTrue(e.getMessage().contains("Se esta validando un objeto nulo"),
                    "No se recibio el mensaje esperado");
        }
    }

    @Test
    public void eliminarTieneTest() {
        try {
            tieneController.eliminar(tiene);
        } catch (PokemonException | PersistenciaException e) {
            assertTrue(e.getMessage().contains("El tiene indicado no existe"), "No se recibio el mensaje esperado");
        }
    }

    @Test
    public void eliminarPorIdEntrenadorTest() {
        try {
            tieneController.eliminar(1,18); 
        } catch (PokemonException | PersistenciaException e) {
            assertTrue(e.getMessage().contains("El tiene indicado no existe"), "No se recibio el mensaje esperado");
        }
    }

    @Test
    public void buscartieneTest() {
        try {
            Tiene tieneEncontrado = tieneController.buscar(tiene.getIdEntrenador(), tiene.getNumeroPokedex());
            assertNotNull(tieneEncontrado, "No se debe de obtener un elemento nulo");
            assertEquals(tiene, tieneEncontrado, "No se ha encontrado lo esperado");
        } catch (PersistenciaException e) {
            fail("Se ha producido un error en la consulta del pokemon ,e:" + e.getMessage());
        }
    }

    @Test
    public void modificartieneExistenteTest() {
        try {
            tieneController.modificar(tiene);
        } catch (PokemonException | PersistenciaException e) {
            fail("No deberia llgar ningun mensaje de error y llega");
        }
    }

    @Test
    public void modificartieneInexistenteTest() {
        Tiene tieneInexistente = new Tiene(5000, 5000, 5000);
        try {
            tieneController.modificar(tieneInexistente);
        } catch (PokemonException | PersistenciaException e) {
            assertEquals("El tiene indicado no existe", e.getMessage(), "El mensaje recibido no es el esperado");
        }
    }

}
