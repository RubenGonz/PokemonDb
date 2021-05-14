package es.iespuertolacruz.pokemon.api;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class VillanoTest {

    // Variables de la clase

    Villano villano1;
    Villano villano2;

    //BeforeEach y AfterEach

    @BeforeEach
    public void SetUp() {
        villano1 = crearVillano();
        villano2 = new Villano(1,"Dominar el universo usando a todos los Pokemon");
    }

    //Test

    @Test
    public void toStringTest() {
        assertEquals("13'Dominar el universo usando a todos los Pokemon",villano1.toString(), "El texto recibido no era el esperado");
    }

    //Funciones y metodos

    private Villano crearVillano() {
        Villano villano;
        villano = new Villano();
        villano.setIdEntrenador(13);
        villano.setProposito("Dominar el universo usando a todos los Pokemon");
        return villano;
    }
}
