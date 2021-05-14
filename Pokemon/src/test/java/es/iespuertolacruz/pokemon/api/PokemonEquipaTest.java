package es.iespuertolacruz.pokemon.api;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PokemonEquipaTest {
    
    // Variables de la clase

    PokemonEquipa pokemonEquipa1;
    PokemonEquipa pokemonEquipa2;

    //BeforeEach y AfterEach

    @BeforeEach
    public void SetUp() {
        pokemonEquipa1 = crearPokemonEquipa();
        pokemonEquipa2 = new PokemonEquipa(143,11);
    }

    //Test

    @Test
    public void toStringTest() {
        assertEquals("143'11",pokemonEquipa1.toString(), "El texto recibido no era el esperado");
    }

    //Funciones y metodos

    private PokemonEquipa crearPokemonEquipa() {
        PokemonEquipa pokemonEquipa;
        pokemonEquipa = new PokemonEquipa();
        pokemonEquipa.setNumeroPokedex(143);
        pokemonEquipa.setIdObjeto(11);
        return pokemonEquipa;
    }
}
