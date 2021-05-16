package es.iespuertolacruz.pokemon.api;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class EntrenadorTest {
    
    // Variables de la clase

    Entrenador entrenador1;
    Entrenador entrenador2;
    Entrenador entrenador3;

    //BeforeEach y AfterEach

    @BeforeEach
    public void SetUp() {
        entrenador1 = crearEntrenador();
        entrenador2 = new Entrenador(1,"Azul");
        entrenador3 = new Entrenador("1'Azul");
    }

    //Test

    @Test
    public void toStringTest() {
        assertEquals("1'Azul",entrenador1.toString(), "El texto recibido no era el esperado");
        assertEquals("1'Azul",entrenador2.toString(), "El texto recibido no era el esperado");
        assertEquals("1'Azul",entrenador3.toString(), "El texto recibido no era el esperado");
    }

    //Funciones y metodos

    private Entrenador crearEntrenador() {
        Entrenador entrenador;
        entrenador = new Entrenador();
        entrenador.setId(1);
        entrenador.setNombre("Azul");
        return entrenador;
    }
}
