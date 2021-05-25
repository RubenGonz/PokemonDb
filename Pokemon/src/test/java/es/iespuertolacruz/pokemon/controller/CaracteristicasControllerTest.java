package es.iespuertolacruz.pokemon.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import es.iespuertolacruz.pokemon.api.Caracteristicas;
import es.iespuertolacruz.pokemon.excepciones.PersistenciaException;
import es.iespuertolacruz.pokemon.excepciones.PokemonException;

public class CaracteristicasControllerTest {

    // Variables de clase

    static CaracteristicasController caracteristicasController;
    Caracteristicas caracteristicas = null;

    // Before y after

    @BeforeAll
    public static void beforeAll() {
        if (caracteristicasController == null) {
            try {
                caracteristicasController = new CaracteristicasController();
            } catch (PersistenciaException e) {
                fail("Se ha producido un error iniciando caracteristicasController");
            }
        }
    }

    @BeforeEach
    public void crearCaracteristicas() {
        insertarCaracteristicasTest();
    }

    @AfterEach
    public void eliminarCaracteristicas() {
        if (caracteristicas != null) {
            eliminarCaracteristicasTest();
        }
    }

    // Test

    @Test
    public void insertarCaracteristicasTest() {
        caracteristicas = new Caracteristicas(1, 6.9f, 0.7f, "Semilla", "Espesura", "Starter");
        try {
            caracteristicasController.insertar(caracteristicas);
        } catch (PokemonException | PersistenciaException e) {
            assertTrue(e.getMessage().contains("La caracteristica indicada ya existe"),
                    "No se recibio el mensaje esperado");
        }
    }

    @Test
    public void validarTest() {
        Caracteristicas caracteristicasInvalida = new Caracteristicas(0,0.0f,0.0f,"","","");
        try {
            caracteristicasController.validar(caracteristicasInvalida);
        } catch (PokemonException e) {
              assertTrue(e.getMessage().contains(""));
        }
    }

    @Test
    public void validarNuloTest() {
        Caracteristicas caracteristicasInvalido = null;
        try {
            caracteristicasController.validar(caracteristicasInvalido);
        } catch (PokemonException e) {
            assertTrue(e.getMessage().contains("Se esta validando un objeto nulo"),
                    "No se recibio el mensaje esperado");
        }
    }

    @Test
    public void eliminarCaracteristicasTest() {
        try {
            caracteristicasController.eliminar(caracteristicas);
        } catch (PokemonException | PersistenciaException e) {
            assertTrue(e.getMessage().contains("La caracteristica indicada no existe"),
                    "No se recibio el mensaje esperado");
        }
    }

    @Test
    public void eliminarPorNombreTest() {
        try {
            caracteristicasController.eliminar(1);
        } catch (PokemonException | PersistenciaException e) {
            assertTrue(e.getMessage().contains("La caracteristica indicada no existe"),
                    "No se recibio el mensaje esperado");
        }
    }

    @Test
    public void buscarCaracteristicasTest() {
        try {
            Caracteristicas caracteristicasEncontradas = caracteristicasController.buscar(caracteristicas.getId());
            assertNotNull(caracteristicasEncontradas, "No se debe de obtener un elemento nulo");
            assertEquals(caracteristicas, caracteristicasEncontradas, "No se ha encontrado lo esperado");
        } catch (PersistenciaException e) {
            fail("Se ha producido un error en la consulta del caracteristicas ,e:" + e.getMessage());
        }
    }

    @Test
    public void modificarCaracteristicasExistenteTest() {
        try {
            caracteristicasController.modificar(caracteristicas);
        } catch (PokemonException | PersistenciaException e) {
            fail("No deberia llgar ningun mensaje de error y llega");
        }
    }

    @Test
    public void modificarCaracteristicasInexistenteTest() {
        Caracteristicas caracteristicasInexistente = new Caracteristicas(8000,8000f,8000f,"Cractersitica","Inexistente","Aqui");
        try {
            caracteristicasController.modificar(caracteristicasInexistente);
        } catch (PokemonException | PersistenciaException e) {
            assertEquals("La caracteristica indicada no existe", e.getMessage(),
                    "El mensaje recibido no es el esperado");
        }
    }

}
