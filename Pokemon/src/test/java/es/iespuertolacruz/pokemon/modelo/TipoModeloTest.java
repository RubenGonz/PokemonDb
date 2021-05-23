package es.iespuertolacruz.pokemon.modelo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import es.iespuertolacruz.pokemon.api.Tipo;
import es.iespuertolacruz.pokemon.excepciones.PersistenciaException;

public class TipoModeloTest {

    static TipoModelo tipoModelo;
    Tipo tipo = null;

    @BeforeAll 
    public static void beforeAll() {
        if (tipoModelo == null) {
            try {
                tipoModelo = new TipoModelo();
            } catch (Exception e) {
                fail("Se ha producido un error en el indicio de la BBDD");
            }
        }
    }

    @BeforeEach
    public void crearTipo() {
        tipo = new Tipo("Agua","Azul");
        try {
            tipoModelo.insertar(tipo);
        } catch (PersistenciaException e) {
            fail("Se ha producido un error insertando el tipo");
        }
    }

    @AfterEach
    public void eliminarTipo() {
        if (tipo != null) {
            try {
                tipoModelo.eliminar(tipo);
            } catch (PersistenciaException e) {
                fail("Se ha producido un error eliminando el pokemon");
            }
        }
    }

    @Test
    public void buscarTipoTest(){
        try {
            Tipo tipoEncontrado = tipoModelo.buscar(tipo.getNombre());
            assertNotNull(tipoEncontrado, "No se debe de obtener un elemento nulo");
            assertEquals(tipo, tipoEncontrado, "No se ha encontrado lo esperado");
        } catch (PersistenciaException e) {
           fail("Se ha producido un error en la consulta del tipo,e:"+e.getMessage());
        }
    }
}
