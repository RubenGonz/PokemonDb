package es.iespuertolacruz.pokemon.api;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ObjetoTest {
    
    // Variables de la clase

    Objeto objeto1;
    Objeto objeto2;

    //BeforeEach y AfterEach

    @BeforeEach
    public void SetUp() {
        objeto1 = crearObjeto();
        objeto2 = new Objeto(8,"Fosil domo","Entregado",500);
    }

    //Test

    @Test
    public void toStringTest() {
        assertEquals("8'Fosil domo'Entregado'500",objeto1.toString(), "El texto recibido no era el esperado");
    }

    //Funciones y metodos

    private Objeto crearObjeto() {
        Objeto objeto;
        objeto = new Objeto();
        objeto.setId(8);
        objeto.setNombre("Fosil domo");
        objeto.setModoObtencion("Entregado");
        objeto.setPrecioVenta(500);
        return objeto;
    }
    
}
