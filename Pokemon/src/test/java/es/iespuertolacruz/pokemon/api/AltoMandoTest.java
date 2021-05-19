package es.iespuertolacruz.pokemon.api;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class AltoMandoTest {
    
    // Variables de la clase

    AltoMando altoMando1;
    AltoMando altoMando2;
    AltoMando altoMando3;

    //BeforeEach y AfterEach

    @BeforeEach
    public void SetUp() {
        altoMando1 = crearAltoMando();
        altoMando2 = new AltoMando(2,"Hielo");
        altoMando3 = new AltoMando("2'Hielo");
    }

    //Test

    @Test
    public void toStringTest() {
        assertEquals("2'Hielo",altoMando1.toString(), "El texto recibido no era el esperado");
    }

    @Test
    public void EqualsTest() {
        assertEquals(altoMando2,altoMando3,"Los objetos son iguales y equals no lo reconoce");
    }

    //Funciones y metodos

    private AltoMando crearAltoMando() {
        AltoMando altoMando;
        altoMando = new AltoMando();
        altoMando.setIdEntrenador(2);
        altoMando.setTipoPrincipal("Hielo");
        return altoMando;
    }
}
