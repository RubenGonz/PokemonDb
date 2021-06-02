package es.iespuertolacruz.pokemon.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import es.iespuertolacruz.pokemon.api.Tipo;
import es.iespuertolacruz.pokemon.excepciones.PersistenciaException;
import es.iespuertolacruz.pokemon.excepciones.PokemonException;

public class TipoControllerTest {
    
    // Variables de clase

    static TipoController tipoController;
    Tipo tipo = null;

    // Before y after

    @BeforeAll
    public static void beforeAll() {
        if (tipoController == null) {
            try {
                tipoController = new TipoController();
            } catch (Exception e) {
                fail("Se ha producido un error iniciando tipoController");
            }
        }
    }

    @BeforeEach
    public void crearTipo() {
        insertarTipoTest();
    }

    @AfterEach
    public void eliminarTipo() {
        if (tipo != null) {
            eliminarTipoTest();
        }
    }

    // Test

    @Test
    public void insertarTipoTest() {
        tipo = new Tipo("Fuego", "Rojo");
        try {
            tipoController.insertar(tipo);
        } catch (PokemonException | PersistenciaException e) {
            assertTrue(e.getMessage().contains("El tipo indicado ya existe"), "No se recibio el mensaje esperado");
        }
    }

    @Test
    public void validarTest() {
        Tipo tipoInvalido = new Tipo("", "");
        try {
            tipoController.validar(tipoInvalido);
        } catch (PokemonException e) {
            assertTrue(e.getMessage().contains(
                    "El nombre del tipo es nulo o vacio, el color del tipo es nulo o vacio."),
                    "No se recibio el mensaje esperado");
        }
    }

    @Test
    public void validarNuloTest() {
        Tipo tipoInvalido = null;
        try {
            tipoController.validar(tipoInvalido);
        } catch (PokemonException e) {
            assertTrue(e.getMessage().contains("Se esta validando un objeto nulo"),
                    "No se recibio el mensaje esperado");
        }
    }

    @Test
    public void eliminarTipoTest() {
        try {
            tipoController.eliminar(tipo);
        } catch (PokemonException | PersistenciaException e) {
            assertTrue(e.getMessage().contains("El tipo indicado no existe"), "No se recibio el mensaje esperado");
        }
    }

    @Test
    public void eliminarPorNombreTest() {
        try {
            tipoController.eliminar("Fuego");
        } catch (PokemonException | PersistenciaException e) {
            assertTrue(e.getMessage().contains("El tipo indicado no existe"), "No se recibio el mensaje esperado");
        }
    }

    @Test
    public void buscarTipoTest() {
        try {
            Tipo tipoEncontrado = tipoController.buscar(tipo.getNombre());
            assertNotNull(tipoEncontrado, "No se debe de obtener un elemento nulo");
            assertEquals(tipo, tipoEncontrado, "No se ha encontrado lo esperado");
        } catch (PersistenciaException e) {
            fail("Se ha producido un error en la consulta del tipo ,e:" + e.getMessage());
        }
    }

    @Test
    public void modificarTipoExistenteTest() {
        try {
            tipoController.modificar(tipo);
        } catch (PokemonException | PersistenciaException e) {
            fail("No deberia llgar ningun mensaje de error y llega");
        }
    }

    @Test
    public void modificarTipoInexistenteTest() {
        Tipo tipoInexistente = new Tipo("Tipo","Inexistente");
        try {
            tipoController.modificar(tipoInexistente);
        } catch (PokemonException | PersistenciaException e) {
            assertEquals("El tipo indicado no existe",e.getMessage(), "El mensaje recibido no es el esperado");
        }
    }

}
