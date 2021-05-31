package es.iespuertolacruz.pokemon.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import es.iespuertolacruz.pokemon.api.Maquina;
import es.iespuertolacruz.pokemon.excepciones.PersistenciaException;
import es.iespuertolacruz.pokemon.excepciones.PokemonException;

public class MaquinaControllerTest {
    // Variables de clase

    static MaquinaController maquinaController;
    Maquina maquina = null;


     @BeforeAll
    public static void beforeAll() {
        if (maquinaController == null) {
            try {
                maquinaController = new MaquinaController();
            } catch (Exception e) {
                fail("Se ha producido un error iniciando maquinaController");
            }
        }
    }

 /*
    @BeforeEach
    public void crearmaquina() {
        insertarmaquinaTest();
    }

    @AfterEach
    public void eliminarmaquina() {
        if (maquina != null) {
            eliminarmaquinaTest();
        }
    }

    // Test
    

    @Test
    public void insertarmaquinaTest() {
        maquina = new Maquina(1, 1);
        try {
            maquinaController.insertar(maquina);
        } catch (PokemonException | PersistenciaException e) {
            assertTrue(e.getMessage().contains("El maquina indicado ya existe"), "No se recibio el mensaje esperado");
        }
    }

    @Test
    public void validarTest() {
        Maquina maquinaInvalido = new Maquina(1, 1);
        try {
            maquinaController.validar(maquinaInvalido);
        } catch (PokemonException e) {
            assertTrue(e.getMessage().contains(""));
        }
    }

    @Test
    public void validarNuloTest() {
        Maquina maquinaInvalido = null;
        try {
            maquinaController.validar(maquinaInvalido);
        } catch (PokemonException e) {
            assertTrue(e.getMessage().contains("Se esta validando un objeto nulo"),
                    "No se recibio el mensaje esperado");
        }
    }

    @Test
    public void eliminarmaquinaTest() {
        try {
            maquinaController.eliminar(maquina);
        } catch (PokemonException | PersistenciaException e) {
            assertTrue(e.getMessage().contains("El maquina indicado no existe"), "No se recibio el mensaje esperado");
        }
    }

    @Test
    public void eliminarPorIdEntrenadorTest() {
        try {
            maquinaController.eliminar(1 ,2);
        } catch (PokemonException | PersistenciaException e) {
            assertTrue(e.getMessage().contains("El maquina indicado no existe"), "No se recibio el mensaje esperado");
        }
    }

    @Test
    public void buscarmaquinaTest() {
        try {
          Maquina maquinaEncontrado = maquinaController.buscar(maquina.getIdMovimiento() ,maquina.getIdObjeto());
           assertNotNull(maquinaEncontrado, "No se debe de obtener un elemento nulo");
            assertEquals(maquina, maquinaEncontrado, "No se ha encontrado lo esperado");
        } catch (PersistenciaException e) {
            fail("Se ha producido un error en la consulta del pokemon ,e:" + e.getMessage());
        }
    }

    @Test
    public void modificarmaquinaExistenteTest() {
        try {
            maquinaController.modificar(maquina);
        } catch (PokemonException | PersistenciaException e) {
            fail("No deberia llgar ningun mensaje de error y llega");
        }
    }

    @Test
    public void modificarmaquinaInexistenteTest() {
        Maquina maquinaInexistente = new Maquina(1, 1);
        try {
            maquinaController.modificar(maquinaInexistente);
        } catch (PokemonException | PersistenciaException e) {
            assertEquals("El maquina indicado no existe", e.getMessage(), "El mensaje recibido no es el esperado");
        }
    }
*/
}
