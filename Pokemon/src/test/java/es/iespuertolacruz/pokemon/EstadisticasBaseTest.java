package es.iespuertolacruz.pokemon;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
        estadisticasBase2 = new EstadisticasBase(1,45, 49, 49, 65, 65, 45);
    }

    //Test

    @Test
    public void toStringTest() {
        assertEquals("1'45'49'49'65'65'45",estadisticasBase1.toString(), "El texto recibido no era el esperado");
    }

    //Funciones y metodos

    private EstadisticasBase crearEstadisticasBase() {
        EstadisticasBase estadisticasBase;
        estadisticasBase = new EstadisticasBase();
        estadisticasBase.setId(1);
        estadisticasBase.setPsBase(45);
        estadisticasBase.setAtaqueBase(49);
        estadisticasBase.setDefensaBase(49);
        estadisticasBase.setAtaqueEspecialBase(65);
        estadisticasBase.setDefensaEspecialBase(65);
        estadisticasBase.setVelocidadBase(45);
        return estadisticasBase;
    }

}
