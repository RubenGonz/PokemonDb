package es.iespuertolacruz.pokemon.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import es.iespuertolacruz.pokemon.api.ObjetoComun;
import es.iespuertolacruz.pokemon.excepciones.PersistenciaException;
import es.iespuertolacruz.pokemon.excepciones.PokemonException;

/**
 * Clase controller de ObjetoComun
 */
public class ObjetoComunControllerTest {

    // Variables de clase

    static ObjetoComunController objetoComunController;
    ObjetoComun objetoComun = null;

    //BeforeEach y AfterEach

    @BeforeAll
    public static void beforeAll() {
        if (objetoComunController == null) {
            try {
                objetoComunController = new ObjetoComunController();
            } catch (Exception e) {
                fail("Se ha producido un error iniciando objetoComunController");
            }
        }
    }

    @BeforeEach
    public void crearobjetoComun() {
        insertarObjetoComunTest();
    }

    @AfterEach
    public void eliminarobjetoComun() {
        if (objetoComun != null) {
            eliminarObjetoComunTest();
        }
    }

    // Test

    @Test
    public void insertarObjetoComunTest() {
        objetoComun = new ObjetoComun(1, "Pokedex");
        try {
            objetoComunController.insertar(objetoComun);
        } catch (PokemonException | PersistenciaException e) {
            assertTrue(e.getMessage().contains("El objetoComun indicado ya existe"),
                    "No se recibio el mensaje esperado");
        }
    }

    @Test
    public void validarTest() {
        ObjetoComun objetoComunInvalido = new ObjetoComun(0, "");
        try {
            objetoComunController.validar(objetoComunInvalido);
        } catch (PokemonException e) {
            assertTrue(e.getMessage().contains(""));
        }
    }

    @Test
    public void validarNuloTest() {
        ObjetoComun objetoComunInvalido = null;
        try {
            objetoComunController.validar(objetoComunInvalido);
        } catch (PokemonException e) {
            assertTrue(e.getMessage().contains("Se esta validando un objeto nulo"),
                    "No se recibio el mensaje esperado");
        }
    }

    @Test
    public void eliminarObjetoComunTest() {
        try {
            objetoComunController.eliminar(objetoComun);
        } catch (PokemonException | PersistenciaException e) {
            assertTrue(e.getMessage().contains("El objetoComun indicado no existe"),
                    "No se recibio el mensaje esperado");
        }
    }

    @Test
    public void eliminarPorIdEntrenadorTest() {
        try {
            objetoComunController.eliminar(1);
        } catch (PokemonException | PersistenciaException e) {
            assertTrue(e.getMessage().contains("El objetoComun indicado no existe"),
                    "No se recibio el mensaje esperado");
        }
    }

    @Test
    public void buscarobjetoComunTest() {
        try {
            ObjetoComun objetoComunEncontrado = objetoComunController.buscar(objetoComun.getIdObjeto());
            assertNotNull(objetoComunEncontrado, "No se debe de obtener un elemento nulo");
            assertEquals(objetoComun, objetoComunEncontrado, "No se ha encontrado lo esperado");
        } catch (PersistenciaException e) {
            fail("Se ha producido un error en la consulta del pokemon ,e:" + e.getMessage());
        }
    }

    @Test
    public void modificarobjetoComunExistenteTest() {
        try {
            objetoComunController.modificar(objetoComun);
        } catch (PokemonException | PersistenciaException e) {
            fail("No deberia llgar ningun mensaje de error y llega");
        }
    }

    @Test
    public void modificarobjetoComunInexistenteTest() {
        ObjetoComun objetoComunInexistente = new ObjetoComun(5000, "Inexistente");
        try {
            objetoComunController.modificar(objetoComunInexistente);
        } catch (PokemonException | PersistenciaException e) {
            assertEquals("El objetoComun indicado no existe", e.getMessage(), "El mensaje recibido no es el esperado");
        }
    }

}
