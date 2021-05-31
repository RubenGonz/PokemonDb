package es.iespuertolacruz.pokemon.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import es.iespuertolacruz.pokemon.api.Pertenece;
import es.iespuertolacruz.pokemon.excepciones.PersistenciaException;
import es.iespuertolacruz.pokemon.excepciones.PokemonException;

public class PerteneceControllerTest {
    // Variables de clase

    static PerteneceController perteneceController;
    Pertenece pertenece = null;


     @BeforeAll
    public static void beforeAll() {
        if (perteneceController == null) {
            try {
                perteneceController = new PerteneceController();
            } catch (Exception e) {
                fail("Se ha producido un error iniciando perteneceController");
            }
        }
    }

 /** 
    @BeforeEach
    public void crearPertenece() {
        insertarPerteneceTest();
    }

    @AfterEach
    public void eliminarPertenece() {
        if (pertenece != null) {
            eliminarPerteneceTest();
        }
    }

    // Test
    

    @Test
    public void insertarPerteneceTest() {
        pertenece = new Pertenece(1, "");
        try {
            perteneceController.insertar(pertenece);
        } catch (PokemonException | PersistenciaException e) {
            assertTrue(e.getMessage().contains("El pertenece indicado ya existe"), "No se recibio el mensaje esperado");
        }
    }

    @Test
    public void validarTest() {
        Pertenece perteneceInvalido = new Pertenece(1, "");
        try {
            perteneceController.validar(perteneceInvalido);
        } catch (PokemonException e) {
            assertTrue(e.getMessage().contains(""));
        }
    }

    @Test
    public void validarNuloTest() {
        Pertenece perteneceInvalido = null;
        try {
            perteneceController.validar(perteneceInvalido);
        } catch (PokemonException e) {
            assertTrue(e.getMessage().contains("Se esta validando un objeto nulo"),
                    "No se recibio el mensaje esperado");
        }
    }

    @Test
    public void eliminarPerteneceTest() {
        try {
            perteneceController.eliminar(pertenece);
        } catch (PokemonException | PersistenciaException e) {
            assertTrue(e.getMessage().contains("El pertenece indicado no existe"), "No se recibio el mensaje esperado");
        }
    }

    @Test
    public void eliminarPorIdEntrenadorTest() {
        try {
            perteneceController.eliminar(1);
        } catch (PokemonException | PersistenciaException e) {
            assertTrue(e.getMessage().contains("El pertenece indicado no existe"), "No se recibio el mensaje esperado");
        }
    }

    @Test
    public void buscarperteneceTest() {
        try {
          Pertenece perteneceEncontrado = perteneceController.buscar(pertenece.getNumeroPokedex());
           assertNotNull(perteneceEncontrado, "No se debe de obtener un elemento nulo");
            assertEquals(pertenece, perteneceEncontrado, "No se ha encontrado lo esperado");
        } catch (PersistenciaException e) {
            fail("Se ha producido un error en la consulta del pokemon ,e:" + e.getMessage());
        }
    }

    @Test
    public void modificarperteneceExistenteTest() {
        try {
            perteneceController.modificar(pertenece);
        } catch (PokemonException | PersistenciaException e) {
            fail("No deberia llgar ningun mensaje de error y llega");
        }
    }

    @Test
    public void modificarperteneceInexistenteTest() {
        Pertenece perteneceInexistente = new Pertenece(1, "");
        try {
            perteneceController.modificar(perteneceInexistente);
        } catch (PokemonException | PersistenciaException e) {
            assertEquals("El pertenece indicado no existe", e.getMessage(), "El mensaje recibido no es el esperado");
        }
    }
*/
}
