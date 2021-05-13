package es.iespuertolacruz.pokemon;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import es.iespuertolacruz.pokemon.api.Pokemon;

public class PokemonTest {
    
    // Variables de la clase

    Pokemon pokemon1;
    Pokemon pokemon2;

    //BeforeEach y AfterEach

    @BeforeEach
    public void SetUp() {
        pokemon1 = crearPokemon(); 
        pokemon2 = new Pokemon(1, "Bulbasaur", "Planta", "Veneno", 1, 1);
    }
    
    //Test

    @Test
    public void toStringTest() {
        assertEquals("1'Bulbasaur'Planta'Veneno'1'1",pokemon1.toString(), "El texto recibido no era el esperado");
    }
 
    //Funciones y metodos

    private Pokemon crearPokemon() {
        Pokemon pokemon;
        pokemon = new Pokemon();
        pokemon.setNumeroPokedex(001);
        pokemon.setNombre("Bulbasaur");
        pokemon.setTipoPrincipal("Planta");
        pokemon.setTipoSecundario("Veneno");
        pokemon.setCaracteristicas(1);
        pokemon.setEstadisticasBase(1);
        return pokemon;
    }
}
