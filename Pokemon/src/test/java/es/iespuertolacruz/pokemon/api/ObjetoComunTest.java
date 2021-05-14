package es.iespuertolacruz.pokemon.api;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ObjetoComunTest {
    
    // Variables de la clase

    ObjetoComun objetoComun1;
    ObjetoComun objetoComun2;

    //BeforeEach y AfterEach

    @BeforeEach
    public void SetUp() {
        objetoComun1 = crearObjetoComun();
        objetoComun2 = new ObjetoComun(1,"Muestra informacion sobre los pokemon");
    }

    //Test

    @Test
    public void toStringTest() {
        assertEquals("1'Muestra informacion sobre los pokemon",objetoComun1.toString(), "El texto recibido no era el esperado");
    }

    //Funciones y metodos

    private ObjetoComun crearObjetoComun() {
        ObjetoComun objetoComun;
        objetoComun = new ObjetoComun();
        objetoComun.setIdObjeto(1);
        objetoComun.setEfecto("Muestra informacion sobre los pokemon");
        return objetoComun;
    }
}
