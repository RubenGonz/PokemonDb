package es.iespuertolacruz.pokemon.controller;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import es.iespuertolacruz.pokemon.api.AltoMando;
import es.iespuertolacruz.pokemon.excepciones.PersistenciaException;
import es.iespuertolacruz.pokemon.excepciones.PokemonException;


public class AltaMandoControllerTest {
    
    // Variables de clase

    static AltoMandoController altoMandoController;
    AltoMando altoMando = null;


    // Before y after

    @BeforeAll
    public static void beforeAll() {
        if (altoMandoController == null) {
            try {
                altoMandoController = new AltoMandoController();
            } catch (Exception e) {
                fail(e.getMessage());
            }
        }
    }

   
    @Test
    public void insertarAltoMandoTest() {
     //   altoMando = new AltoMando(1, "");
       // try {
       //     altoMandoController.insertar(altoMando);
       // } catch (PokemonException | PersistenciaException e) {
       //     assertTrue(e.getMessage().contains("La caracteristica indicada ya existe"),
        //            "No se recibio el mensaje esperado");
       // }
    }

   
}
