package es.iespuertolacruz.pokemon.api;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PokeballTest {
    
    // Variables de la clase

    Pokeball pokeball1;
    Pokeball pokeball2;
    Pokeball pokeball3;

    //BeforeEach y AfterEach

    @BeforeEach
    public void SetUp() {
        pokeball1 = crearPokeball();
        pokeball2 = new Pokeball(12,1.0f);
        pokeball3 = new Pokeball("12'1");
    }

    //Test

    @Test
    public void toStringTest() {
        assertEquals("12'1.0",pokeball1.toString(), "El texto recibido no era el esperado");
    }

    @Test
    public void EqualsTest() {
        assertEquals(pokeball2,pokeball3,"Los objetos son iguales y equals no lo reconoce");
    }

    //Funciones y metodos

    private Pokeball crearPokeball() {
        Pokeball pokeball;
        pokeball = new Pokeball();
        pokeball.setIdObjeto(12);
        pokeball.setRatio(1.0f);
        return pokeball;
    }
}
