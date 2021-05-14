package es.iespuertolacruz.pokemon.api;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ConoceTest {
    
    // Variables de la clase

    Conoce conoce1;
    Conoce conoce2;

    //BeforeEach y AfterEach

    @BeforeEach
    public void SetUp() {
        conoce1 = crearConoce();
        conoce2 = new Conoce(1,119);
    }

    //Test

    @Test
    public void toStringTest() {
        assertEquals("1'119",conoce1.toString(), "El texto recibido no era el esperado");
    }

    //Funciones y metodos

    private Conoce crearConoce() {
        Conoce conoce;
        conoce = new Conoce();
        conoce.setNumeroPokedex(1);
        conoce.setIdMovimiento(119);
        return conoce;
    }
}
