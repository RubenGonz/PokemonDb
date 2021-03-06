package es.iespuertolacruz.pokemon.api;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Clase que testea la api de PokemonEquipa
 */
public class PokemonEquipaTest {

    // Variables de la clase

    PokemonEquipa pokemonEquipa1;
    PokemonEquipa pokemonEquipa2;
    PokemonEquipa pokemonEquipa3;

    // BeforeEach y AfterEach

    @BeforeEach
    public void SetUp() {
        pokemonEquipa1 = crearPokemonEquipa();
        pokemonEquipa2 = new PokemonEquipa(143, 11);
        pokemonEquipa3 = new PokemonEquipa("143'11");
    }

    // Test

    @Test
    public void toStringTest() {
        assertEquals("143'11", pokemonEquipa1.toString(), "El texto recibido no era el esperado");
    }

    @Test
    public void EqualsTest() {
        assertEquals(pokemonEquipa2, pokemonEquipa3, "Los objetos son iguales y equals no lo reconoce");
    }

    // Funciones y metodos

    private PokemonEquipa crearPokemonEquipa() {
        PokemonEquipa pokemonEquipa;
        pokemonEquipa = new PokemonEquipa();
        pokemonEquipa.setNumeroPokedex(143);
        pokemonEquipa.setIdObjeto(11);
        return pokemonEquipa;
    }
}
