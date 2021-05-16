package es.iespuertolacruz.pokemon.api;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MaquinaTest {
    
    // Variables de la clase

    Maquina maquina1;
    Maquina maquina2;

    //BeforeEach y AfterEach

    @BeforeEach
    public void SetUp() {
        maquina1 = crearMaquina();
        maquina2 = new Maquina(17,61);
    }

    //Test

    @Test
    public void toStringTest() {
        assertEquals("17'61",maquina1.toString(), "El texto recibido no era el esperado");
    }

    //Funciones y metodos

    private Maquina crearMaquina() {
        Maquina maquina;
        maquina = new Maquina();
        maquina.setIdObjeto(17);
        maquina.setIdMovimiento(61);
        return maquina;
    }
}