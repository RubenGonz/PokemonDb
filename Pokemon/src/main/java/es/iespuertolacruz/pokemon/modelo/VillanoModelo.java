package es.iespuertolacruz.pokemon.modelo;

import es.iespuertolacruz.pokemon.api.Villano;
import es.iespuertolacruz.pokemon.excepciones.PersistenciaException;

public class VillanoModelo {
    // Variables de clase

    DdBbSqLite persistencia;
    private static final String TABLA = "TIPO";
    private static final String CLAVE = "nombre";

    // Constructores

    public VillanoModelo() throws PersistenciaException {
        persistencia = new DdBbSqLite(TABLA, CLAVE, null, null);
    }

    public void insertar(Villano villano) throws PersistenciaException {
        String sql = "INSERT INTO " + TABLA + " VALUES ('" + villano.getIdEntrenador() + "','"
                + villano.getProposito() + "');";
        persistencia.update(sql);
    }

    public void eliminar(Villano villano) throws PersistenciaException {
        String sql = "DELETE FROM " + TABLA + " WHERE " + CLAVE + " = '" + villano.getIdEntrenador() + "';";
        persistencia.update(sql);
    }
/**
    public Villano buscar(int numeroPokedex) throws PersistenciaException {
        return (Villano) persistencia.buscarElemento(numeroPokedex);
    }
*/
    public void modificar(Villano villano) throws PersistenciaException {
        String sql = "UPDATE " + TABLA + " SET Tipo_Principal = '" + villano.getProposito() + "' WHERE " + CLAVE
                + " = '" + villano.getIdEntrenador() + "';";
        persistencia.update(sql);
    }

}
