package es.iespuertolacruz.pokemon.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import es.iespuertolacruz.pokemon.api.Pokemon;
import es.iespuertolacruz.pokemon.excepciones.PersistenciaException;
import es.iespuertolacruz.pokemon.excepciones.PokemonException;

/**
 * Clase controller de Pokemon
 */
public class PokemonControllerTest {

    // Variables de clase

    static PokemonController pokemonController;
    Pokemon pokemon = null;

    // BeforeEach y AfterEach

    @BeforeAll
    public static void beforeAll() {
        if (pokemonController == null) {
            try {
                pokemonController = new PokemonController();
            } catch (Exception e) {
                fail("Se ha producido un error iniciando pokemonController");
            }
        }
    }

    @BeforeEach
    public void crearPokemon() {
        insertarPokemonTest();
    }

    @AfterEach
    public void eliminarPokemon() {
        if (pokemon != null) {
            eliminarPokemonTest();
        }
    }

    // Test

    @Test
    public void insertarPokemonTest() {
        pokemon = new Pokemon(1, "Bulbasaur", 1, 1);
        try {
            pokemonController.insertar(pokemon);
        } catch (PokemonException | PersistenciaException e) {
            assertTrue(e.getMessage().contains("El pokemon indicado ya existe"), "No se recibio el mensaje esperado");
        }
    }

    @Test
    public void validarTest() {
        Pokemon pokemonInvalido = new Pokemon(0, "", 0, 0);
        try {
            pokemonController.validar(pokemonInvalido);
        } catch (PokemonException e) {
            assertTrue(e.getMessage().contains(""));
        }
    }

    @Test
    public void validarNuloTest() {
        Pokemon pokemonInvalido = null;
        try {
            pokemonController.validar(pokemonInvalido);
        } catch (PokemonException e) {
            assertTrue(e.getMessage().contains("Se esta validando un objeto nulo"),
                    "No se recibio el mensaje esperado");
        }
    }

    @Test
    public void eliminarPokemonTest() {
        try {
            pokemonController.eliminar(pokemon);
        } catch (PokemonException | PersistenciaException e) {
            assertTrue(e.getMessage().contains("El pokemon indicado no existe"), "No se recibio el mensaje esperado");
        }
    }

    @Test
    public void eliminarPorNumeroPokedexTest() {
        try {
            pokemonController.eliminar(1);
        } catch (PokemonException | PersistenciaException e) {
            assertTrue(e.getMessage().contains("El pokemon indicado no existe"), "No se recibio el mensaje esperado");
        }
    }

    @Test
    public void buscarPokemonTest() {
        try {
            Pokemon pokemonEncontrado = pokemonController.buscar(pokemon.getNumeroPokedex());
            assertNotNull(pokemonEncontrado, "No se debe de obtener un elemento nulo");
            assertEquals(pokemon, pokemonEncontrado, "No se ha encontrado lo esperado");
        } catch (PersistenciaException e) {
            fail("Se ha producido un error en la consulta del pokemon ,e:" + e.getMessage());
        }
    }

    @Test
    public void modificarPokemonExistenteTest() {
        try {
            pokemonController.modificar(pokemon);
        } catch (PokemonException | PersistenciaException e) {
            fail("No deberia llgar ningun mensaje de error y llega");
        }
    }

    @Test
    public void modificarPokemonInexistenteTest() {
        Pokemon pokemonInexistente = new Pokemon(8000, "Pokemon Inexistente", 8000, 8000);
        try {
            pokemonController.modificar(pokemonInexistente);
        } catch (PokemonException | PersistenciaException e) {
            assertEquals("El pokemon indicado no existe", e.getMessage(), "El mensaje recibido no es el esperado");
        }
    }

}
