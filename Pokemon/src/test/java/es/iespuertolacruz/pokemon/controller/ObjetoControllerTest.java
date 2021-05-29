package es.iespuertolacruz.pokemon.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import es.iespuertolacruz.pokemon.api.Objeto;
import es.iespuertolacruz.pokemon.excepciones.PersistenciaException;
import es.iespuertolacruz.pokemon.excepciones.PokemonException;

public class ObjetoControllerTest {

    // Variables de clase

    static ObjetoController objetoController;
    Objeto objeto = null;

    // Before y after

    @BeforeAll
    public static void beforeAll() {
        if (objetoController == null) {
            try {
                objetoController = new ObjetoController();
            } catch (PersistenciaException e) {
                fail("Se ha producido un error iniciando objetoController");
            }
        }
    }

    @BeforeEach
    public void crearObjeto() {
        insertarObjetoTest();
    }

    @AfterEach
    public void eliminarObjeto() {
        if (objeto != null) {
            eliminarObjetoTest();
        }
    }

    // Test

    @Test
    public void insertarObjetoTest() {
        objeto = new Objeto(1,"Pokedex","Entregado");
        try {
            objetoController.insertar(objeto);
        } catch (PokemonException | PersistenciaException e) {
            assertTrue(e.getMessage().contains("El objeto indicado ya existe"),
                    "No se recibio el mensaje esperado");
        }
    }

    @Test
    public void validarTest() {
        Objeto objetoInvalida = new Objeto(0,"","");
        try {
            objetoController.validar(objetoInvalida);
        } catch (PokemonException e) {
              assertTrue(e.getMessage().contains(""));
        }
    }

    @Test
    public void validarNuloTest() {
        Objeto objetoInvalido = null;
        try {
            objetoController.validar(objetoInvalido);
        } catch (PokemonException e) {
            assertTrue(e.getMessage().contains("Se esta validando un objeto nulo"),
                    "No se recibio el mensaje esperado");
        }
    }

    @Test
    public void eliminarObjetoTest() {
        try {
            objetoController.eliminar(objeto);
        } catch (PokemonException | PersistenciaException e) {
            assertTrue(e.getMessage().contains("El objeto indicado no existe"),
                    "No se recibio el mensaje esperado");
        }
    }

    @Test
    public void eliminarPorNombreTest() {
        try {
            objetoController.eliminar(1);
        } catch (PokemonException | PersistenciaException e) {
            assertTrue(e.getMessage().contains("El objeto indicado no existe"),
                    "No se recibio el mensaje esperado");
        }
    }

    @Test
    public void buscarObjetoTest() {
        try {
            Objeto objetoEncontradas = objetoController.buscar(objeto.getId());
            assertNotNull(objetoEncontradas, "No se debe de obtener un elemento nulo");
            assertEquals(objeto, objetoEncontradas, "No se ha encontrado lo esperado");
        } catch (PersistenciaException e) {
            fail("Se ha producido un error en la consulta del objeto ,e:" + e.getMessage());
        }
    }
    
    @Test
    public void modificarObjetoExistenteTest() {
        try {
            objetoController.modificar(objeto);
        } catch (PokemonException | PersistenciaException e) {
            fail("No deberia llegar ningun mensaje de error y llega");
        }
    }

    @Test
    public void modificarObjetoInexistenteTest() {
        Objeto objetoInexistente = new Objeto(1000,"Objeto","Nulo");
        try {
            objetoController.modificar(objetoInexistente);
        } catch (PokemonException | PersistenciaException e) {
            assertEquals("El objeto indicado no existe", e.getMessage(),
                    "El mensaje recibido no es el esperado");
        }
    }

}
