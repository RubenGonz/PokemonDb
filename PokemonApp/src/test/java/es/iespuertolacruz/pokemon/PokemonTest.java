package es.iespuertolacruz.pokemon;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import es.iespuertolacruz.pokemon.api.Caracteristicas;
import es.iespuertolacruz.pokemon.api.EstadisticasBase;
import es.iespuertolacruz.pokemon.api.Pokemon;

public class PokemonTest {
    
    // Variables de la clase

    Pokemon pokemon1;
    Pokemon pokemon2;

    //BeforeEach y AfterEach

    @BeforeEach
    public void SetUp() {
        pokemon1 = crearPokemon(); 
        pokemon2 = new Pokemon(001, "Bulbasaur", "Planta", "Veneno", crearCaracteristicas(), crearEstadisticasBase());
    }
    
    //Test

    @Test
    public void equalsTest() {
        assertTrue(pokemon1.equals(pokemon2), "Los pokemons son iguales pero el equals dice lo contrario");
    }

    @Test
    public void toStringTest() {
        assertTrue(pokemon1.toString().contains("Bulbasaur"), "El texto recibido no era el esperado");
    }
 
    //Funciones y metodos

    private Pokemon crearPokemon() {
        Pokemon pokemon;
        pokemon = new Pokemon();
        pokemon.setNumeroPokedex(001);
        pokemon.setNombre("Bulbasaur");
        pokemon.setTipoPrincipal("Planta");
        pokemon.setTipoSecundario("Veneno");
        pokemon.setCaracteristicas(crearCaracteristicas());
        pokemon.setEstadisticasBase(crearEstadisticasBase());
        return pokemon;
    }

    private Caracteristicas crearCaracteristicas() {
        Caracteristicas caracteristicas;
        caracteristicas = new Caracteristicas();
        caracteristicas.setPeso(6.9f);
        caracteristicas.setAltura(0.7f);
        caracteristicas.setEspecie("Semilla");
        caracteristicas.setHabilidad("Espesura");
        caracteristicas.setColor("Verde");
        caracteristicas.setCategoria("Starter");
        return caracteristicas;
    }

    private EstadisticasBase crearEstadisticasBase() {
        EstadisticasBase estadisticasBase;
        estadisticasBase = new EstadisticasBase();
        estadisticasBase.setPsBase(45);
        estadisticasBase.setAtaqueBase(49);
        estadisticasBase.setDefensaBase(49);
        estadisticasBase.setAtaqueEspecialBase(65);
        estadisticasBase.setDefensaEspecialBase(65);
        estadisticasBase.setVelocidadBase(45);
        return estadisticasBase;
    }
}
