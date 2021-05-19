package es.iespuertolacruz.pokemon.api;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PokemonTest {
    
    // Variables de la clase

    Pokemon pokemon1;
    Pokemon pokemon2;
    Pokemon pokemon3;

    //BeforeEach y AfterEach

    @BeforeEach
    public void SetUp() {
        pokemon1 = crearPokemon(); 
        pokemon2 = new Pokemon(1,"Bulbasaur",1,1);
        pokemon3 = new Pokemon("1'Bulbasaur'1'1");
    }
    
    //Test

    @Test
    public void toStringTest() {
        assertEquals("1'Bulbasaur'1'1",pokemon1.toString(), "El texto recibido no era el esperado");
    }

    @Test
    public void EqualsTest() {
        assertEquals(pokemon2,pokemon3,"Los objetos son iguales y equals no lo reconoce");
    }

    //Funciones y metodos

    private Pokemon crearPokemon() {
        Pokemon pokemon;
        pokemon = new Pokemon();
        pokemon.setNumeroPokedex(1);
        pokemon.setNombre("Bulbasaur");
        pokemon.setCaracteristicas(1);
        pokemon.setEstadisticasBase(1);
        return pokemon;
    }
}
