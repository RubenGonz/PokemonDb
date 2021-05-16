package es.iespuertolacruz.pokemon.api;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PerteneceTest {
    
    // Variables de la clase

    Pertenece pertenece1;
    Pertenece pertenece2;

    //BeforeEach y AfterEach

    @BeforeEach
    public void SetUp() {
        pertenece1 = crearPertenece();
        pertenece2 = new Pertenece(1,"Planta");
    }

    //Test

    @Test
    public void toStringTest() {
        assertEquals("1'Planta",pertenece1.toString(), "El texto recibido no era el esperado");
    }

    //Funciones y metodos

    private Pertenece crearPertenece() {
        Pertenece pertenece;
        pertenece = new Pertenece();
        pertenece.setNumeroPokedex(1);
        pertenece.setTipo("Planta");
        return pertenece;
    }
}
