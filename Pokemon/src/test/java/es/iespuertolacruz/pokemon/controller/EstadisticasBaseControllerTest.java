package es.iespuertolacruz.pokemon.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import es.iespuertolacruz.pokemon.api.EstadisticasBase;
import es.iespuertolacruz.pokemon.excepciones.PersistenciaException;
import es.iespuertolacruz.pokemon.excepciones.PokemonException;

/**
 * Clase controller de EstadisticasBase
 */
public class EstadisticasBaseControllerTest {
    
    // Variables de clase

    static EstadisticasBaseController estadisticasBaseController;
    EstadisticasBase estadisticasBase = null;

    //BeforeEach y AfterEach

    @BeforeAll
    public static void beforeAll() {
        if (estadisticasBaseController == null) {
            try {
                estadisticasBaseController = new EstadisticasBaseController();
            } catch (Exception e) {
                fail("Se ha producido un error iniciando estadisticasBaseController");
            }
        }
    }

    @BeforeEach
    public void crearEstadisticasBase() {
        insertarEstadisticasBaseTest();
    }

    @AfterEach
    public void eliminarEstadisticasBase() {
        if (estadisticasBase != null) {
            eliminarEstadisticasBaseTest();
        }
    }

    // Test

    @Test
    public void insertarEstadisticasBaseTest() {
        estadisticasBase = new EstadisticasBase(1,45,49,49,65,65,45);
        try {
            estadisticasBaseController.insertar(estadisticasBase);
        } catch (PokemonException | PersistenciaException e) {
            assertTrue(e.getMessage().contains(""), "No se recibio el mensaje esperado " + e);
        }
    }

    @Test
    public void validarTest() {
        EstadisticasBase estadisticasBaseInvalido = new EstadisticasBase(0,0,0,0,0,0,0);
        try {
            estadisticasBaseController.validar(estadisticasBaseInvalido);
        } catch (PokemonException e) {
            assertTrue(e.getMessage().contains(
                    ""),
                    "No se recibio el mensaje esperado");
        }
    }

    @Test
    public void validarNuloTest() {
        EstadisticasBase estadisticasBaseInvalido = null;
        try {
            estadisticasBaseController.validar(estadisticasBaseInvalido);
        } catch (PokemonException e) {
            assertTrue(e.getMessage().contains("Se esta validando un objeto nulo"),
                    "No se recibio el mensaje esperado");
        }
    }

    @Test
    public void eliminarEstadisticasBaseTest() {
        try {
            estadisticasBaseController.eliminar(estadisticasBase);
        } catch (PokemonException | PersistenciaException e) {
            assertTrue(e.getMessage().contains("La estadisticasBase indicada no existe"), "No se recibio el mensaje esperado");
        }
    }

    @Test
    public void eliminarPorNombreTest() {
        try {
            estadisticasBaseController.eliminar(1);
        } catch (PokemonException | PersistenciaException e) {
            assertTrue(e.getMessage().contains("La estadisticasBase indicada no existe"), "No se recibio el mensaje esperado");
        }
    }

    @Test
    public void buscarEstadisticasBaseTest() {
        try {
            EstadisticasBase estadisticasBaseEncontrado = estadisticasBaseController.buscar(estadisticasBase.getId());
            assertNotNull(estadisticasBaseEncontrado, "No se debe de obtener un elemento nulo");
            assertEquals(estadisticasBase, estadisticasBaseEncontrado, "No se ha encontrado lo esperado");
        } catch (PersistenciaException e) {
            fail("Se ha producido un error en la consulta del estadisticasBase ,e:" + e.getMessage());
        }
    }

    @Test
    public void modificarEstadisticasBaseExistenteTest() {
        try {
            estadisticasBaseController.modificar(estadisticasBase);
        } catch (PokemonException | PersistenciaException e) {
            fail("No deberia llegar ningun mensaje de error y llega");
        }
    }

    @Test
    public void modificarEstadisticasBaseInexistenteTest() {
        EstadisticasBase estadisticasBaseInexistente = new EstadisticasBase(1000,1000,1000,1000,1000,1000,1000);
        try {
            estadisticasBaseController.modificar(estadisticasBaseInexistente);
        } catch (PokemonException | PersistenciaException e) {
            assertEquals("La estadisticasBase indicada no existe",e.getMessage(), "El mensaje recibido no es el esperado");
        }
    }

}
