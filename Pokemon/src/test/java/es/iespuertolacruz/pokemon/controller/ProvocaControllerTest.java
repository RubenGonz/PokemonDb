package es.iespuertolacruz.pokemon.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import es.iespuertolacruz.pokemon.api.Provoca;
import es.iespuertolacruz.pokemon.excepciones.PersistenciaException;
import es.iespuertolacruz.pokemon.excepciones.PokemonException;

public class ProvocaControllerTest {
    // Variables de clase

    static ProvocaController provocaController;
    Provoca provoca = null;


     @BeforeAll
    public static void beforeAll() {
        if (provocaController == null) {
            try {
                provocaController = new ProvocaController();
            } catch (Exception e) {
                fail("Se ha producido un error iniciando provocaController");
            }
        }
    }

 /**
    @BeforeEach
    public void crearProvoca() {
        insertarProvocaTest();
    }

    @AfterEach
    public void eliminarProvoca() {
        if (provoca != null) {
            eliminarProvocaTest();
        }
    }

    // Test
    

    @Test
    public void insertarProvocaTest() {
        provoca = new Provoca(1, 1);
        try {
            provocaController.insertar(provoca);
        } catch (PokemonException | PersistenciaException e) {
            assertTrue(e.getMessage().contains("El provoca indicado ya existe"), "No se recibio el mensaje esperado");
        }
    }

    @Test
    public void validarTest() {
        Provoca provocaInvalido = new Provoca(1, 1);
        try {
            provocaController.validar(provocaInvalido);
        } catch (PokemonException e) {
            assertTrue(e.getMessage().contains(""));
        }
    }

    @Test
    public void validarNuloTest() {
        Provoca provocaInvalido = null;
        try {
            provocaController.validar(provocaInvalido);
        } catch (PokemonException e) {
            assertTrue(e.getMessage().contains("Se esta validando un objeto nulo"),
                    "No se recibio el mensaje esperado");
        }
    }

    @Test
    public void eliminarProvocaTest() {
        try {
            provocaController.eliminar(provoca);
        } catch (PokemonException | PersistenciaException e) {
            assertTrue(e.getMessage().contains("El provoca indicado no existe"), "No se recibio el mensaje esperado");
        }
    }

    @Test
    public void eliminarPorIdEntrenadorTest() {
        try {
            provocaController.eliminar(1 ,2);
        } catch (PokemonException | PersistenciaException e) {
            assertTrue(e.getMessage().contains("El provoca indicado no existe"), "No se recibio el mensaje esperado");
        }
    }

    @Test
    public void buscarprovocaTest() {
        try {
          Provoca provocaEncontrado = provocaController.buscar(provoca.getIdEstado() , provoca.getIdMovimiento());
           assertNotNull(provocaEncontrado, "No se debe de obtener un elemento nulo");
            assertEquals(provoca, provocaEncontrado, "No se ha encontrado lo esperado");
        } catch (PersistenciaException e) {
            fail("Se ha producido un error en la consulta del pokemon ,e:" + e.getMessage());
        }
    }

    @Test
    public void modificarprovocaExistenteTest() {
        try {
            provocaController.modificar(provoca);
        } catch (PokemonException | PersistenciaException e) {
            fail("No deberia llgar ningun mensaje de error y llega");
        }
    }

    @Test
    public void modificarprovocaInexistenteTest() {
        Provoca provocaInexistente = new Provoca(1, 1);
        try {
            provocaController.modificar(provocaInexistente);
        } catch (PokemonException | PersistenciaException e) {
            assertEquals("El provoca indicado no existe", e.getMessage(), "El mensaje recibido no es el esperado");
        }
    }
*/
}
