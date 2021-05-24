package es.iespuertolacruz.pokemon.modelo;

import es.iespuertolacruz.pokemon.api.Pertenece;
import es.iespuertolacruz.pokemon.excepciones.PersistenciaException;

public class PerteneceModelo {
    // Variables de clase

    DdBbSqLite persistencia;
    private static final String TABLA = "TIPO";
    private static final String CLAVE = "nombre";

    // Constructores

    public PerteneceModelo() throws PersistenciaException {
        persistencia = new DdBbSqLite(TABLA, CLAVE, null, null);
    }

    public void insertar(Pertenece pertenece) throws PersistenciaException {
        String sql = "INSERT INTO " + TABLA + " VALUES ('" + pertenece.getNumeroPokedex() + "','"
                + pertenece.getTipo() + "');";
        persistencia.update(sql);
    }

    public void eliminar(Pertenece pertenece) throws PersistenciaException {
        String sql = "DELETE FROM " + TABLA + " WHERE " + CLAVE + " = '" + pertenece.getNumeroPokedex() + "';";
        persistencia.update(sql);
    }
/**
    public Pertenece buscar(int numeroPokedex) throws PersistenciaException {
        return (Pertenece) persistencia.buscarElemento(numeroPokedex);
    }
*/
    public void modificar(Pertenece pertenece) throws PersistenciaException {
        String sql = "UPDATE " + TABLA + " SET Tipo_Principal = '" + pertenece.getTipo() + "' WHERE " + CLAVE
                + " = '" + pertenece.getNumeroPokedex() + "';";
        persistencia.update(sql);
    }

}
