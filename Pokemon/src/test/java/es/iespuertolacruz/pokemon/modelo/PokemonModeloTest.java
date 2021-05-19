package es.iespuertolacruz.pokemon.modelo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import es.iespuertolacruz.pokemon.api.Pokemon;
import es.iespuertolacruz.pokemon.excepciones.PersistenciaException;

public class PokemonModeloTest {

    static PokemonModelo pokemonModelo;
    Pokemon pokemon = null;

    @BeforeAll 
    public static void beforeAll() {
        if (pokemonModelo == null) {
            try {
                pokemonModelo = new PokemonModelo();
            } catch (Exception e) {
                fail("Se ha producido un error en el indicio de la BBDD");
            }
        }
    }

    @BeforeEach
    public void crearCuenta() {
        pokemon = new Pokemon(1,"Bulbasaur",1,1);
        try {
            pokemonModelo.insertar(pokemon);
        } catch (PersistenciaException e) {
            fail("Se ha producido un error insertando el pokemon");
        }
    }

    @AfterEach
    public void eliminarCuenta() {
        if (pokemon != null) {
            try {
                pokemonModelo.eliminar(pokemon);
            } catch (PersistenciaException e) {
                fail("Se ha producido un error eliminando el pokemon");
            }
        }
    }

    @Test
    public void buscarPokemonTest(){
        try {
            Pokemon pokemonEncontrado = pokemonModelo.buscar(pokemon.getNumeroPokedex());
            assertNotNull(pokemonEncontrado, "No se debe de obtener un elemento nulo");
            assertEquals(pokemon, pokemonEncontrado, "No se ha encontrado lo esperado");
        } catch (PersistenciaException e) {
           fail("Se ha producido un error en la consulta del pokemon,e:"+e.getMessage());
        }
    }
}
