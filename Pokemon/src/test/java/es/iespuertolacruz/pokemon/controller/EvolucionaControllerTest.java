package es.iespuertolacruz.pokemon.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import es.iespuertolacruz.pokemon.api.Evoluciona;
import es.iespuertolacruz.pokemon.excepciones.PersistenciaException;
import es.iespuertolacruz.pokemon.excepciones.PokemonException;

public class EvolucionaControllerTest {
    // Variables de clase

    static EvolucionaController evolucionaController;
    Evoluciona evoluciona = null;


     @BeforeAll
    public static void beforeAll() {
        if (evolucionaController == null) {
            try {
                evolucionaController = new EvolucionaController();
            } catch (Exception e) {
                fail("Se ha producido un error iniciando evolucionaController");
            }
        }
    }
/*
 
    @BeforeEach
    public void crearEvoluciona() {
        insertarEvolucionaTest();
    }

    @AfterEach
    public void eliminarEvoluciona() {
        if (evoluciona != null) {
            eliminarEvolucionaTest();
        }
    }

    // Test
    

    @Test
    public void insertarEvolucionaTest() {
        evoluciona = new Evoluciona(1, 2, "");
        try {
            evolucionaController.insertar(evoluciona);
        } catch (PokemonException | PersistenciaException e) {
            assertTrue(e.getMessage().contains("La evoluciona indicado ya existe"), "No se recibio el mensaje esperado");
        }
    }

    @Test
    public void validarTest() {
        Evoluciona evolucionaInvalido = new Evoluciona(1, 2, "");
        try {
            evolucionaController.validar(evolucionaInvalido);
        } catch (PokemonException e) {
            assertTrue(e.getMessage().contains(""));
        }
    }

    @Test
    public void validarNuloTest() {
        Evoluciona evolucionaInvalido = null;
        try {
            evolucionaController.validar(evolucionaInvalido);
        } catch (PokemonException e) {
            assertTrue(e.getMessage().contains("Se esta validando un objeto nulo"),
                    "No se recibio el mensaje esperado");
        }
    }

    @Test
    public void eliminarEvolucionaTest() {
        try {
            evolucionaController.eliminar(evoluciona);
        } catch (PokemonException | PersistenciaException e) {
            assertTrue(e.getMessage().contains("El evoluciona indicado no existe"), "No se recibio el mensaje esperado");
        }
    }

    @Test
    public void eliminarPorIdEntrenadorTest() {
        try {
            evolucionaController.eliminar(1 ,2);
        } catch (PokemonException | PersistenciaException e) {
            assertTrue(e.getMessage().contains("El evoluciona indicado no existe"), "No se recibio el mensaje esperado");
        }
    }

    @Test
    public void buscarevolucionaTest() {
        try {
          Evoluciona evolucionaEncontrado = evolucionaController.buscar(evoluciona.getNumeroPokedexOrigen() , evoluciona.getNumeroPokedexDestino());
           assertNotNull(evolucionaEncontrado, "No se debe de obtener un elemento nulo");
            assertEquals(evoluciona, evolucionaEncontrado, "No se ha encontrado lo esperado");
        } catch (PersistenciaException e) {
            fail("Se ha producido un error en la consulta del pokemon ,e:" + e.getMessage());
        }
    }

    @Test
    public void modificarevolucionaExistenteTest() {
        try {
            evolucionaController.modificar(evoluciona);
        } catch (PokemonException | PersistenciaException e) {
            fail("No deberia llgar ningun mensaje de error y llega");
        }
    }

    @Test
    public void modificarevolucionaInexistenteTest() {
        Evoluciona evolucionaInexistente = new Evoluciona(1, 1 , "");
        try {
            evolucionaController.modificar(evolucionaInexistente);
        } catch (PokemonException | PersistenciaException e) {
            assertEquals("El evoluciona indicado no existe", e.getMessage(), "El mensaje recibido no es el esperado");
        }
    }
*/
}
