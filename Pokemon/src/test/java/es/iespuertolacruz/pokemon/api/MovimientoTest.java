package es.iespuertolacruz.pokemon.api;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MovimientoTest {

    // Variables de la clase

    Movimiento movimiento1;
    Movimiento movimiento2;

    //BeforeEach y AfterEach

    @BeforeEach
    public void SetUp() {
        movimiento1 = crearMovimiento();
        movimiento1 = new Movimiento(15,"Impactrueno","Electrico","Especial",30,40,100,1);
    }

    //Test

    @Test
    public void toStringTest() {
        assertEquals("15'Impactrueno'Electrico'Especial'30'40'100'1",movimiento1.toString(), "El texto recibido no era el esperado");
    }

    //Funciones y metodos

    private Movimiento crearMovimiento() {
        Movimiento movimiento;
        movimiento = new Movimiento();
        movimiento.setId(1);
        movimiento.setNombre("Burbuja");
        movimiento.setTipo("Agua");
        movimiento.setCategoria("Especial");
        movimiento.setPp(30);
        movimiento.setPotencia(40);
        movimiento.setCerteza(100);
        movimiento.setIdEstado(1);
        return movimiento;
    }

}
