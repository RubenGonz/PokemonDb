package es.iespuertolacruz.pokemon.modelo;

import es.iespuertolacruz.pokemon.api.LiderGimnasio;
import es.iespuertolacruz.pokemon.excepciones.PersistenciaException;

public class LiderGimnasioModelo {
    // Variables de clase

    DdBbSqLite persistencia;
    private static final String TABLA = "TIPO";
    private static final String CLAVE = "nombre";
    // Constructores

    public LiderGimnasioModelo() throws PersistenciaException {
        persistencia = new DdBbSqLite(TABLA, CLAVE, null, null);
    }

    public void insertar(LiderGimnasio liderGimnasio) throws PersistenciaException {
        String sql = "INSERT INTO " + TABLA + " VALUES ('" + liderGimnasio.getIdEntrenador() + "','"
                + liderGimnasio.getMedalla() + "');";
        persistencia.update(sql);
    }

    public void eliminar(LiderGimnasio liderGimnasio) throws PersistenciaException {
        String sql = "DELETE FROM " + TABLA + " WHERE " + CLAVE + " = '" + altoMando.getIdEntrenador() + "';";
        persistencia.update(sql);
    }

    public LiderGimnasio buscar(int idEntrenador) throws PersistenciaException {
        return (LiderGimnasio) persistencia.buscarElemento(idEntrenador);
    }

    public void modificar(LiderGimnasio liderGimnasio) throws PersistenciaException {
        String sql = "UPDATE " + TABLA + " SET Tipo_Principal = '" + liderGimnasio.getMedalla() + "' WHERE " + CLAVE
                + " = '" + liderGimnasio.getIdEntrenador() + "';";
        persistencia.update(sql);
    }
}
