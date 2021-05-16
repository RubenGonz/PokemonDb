package es.iespuertolacruz.pokemon.api;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class EvolucionaTest {
    
    //Variables de clase

    Evoluciona evoluciona1;
    Evoluciona evoluciona2;
    Evoluciona evoluciona3;

    @BeforeEach
    public void SetUp() {
        evoluciona1 = crearEvolucion();
        evoluciona2 = new Evoluciona(1,2,"Nivel");
        evoluciona3 = new Evoluciona("1'2'Nivel");
    }

    //Test

    @Test
    public void toStringTest() {
        assertEquals("1'2'Nivel",evoluciona1.toString(), "El texto recibido no era el esperado");
        assertEquals("1'2'Nivel",evoluciona2.toString(), "El texto recibido no era el esperado");
        assertEquals("1'2'Nivel",evoluciona3.toString(), "El texto recibido no era el esperado");
    }

    //Funciones y metodos

    private Evoluciona crearEvolucion() {
        Evoluciona evoluciona;
        evoluciona = new Evoluciona();
        evoluciona.setNumeroPokedexOrigen(1);
        evoluciona.setNumeroPokedexDestino(2);
        evoluciona.setModoEvoluciona("Nivel");
        return evoluciona;
    }
}
