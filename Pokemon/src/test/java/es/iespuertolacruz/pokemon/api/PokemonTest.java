package es.iespuertolacruz.pokemon.api;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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

    @Test
    public void toStringConNullTest() {
        Pokemon pokemon;
        pokemon = new Pokemon();
        pokemon.setNumeroPokedex(001);
        pokemon.setNombre("Magikar");
        pokemon.setTipoPrincipal("Agua");
        pokemon.setCaracteristicas(1);
        pokemon.setEstadisticasBase(1);
        assertEquals("1'Magikar'Agua'null'1'1",pokemon.toString(), "El texto recibido no era el esperado");
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
