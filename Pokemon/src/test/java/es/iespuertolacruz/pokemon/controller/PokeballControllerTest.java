package es.iespuertolacruz.pokemon.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import es.iespuertolacruz.pokemon.api.Pokeball;
import es.iespuertolacruz.pokemon.excepciones.PersistenciaException;
import es.iespuertolacruz.pokemon.excepciones.PokemonException;

public class PokeballControllerTest {
    // Variables de clase

    static PokeballController pokeballController;
    Pokeball pokeball = null;


     @BeforeAll
    public static void beforeAll() {
        if (pokeballController == null) {
            try {
                pokeballController = new PokeballController();
            } catch (Exception e) {
                fail("Se ha producido un error iniciando pokeballController");
            }
        }
    }

 /** 
    @BeforeEach
    public void crearPokeball() {
        insertarPokeballTest();
    }

    @AfterEach
    public void eliminarPokeball() {
        if (pokeball != null) {
            eliminarPokeballTest();
        }
    }

    // Test
    

    @Test
    public void insertarPokeballTest() {
        pokeball = new Pokeball(1, 1);
        try {
            pokeballController.insertar(pokeball);
        } catch (PokemonException | PersistenciaException e) {
            assertTrue(e.getMessage().contains("El pokeball indicado ya existe"), "No se recibio el mensaje esperado");
        }
    }

    @Test
    public void validarTest() {
        Pokeball pokeballInvalido = new Pokeball(1, 1);
        try {
            pokeballController.validar(pokeballInvalido);
        } catch (PokemonException e) {
            assertTrue(e.getMessage().contains(""));
        }
    }

    @Test
    public void validarNuloTest() {
        pokeball pokeballInvalido = null;
        try {
            pokeballController.validar(pokeballInvalido);
        } catch (PokemonException e) {
            assertTrue(e.getMessage().contains("Se esta validando un objeto nulo"),
                    "No se recibio el mensaje esperado");
        }
    }

    @Test
    public void eliminarPokeballTest() {
        try {
            pokeballController.eliminar(pokeball);
        } catch (PokemonException | PersistenciaException e) {
            assertTrue(e.getMessage().contains("El pokeball indicado no existe"), "No se recibio el mensaje esperado");
        }
    }

    @Test
    public void eliminarPorIdEntrenadorTest() {
        try {
            pokeballController.eliminar(1);
        } catch (PokemonException | PersistenciaException e) {
            assertTrue(e.getMessage().contains("El pokeball indicado no existe"), "No se recibio el mensaje esperado");
        }
    }

    @Test
    public void buscarpokeballTest() {
        try {
          Pokeball pokeballEncontrado = pokeballController.buscar(pokeball.getIdObjeto());
           assertNotNull(pokeballEncontrado, "No se debe de obtener un elemento nulo");
            assertEquals(pokeball, pokeballEncontrado, "No se ha encontrado lo esperado");
        } catch (PersistenciaException e) {
            fail("Se ha producido un error en la consulta del pokemon ,e:" + e.getMessage());
        }
    }

    @Test
    public void modificarpokeballExistenteTest() {
        try {
            pokeballController.modificar(pokeball);
        } catch (PokemonException | PersistenciaException e) {
            fail("No deberia llgar ningun mensaje de error y llega");
        }
    }

    @Test
    public void modificarpokeballInexistenteTest() {
        Pokeball pokeballInexistente = new Pokeball(1, 1);
        try {
            pokeballController.modificar(pokeballInexistente);
        } catch (PokemonException | PersistenciaException e) {
            assertEquals("El pokeball indicado no existe", e.getMessage(), "El mensaje recibido no es el esperado");
        }
    }
*/
}
