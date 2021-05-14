package es.iespuertolacruz.pokemon.api;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class EntrenadorCasualTest {
    
    // Variables de la clase

    EntrenadorCasual entrenadorCasual1;
    EntrenadorCasual entrenadorCasual2;

    //BeforeEach y AfterEach

    @BeforeEach
    public void SetUp() {
        entrenadorCasual1 = crearEntrenadorCasual();
        entrenadorCasual2 = new EntrenadorCasual(16,8);
    }

    //Test

    @Test
    public void toStringTest() {
        assertEquals("16'8",entrenadorCasual1.toString(), "El texto recibido no era el esperado");
    }

    //Funciones y metodos

    private EntrenadorCasual crearEntrenadorCasual() {
        EntrenadorCasual entrenadorCasual;
        entrenadorCasual = new EntrenadorCasual();
        entrenadorCasual.setIdEntrenador(16);
        entrenadorCasual.setCantidadMedallas(8);
        return entrenadorCasual;
    }
}
