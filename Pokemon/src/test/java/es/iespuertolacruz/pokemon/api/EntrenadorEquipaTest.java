package es.iespuertolacruz.pokemon.api;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class EntrenadorEquipaTest {
    
    // Variables de la clase

    EntrenadorEquipa entrenadorEquipa1;
    EntrenadorEquipa entrenadorEquipa2;
    EntrenadorEquipa entrenadorEquipa3;

    //BeforeEach y AfterEach

    @BeforeEach
    public void SetUp() {
        entrenadorEquipa1 = crearEntrenadorEquipa();
        entrenadorEquipa2 = new EntrenadorEquipa(1,1,1);
        entrenadorEquipa3 = new EntrenadorEquipa("1'1'1");
    }

    //Test

    @Test
    public void toStringTest() {
        assertEquals("1'1'1",entrenadorEquipa1.toString(), "El texto recibido no era el esperado");
        assertEquals("1'1'1",entrenadorEquipa2.toString(), "El texto recibido no era el esperado");
        assertEquals("1'1'1",entrenadorEquipa3.toString(), "El texto recibido no era el esperado");
    }

    //Funciones y metodos

    private EntrenadorEquipa crearEntrenadorEquipa() {
        EntrenadorEquipa entrenadorEquipa;
        entrenadorEquipa = new EntrenadorEquipa();
        entrenadorEquipa.setIdEntrenador(1);
        entrenadorEquipa.setIdObjeto(1);
        entrenadorEquipa.setCantidad(1);
        return entrenadorEquipa;
    }
}
