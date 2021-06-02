package es.iespuertolacruz.pokemon.api;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Clase que testea la api de Objeto
 */
public class ObjetoTest {

    // Variables de la clase

    Objeto objeto1;
    Objeto objeto2;
    Objeto objeto3;

    // BeforeEach y AfterEach

    @BeforeEach
    public void SetUp() {
        objeto1 = crearObjeto();
        objeto2 = new Objeto(8, "Fosil domo", "Entregado");
        objeto3 = new Objeto("8'Fosil domo'Entregado");
    }

    // Test

    @Test
    public void toStringTest() {
        assertEquals("8'Fosil domo'Entregado", objeto1.toString(), "El texto recibido no era el esperado");
    }

    @Test
    public void EqualsTest() {
        assertEquals(objeto2, objeto3, "Los objetos son iguales y equals no lo reconoce");
    }

    // Funciones y metodos

    private Objeto crearObjeto() {
        Objeto objeto;
        objeto = new Objeto();
        objeto.setId(8);
        objeto.setNombre("Fosil domo");
        objeto.setModoObtencion("Entregado");
        return objeto;
    }

}
