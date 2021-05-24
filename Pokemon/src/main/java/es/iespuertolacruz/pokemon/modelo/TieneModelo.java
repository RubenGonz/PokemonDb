package es.iespuertolacruz.pokemon.modelo;

import es.iespuertolacruz.pokemon.api.Tiene;
import es.iespuertolacruz.pokemon.excepciones.PersistenciaException;

public class TieneModelo {
    // Variables de clase

    DdBbSqLite persistencia;
    private static final String TABLA = "TIPO";
    private static final String CLAVE = "nombre";

    // Constructores

    public TieneModelo() throws PersistenciaException {
        persistencia = new DdBbSqLite(TABLA, CLAVE, null, null);
    }

    public void insertar(Tiene tiene) throws PersistenciaException {
        String sql = "INSERT INTO " + TABLA + " VALUES ('" + tiene.getIdEntrenador() + "','"
                + tiene.getNumeroPokedex()+ "','"+tiene.getCantidad() + "');";
        persistencia.update(sql);
    }

    public void eliminar(Tiene tiene) throws PersistenciaException {
        String sql = "DELETE FROM " + TABLA + " WHERE " + CLAVE + " = '" + tiene.getIdEntrenador() + "';";
        persistencia.update(sql);
    }
/**
    public Tiene buscar(int idEntrenador) throws PersistenciaException {
        return (Tiene) persistencia.buscarElemento(idEntrenador);
    }
*/
    public void modificar(Tiene tiene) throws PersistenciaException {
        String sql = "UPDATE " + TABLA + " SET Tipo_Principal = '" + tiene.getNumeroPokedex() + "','"
                + tiene.getCantidad() + "' WHERE " + CLAVE
                + " = '" + tiene.getIdEntrenador() + "';";
        persistencia.update(sql);
    }

}
