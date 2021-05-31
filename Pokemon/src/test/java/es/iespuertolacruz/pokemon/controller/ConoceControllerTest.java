package es.iespuertolacruz.pokemon.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import es.iespuertolacruz.pokemon.api.Conoce;
import es.iespuertolacruz.pokemon.excepciones.PersistenciaException;
import es.iespuertolacruz.pokemon.excepciones.PokemonException;

public class ConoceControllerTest {

    // Variables de clase
   static ConoceController conoceController;
    Conoce conoce;

    @BeforeAll
    public static void beforeAll() {
        if (conoceController == null) {
            try {
                conoceController = new ConoceController();
            } catch (Exception e) {
                fail("Se ha producido un error iniciando conoceController");
            }
        }
    }
  /*  
    @BeforeEach
    public void crearConoce() {
        insertarConoceTest();
    }

    @AfterEach
    public void eliminarConoce() {
        if (conoce!= null) {
            eliminarConoceTest();
        }
    }

// Test
 
    @Test
    public void insertarConoceTest() {
        conoce = new Conoce(1, 1);
        try {
            conoceController.insertar(conoce);
        } catch (PokemonException | PersistenciaException e) {
            assertTrue(e.getMessage().contains("El pokemon indicado ya existe"), "No se recibio el mensaje esperado");
        }
    }

    @Test
    public void validarTest() {
        Conoce conoceInvalido = new Conoce(1, 1);
        try {
            conoceController.validar(conoceInvalido);
        } catch (PokemonException e) {
            assertTrue(e.getMessage().contains(""));
        }
    }

    @Test
    public void validarNuloTest() {
        Conoce conoceInvalido = null;
        try {
            conoceController.validar(conoceInvalido);
        } catch (PokemonException e) {
            assertTrue(e.getMessage().contains("Se esta validando un objeto nulo"),
                    "No se recibio el mensaje esperado");
        }
    }

    @Test
    public void eliminarConoceTest() {
        try {
            conoceController.eliminar(conoce);
        } catch (PokemonException | PersistenciaException e) {
            assertTrue(e.getMessage().contains("El conoce indicado no existe"), "No se recibio el mensaje esperado");
        }
    }

    @Test
    public void eliminarPorIdEntrenadorTest() {
        try {
            conoceController.eliminar(1);
        } catch (PokemonException | PersistenciaException e) {
            assertTrue(e.getMessage().contains("El conoce indicado no existe"), "No se recibio el mensaje esperado");
        }
    }

    @Test
    public void buscarconoceTest() {
        try {
            Conoce conoceEncontrado = conoceController.buscar( conoce.getNumeroPokedex());
           assertNotNull(conoceEncontrado, "No se debe de obtener un elemento nulo");
            assertEquals(conoce, conoceEncontrado, "No se ha encontrado lo esperado");
        } catch (PersistenciaException e) {
            fail("Se ha producido un error en la consulta del pokemon ,e:" + e.getMessage());
        }
    }

    @Test
    public void modificarconoceExistenteTest() {
        try {
            conoceController.modificar(conoce);
        } catch (PokemonException | PersistenciaException e) {
            fail("No deberia llgar ningun mensaje de error y llega");
        }
    }

    @Test
    public void modificarconoceInexistenteTest() {
        Conoce conoceInexistente = new Conoce(1, 1);
        try {
            conoceController.modificar(conoceInexistente);
        } catch (PokemonException | PersistenciaException e) {
            assertEquals("El conoce indicado no existe", e.getMessage(), "El mensaje recibido no es el esperado");
        }
    }
    */
}
