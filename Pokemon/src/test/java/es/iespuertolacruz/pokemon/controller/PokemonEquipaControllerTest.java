package es.iespuertolacruz.pokemon.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import es.iespuertolacruz.pokemon.api.PokemonEquipa;
import es.iespuertolacruz.pokemon.excepciones.PersistenciaException;
import es.iespuertolacruz.pokemon.excepciones.PokemonException;

public class PokemonEquipaControllerTest {

    // Variables de clase

    static PokemonEquipaController pokemonEquipaController;
    PokemonEquipa pokemonEquipa = null;

    @BeforeAll
    public static void beforeAll() {
        if (pokemonEquipaController == null) {
            try {
                pokemonEquipaController = new PokemonEquipaController();
            } catch (Exception e) {
                fail("Se ha producido un error iniciando pokemonEquipaController");
            }
        }
    }

    @BeforeEach
    public void crearPokemonEquipa() {
        insertarPokemonEquipaTest();
    }

    @AfterEach
    public void eliminarPokemonEquipa() {
        if (pokemonEquipa != null) {
            eliminarPokemonEquipaTest();
        }
    }

    // Test

    @Test
    public void insertarPokemonEquipaTest() {
        pokemonEquipa = new PokemonEquipa(1, 1);
        try {
            pokemonEquipaController.insertar(pokemonEquipa);
        } catch (PokemonException | PersistenciaException e) {
            assertTrue(e.getMessage().contains("El pokemonEquipa indicado ya existe"),
                    "No se recibio el mensaje esperado");
        }
    }

    @Test
    public void validarTest() {
        PokemonEquipa pokemonEquipaInvalido = new PokemonEquipa(0, 0);
        try {
            pokemonEquipaController.validar(pokemonEquipaInvalido);
        } catch (PokemonException e) {
            assertTrue(e.getMessage().contains(""));
        }
    }

    @Test
    public void validarNuloTest() {
        PokemonEquipa pokemonEquipaInvalido = null;
        try {
            pokemonEquipaController.validar(pokemonEquipaInvalido);
        } catch (PokemonException e) {
            assertTrue(e.getMessage().contains("Se esta validando un objeto nulo"),
                    "No se recibio el mensaje esperado");
        }
    }

    @Test
    public void eliminarPokemonEquipaTest() {
        try {
            pokemonEquipaController.eliminar(pokemonEquipa);
        } catch (PokemonException | PersistenciaException e) {
            assertTrue(e.getMessage().contains("El pokemonEquipa indicado no existe"),
                    "No se recibio el mensaje esperado");
        }
    }

    @Test
    public void eliminarPorIdEntrenadorTest() {
        try {
            pokemonEquipaController.eliminar(1, 1);
        } catch (PokemonException | PersistenciaException e) {
            assertTrue(e.getMessage().contains("El pokemonEquipa indicado no existe"),
                    "No se recibio el mensaje esperado");
        }
    }

    @Test
    public void buscarpokemonEquipaTest() {
        try {
            PokemonEquipa pokemonEquipaEncontrado = pokemonEquipaController.buscar(pokemonEquipa.getIdObjeto(),
                    pokemonEquipa.getNumeroPokedex());
            assertNotNull(pokemonEquipaEncontrado, "No se debe de obtener un elemento nulo");
            assertEquals(pokemonEquipa, pokemonEquipaEncontrado, "No se ha encontrado lo esperado");
        } catch (PersistenciaException e) {
            fail("Se ha producido un error en la consulta del pokemon ,e:" + e.getMessage());
        }
    }

    @Test
    public void modificarpokemonEquipaExistenteTest() {
        try {
            pokemonEquipaController.modificar(pokemonEquipa);
        } catch (PokemonException | PersistenciaException e) {
            fail("No deberia llgar ningun mensaje de error y llega");
        }
    }

    @Test
    public void modificarpokemonEquipaInexistenteTest() {
        PokemonEquipa pokemonEquipaInexistente = new PokemonEquipa(5000, 5000);
        try {
            pokemonEquipaController.modificar(pokemonEquipaInexistente);
        } catch (PokemonException | PersistenciaException e) {
            assertEquals("El pokemonEquipa indicado no existe", e.getMessage(),
                    "El mensaje recibido no es el esperado");
        }
    }

}
