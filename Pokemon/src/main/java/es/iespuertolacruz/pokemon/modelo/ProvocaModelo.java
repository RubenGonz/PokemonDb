package es.iespuertolacruz.pokemon.modelo;

import es.iespuertolacruz.pokemon.api.Provoca;
import es.iespuertolacruz.pokemon.excepciones.PersistenciaException;

public class ProvocaModelo {
    // Variables de clase

    DdBbSqLite persistencia;
    private static final String TABLA = "TIPO";
    private static final String CLAVE = "nombre";

    // Constructores

    public ProvocaModelo() throws PersistenciaException {
        persistencia = new DdBbSqLite(TABLA, CLAVE, null, null);
    }

    public void insertar(Provoca provoca) throws PersistenciaException {
        String sql = "INSERT INTO " + TABLA + " VALUES ('" + provoca.getIdMovimiento() + "','"
                + provoca.getIdEstado() + "');";
        persistencia.update(sql);
    }

    public void eliminar(Provoca provoca) throws PersistenciaException {
        String sql = "DELETE FROM " + TABLA + " WHERE " + CLAVE + " = '" + provoca.getIdMovimiento() + "';";
        persistencia.update(sql);
    }
/**
    public Provoca buscar(int idMovimiento) throws PersistenciaException {
        return (Provoca) persistencia.buscarElemento(idMovimiento);
    }
*/
    public void modificar(Provoca provoca) throws PersistenciaException {
        String sql = "UPDATE " + TABLA + " SET Id_Estado = '" + provoca.getIdEstado()
                + "' WHERE " + CLAVE
                + " = '" + provoca.getIdMovimiento() + "';";
        persistencia.update(sql);
    }

}
