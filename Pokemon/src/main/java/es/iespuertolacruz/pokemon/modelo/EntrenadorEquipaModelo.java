package es.iespuertolacruz.pokemon.modelo;

import es.iespuertolacruz.pokemon.api.EntrenadorEquipa;
import es.iespuertolacruz.pokemon.excepciones.PersistenciaException;

public class EntrenadorEquipaModelo {
    // Variables de clase

    DdBbSqLite persistencia;
    private static final String TABLA = "TIPO";
    private static final String CLAVE = "nombre";

    // Constructores

    public EntrenadorEquipaModelo() throws PersistenciaException {
        persistencia = new DdBbSqLite(TABLA, CLAVE, null, null);

    }

    public void insertar(EntrenadorEquipa entrenadorEquipa) throws PersistenciaException {
        String sql = "INSERT INTO " + TABLA + " VALUES ('" + entrenadorEquipa.getIdEntrenador() + "','"
           + "','"+  entrenadorEquipa.getCantidad()   +  entrenadorEquipa.getIdObjeto()+ "');";
        persistencia.update(sql);
    }

    public void eliminar(EntrenadorEquipa entrenadorEquipa) throws PersistenciaException {
        String sql = "DELETE FROM " + TABLA + " WHERE " + CLAVE + " = '" + entrenadorEquipa.getIdObjeto() + "';";
        persistencia.update(sql);
    }

    public EntrenadorEquipa buscar(int idEntrenador) throws PersistenciaException {
        return (EntrenadorEquipa) persistencia.buscarElemento(idEntrenador);
    }

    public void modificar(EntrenadorEquipa entrenadorEquipa) throws PersistenciaException {
        String sql = "UPDATE " + TABLA + " SET id_Entrenador = '" + entrenadorEquipa.getIdObjeto()+entrenadorEquipa.getCantidad() + "' WHERE " + CLAVE
                + " = '" + entrenadorEquipa.getIdObjeto() + "';";
        persistencia.update(sql);
    }
}
