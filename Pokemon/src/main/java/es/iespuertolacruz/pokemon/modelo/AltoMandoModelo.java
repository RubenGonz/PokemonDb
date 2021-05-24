package es.iespuertolacruz.pokemon.modelo;

import es.iespuertolacruz.pokemon.api.AltoMando;
import es.iespuertolacruz.pokemon.excepciones.PersistenciaException;

public class AltoMandoModelo {
    
    // Variables de clase

    DdBbSqLite persistencia;
    private static final String TABLA = "TIPO";
    private static final String CLAVE = "nombre";

    // Constructores
    public AltoMandoModelo() throws PersistenciaException {
        persistencia = new DdBbSqLite(TABLA, CLAVE, null, null);
    }

    public void insertar(AltoMando altoMando) throws PersistenciaException {
        String sql = "INSERT INTO " + TABLA + " VALUES ('" + altoMando.getIdEntrenador() + "','" + altoMando.getTipoPrincipal() + "');";
        persistencia.update(sql);
    }

    public void eliminar(AltoMando altoMando) throws PersistenciaException {
        String sql = "DELETE FROM " + TABLA + " WHERE " + CLAVE + " = '" + altoMando.getIdEntrenador() + "';";
        persistencia.update(sql);
    }
/** 
    public AltoMando buscar(int idEntrenador) throws PersistenciaException {
        return (AltoMando) persistencia.buscarElemento(idEntrenador);
    }
*/
    public void actualizar(AltoMando altoMando) throws PersistenciaException {
        String sql = "UPDATE " + TABLA + " SET Tipo_Principal = '" + altoMando.getTipoPrincipal() + "' WHERE " + CLAVE + " = '"
                + altoMando.getIdEntrenador() + "';";
        persistencia.update(sql);
    }
}
