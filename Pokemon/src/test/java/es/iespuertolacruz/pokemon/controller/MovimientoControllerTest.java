package es.iespuertolacruz.pokemon.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import es.iespuertolacruz.pokemon.api.Movimiento;
import es.iespuertolacruz.pokemon.excepciones.PersistenciaException;
import es.iespuertolacruz.pokemon.excepciones.PokemonException;

public class MovimientoControllerTest {
        // Variables de clase

    static MovimientoController movimientoController;
    Movimiento movimiento = null;


     @BeforeAll
    public static void beforeAll() {
        if (movimientoController == null) {
            try {
                movimientoController = new MovimientoController();
            } catch (Exception e) {
                fail("Se ha producido un error iniciando movimientoController");
            }
        }
    }
/*
 
    @BeforeEach
    public void crearMovimiento() {
        insertarMovimientoTest();
    }

    @AfterEach
    public void eliminarMovimiento() {
        if (movimiento != null) {
            eliminarMovimientoTest();
        }
    }

    // Test
    

    @Test
    public void insertarMovimientoTest() {
        movimiento = new Movimiento(1, "", "", "", 1,1 ,1 );
        try {
            movimientoController.insertar(movimiento);
        } catch (PokemonException | PersistenciaException e) {
            assertTrue(e.getMessage().contains("El movimiento indicado ya existe"), "No se recibio el mensaje esperado");
        }
    }

    @Test
    public void validarTest() {
        Movimiento movimientoInvalido = new Movimiento(1, "", "", "", 1, 1, 1);
        try {
            movimientoController.validar(movimientoInvalido);
        } catch (PokemonException e) {
            assertTrue(e.getMessage().contains(""));
        }
    }

    @Test
    public void validarNuloTest() {
        Movimiento movimientoInvalido = null;
        try {
            movimientoController.validar(movimientoInvalido);
        } catch (PokemonException e) {
            assertTrue(e.getMessage().contains("Se esta validando un objeto nulo"),
                    "No se recibio el mensaje esperado");
        }
    }

    @Test
    public void eliminarMovimientoTest() {
        try {
            movimientoController.eliminar(movimiento);
        } catch (PokemonException | PersistenciaException e) {
            assertTrue(e.getMessage().contains("El movimiento indicado no existe"), "No se recibio el mensaje esperado");
        }
    }

    @Test
    public void eliminarPorIdEntrenadorTest() {
        try {
            movimientoController.eliminar(1);
        } catch (PokemonException | PersistenciaException e) {
            assertTrue(e.getMessage().contains("El movimiento indicado no existe"), "No se recibio el mensaje esperado");
        }
    }

    @Test
    public void buscarmovimientoTest() {
        try {
          Movimiento movimientoEncontrado = movimientoController.buscar(movimiento.getId());
           assertNotNull(movimientoEncontrado, "No se debe de obtener un elemento nulo");
            assertEquals(movimiento, movimientoEncontrado, "No se ha encontrado lo esperado");
        } catch (PersistenciaException e) {
            fail("Se ha producido un error en la consulta del pokemon ,e:" + e.getMessage());
        }
    }

    @Test
    public void modificarmovimientoExistenteTest() {
        try {
            movimientoController.modificar(movimiento);
        } catch (PokemonException | PersistenciaException e) {
            fail("No deberia llgar ningun mensaje de error y llega");
        }
    }

    @Test
    public void modificarmovimientoInexistenteTest() {
        Movimiento movimientoInexistente = new Movimiento(1, "", "", "", 1, 1, 1);
        try {
            movimientoController.modificar(movimientoInexistente);
        } catch (PokemonException | PersistenciaException e) {
            assertEquals("El movimiento indicado no existe", e.getMessage(), "El mensaje recibido no es el esperado");
        }
    }
*/
}
