package es.iespuertolacruz.pokemon.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import es.iespuertolacruz.pokemon.api.Entrenador;
import es.iespuertolacruz.pokemon.excepciones.PersistenciaException;
import es.iespuertolacruz.pokemon.excepciones.PokemonException;

public class EntrenadorControllerTest {

    // Variables de clase

    static EntrenadorController entrenadorController;
    Entrenador entrenador = null;

    // Before y after

    @BeforeAll
    public static void beforeAll() {
        if (entrenadorController == null) {
            try {
                entrenadorController = new EntrenadorController();
            } catch (PersistenciaException e) {
                fail("Se ha producido un error iniciando entrenadorController");
            }
        }
    }

    @BeforeEach
    public void crearEntrenador() {
        insertarEntrenadorTest();
    }

    @AfterEach
    public void eliminarEntrenador() {
        if (entrenador != null) {
            eliminarEntrenadorTest();
        }
    }

    // Test

    @Test
    public void insertarEntrenadorTest() {
        entrenador = new Entrenador(1,"Azul");
        try {
            entrenadorController.insertar(entrenador);
        } catch (PokemonException | PersistenciaException e) {
            assertTrue(e.getMessage().contains("El entrenador indicado ya existe"),
                    "No se recibio el mensaje esperado");
        }
    }

    @Test
    public void validarTest() {
        Entrenador entrenadorInvalida = new Entrenador(0,"");
        try {
            entrenadorController.validar(entrenadorInvalida);
        } catch (PokemonException e) {
              assertTrue(e.getMessage().contains(""));
        }
    }

    @Test
    public void validarNuloTest() {
        Entrenador entrenadorInvalido = null;
        try {
            entrenadorController.validar(entrenadorInvalido);
        } catch (PokemonException e) {
            assertTrue(e.getMessage().contains("Se esta validando un objeto nulo"),
                    "No se recibio el mensaje esperado");
        }
    }

    @Test
    public void eliminarEntrenadorTest() {
        try {
            entrenadorController.eliminar(entrenador);
        } catch (PokemonException | PersistenciaException e) {
            assertTrue(e.getMessage().contains("El entrenador indicado no existe"),
                    "No se recibio el mensaje esperado");
        }
    }

    @Test
    public void eliminarPorNombreTest() {
        try {
            entrenadorController.eliminar(1);
        } catch (PokemonException | PersistenciaException e) {
            assertTrue(e.getMessage().contains("El entrenador indicado no existe"),
                    "No se recibio el mensaje esperado");
        }
    }

    @Test
    public void buscarEntrenadorTest() {
        try {
            Entrenador entrenadorEncontradas = entrenadorController.buscar(entrenador.getId());
            assertNotNull(entrenadorEncontradas, "No se debe de obtener un elemento nulo");
            assertEquals(entrenador, entrenadorEncontradas, "No se ha encontrado lo esperado");
        } catch (PersistenciaException e) {
            fail("Se ha producido un error en la consulta del entrenador ,e:" + e.getMessage());
        }
    }

    @Test
    public void modificarEntrenadorExistenteTest() {
        try {
            entrenadorController.modificar(entrenador);
        } catch (PokemonException | PersistenciaException e) {
            fail("No deberia llgar ningun mensaje de error y llega");
        }
    }

    @Test
    public void modificarEntrenadorInexistenteTest() {
        Entrenador entrenadorInexistente = new Entrenador(5000,"Entrenador nulo");
        try {
            entrenadorController.modificar(entrenadorInexistente);
        } catch (PokemonException | PersistenciaException e) {
            assertEquals("El entrenador indicado no existe", e.getMessage(),
                    "El mensaje recibido no es el esperado");
        }
    }

}
