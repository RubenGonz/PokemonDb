package es.iespuertolacruz.pokemon.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import es.iespuertolacruz.pokemon.api.Estado;
import es.iespuertolacruz.pokemon.excepciones.PersistenciaException;
import es.iespuertolacruz.pokemon.excepciones.PokemonException;

public class EstadoControllerTest {

    // Variables de clase

    static EstadoController estadoController;
    Estado estado = null;

    // Before y after

    @BeforeAll
    public static void beforeAll() {
        if (estadoController == null) {
            try {
                estadoController = new EstadoController();
            } catch (Exception e) {
                fail("Se ha producido un error iniciando estadoController");
            }
        }
    }

    @BeforeEach
    public void crearEstado() {
        insertarEstadoTest();
    }

    @AfterEach
    public void eliminarEstado() {
        if (estado != null) {
            eliminarEstadoTest();
        }
    }

    // Test

    @Test
    public void insertarEstadoTest() {
        estado = new Estado(1,"Paralizado",1,"Puede evitar que el pokemon ataque");
        try {
            estadoController.insertar(estado);
        } catch (PokemonException | PersistenciaException e) {
            assertTrue(e.getMessage().contains("El estado indicado ya existe"),
                    "No se recibio el mensaje esperado");
        }
    }

    @Test
    public void validarTest() {
        Estado estadoInvalida = new Estado(0,"",3,"");
        try {
            estadoController.validar(estadoInvalida);
        } catch (PokemonException e) {
              assertTrue(e.getMessage().contains(""));
        }
    }

    @Test
    public void validarNuloTest() {
        Estado estadoInvalido = null;
        try {
            estadoController.validar(estadoInvalido);
        } catch (PokemonException e) {
            assertTrue(e.getMessage().contains("Se esta validando un objeto nulo"),
                    "No se recibio el mensaje esperado");
        }
    }

    @Test
    public void eliminarEstadoTest() {
        try {
            estadoController.eliminar(estado);
        } catch (PokemonException | PersistenciaException e) {
            assertTrue(e.getMessage().contains("El estado indicado no existe"),
                    "No se recibio el mensaje esperado");
        }
    }

    @Test
    public void eliminarPorNombreTest() {
        try {
            estadoController.eliminar(1);
        } catch (PokemonException | PersistenciaException e) {
            assertTrue(e.getMessage().contains("El estado indicado no existe"),
                    "No se recibio el mensaje esperado");
        }
    }

    @Test
    public void buscarEstadoTest() {
        try {
            Estado estadoEncontradas = estadoController.buscar(estado.getId());
            assertNotNull(estadoEncontradas, "No se debe de obtener un elemento nulo");
            assertEquals(estado, estadoEncontradas, "No se ha encontrado lo esperado");
        } catch (PersistenciaException e) {
            fail("Se ha producido un error en la consulta del estado ,e:" + e.getMessage());
        }
    }

    @Test
    public void modificarEstadoExistenteTest() {
        try {
            estadoController.modificar(estado);
        } catch (PokemonException | PersistenciaException e) {
            fail("No deberia llgar ningun mensaje de error y llega");
        }
    }

    @Test
    public void modificarEstadoInexistenteTest() {
        Estado estadoInexistente = new Estado(5000,"Estado",1,"Nulo");
        try {
            estadoController.modificar(estadoInexistente);
        } catch (PokemonException | PersistenciaException e) {
            assertEquals("El estado indicado no existe", e.getMessage(),
                    "El mensaje recibido no es el esperado");
        }
    }

}
