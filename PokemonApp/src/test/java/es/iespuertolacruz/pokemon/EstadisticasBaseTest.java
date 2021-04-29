package es.iespuertolacruz.pokemon;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import es.iespuertolacruz.pokemon.api.EstadisticasBase;

public class EstadisticasBaseTest {
    
    // Variables de la clase

    EstadisticasBase estadisticasBase1;
    EstadisticasBase estadisticasBase2;

    //BeforeEach y AfterEach

    @BeforeEach
    public void SetUp() {
        estadisticasBase1 = crearEstadisticasBase();
        estadisticasBase2 = new EstadisticasBase(45, 49, 49, 65, 65, 45);
    }

    //Test

    @Test
    public void equalsTest() {
        assertTrue(estadisticasBase1.equals(estadisticasBase2), "Las estadisticas son iguales pero el equals dice lo contrario");
    }

    @Test
    public void toStringTest() {
        assertTrue(estadisticasBase1.toString().contains("65"), "El texto recibido no era el esperado");
    }

    //Funciones y metodos

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
