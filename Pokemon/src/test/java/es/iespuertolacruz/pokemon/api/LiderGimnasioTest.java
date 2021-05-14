package es.iespuertolacruz.pokemon.api;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class LiderGimnasioTest {
    
    // Variables de la clase

    LiderGimnasio liderGimnasio1;
    LiderGimnasio liderGimnasio2;

    //BeforeEach y AfterEach

    @BeforeEach
    public void SetUp() {
        liderGimnasio1 = crearLiderGimnasio();
        liderGimnasio2 = new LiderGimnasio(6,1);
    }

    //Test

    @Test
    public void toStringTest() {
        assertEquals("6'1",liderGimnasio1.toString(), "El texto recibido no era el esperado");
    }

    //Funciones y metodos

    private LiderGimnasio crearLiderGimnasio() {
        LiderGimnasio liderGimnasio;
        liderGimnasio = new LiderGimnasio();
        liderGimnasio.setIdEntrenador(6);
        liderGimnasio.setMedalla(1);
        return liderGimnasio;
    }

}
