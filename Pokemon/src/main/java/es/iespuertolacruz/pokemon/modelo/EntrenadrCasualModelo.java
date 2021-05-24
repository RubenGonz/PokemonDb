package es.iespuertolacruz.pokemon.modelo;

import es.iespuertolacruz.pokemon.api.EntrenadorCasual;
import es.iespuertolacruz.pokemon.excepciones.PersistenciaException;

public class EntrenadrCasualModelo {
    // Variables de clase

    DdBbSqLite persistencia;
    private static final String TABLA = "TIPO";
    private static final String CLAVE = "nombre";
    // Constructores

    public EntrenadrCasualModelo() throws PersistenciaException {
        persistencia = new DdBbSqLite(TABLA, CLAVE, null, null);
    }

    public void insertar(EntrenadorCasual entrenadorCasual) throws PersistenciaException {
        String sql = "INSERT INTO " + TABLA + " VALUES ('" + entrenadorCasual.getIdEntrenador() + "','"
                + entrenadorCasual.getCantidadMedallas() + "');";
        persistencia.update(sql);
    }

    public void eliminar(EntrenadorCasual entrenadorCasual) throws PersistenciaException {
        String sql = "DELETE FROM " + TABLA + " WHERE " + CLAVE + " = '" + entrenadorCasual.getIdEntrenador() + "';";
        persistencia.update(sql);
    }
/**
    public EntrenadorCasual buscar(int idEntrenador) throws PersistenciaException {
        return (AltoMando) persistencia.buscarElemento(idEntrenador);
    }
*/
    public void modificar(EntrenadorCasual entrenadorCasual) throws PersistenciaException {
        String sql = "UPDATE " + TABLA + " SET Tipo_Principal = '" + entrenadorCasual.getCantidadMedallas() + "' WHERE " + CLAVE
                + " = '" + entrenadorCasual.getIdEntrenador() + "';";
        persistencia.update(sql);
    }
}
