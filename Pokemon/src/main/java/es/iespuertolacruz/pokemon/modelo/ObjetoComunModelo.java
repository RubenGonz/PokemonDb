package es.iespuertolacruz.pokemon.modelo;

import es.iespuertolacruz.pokemon.api.ObjetoComun;
import es.iespuertolacruz.pokemon.excepciones.PersistenciaException;

public class ObjetoComunModelo {
    // Variables de clase

    DdBbSqLite persistencia;
    private static final String TABLA = "TIPO";
    private static final String CLAVE = "nombre";
    // Constructores

    public ObjetoComunModelo() throws PersistenciaException {
        persistencia = new DdBbSqLite(TABLA, CLAVE, null, null);
    }

    public void insertar(ObjetoComun objetoComun) throws PersistenciaException {
        String sql = "INSERT INTO " + TABLA + " VALUES ('" + objetoComun.getIdObjeto() + "','"
                + objetoComun.getEfecto() + "');";
        persistencia.update(sql);
    }

    public void eliminar(ObjetoComun objetoComun) throws PersistenciaException {
        String sql = "DELETE FROM " + TABLA + " WHERE " + CLAVE + " = '" + objetoComun.getIdObjeto() + "';";
        persistencia.update(sql);
    }
/**
    public ObjetoComun buscar(int idObjeto) throws PersistenciaException {
        return (ObjetoComun) persistencia.buscarElemento(idObjeto);
    }
*/
    public void modificar(ObjetoComun objetoComun) throws PersistenciaException {
        String sql = "UPDATE " + TABLA + " SET Tipo_Principal = '" + objetoComun.getEfecto() + "' WHERE " + CLAVE
                + " = '" + objetoComun.getIdObjeto() + "';";
        persistencia.update(sql);
    }

}
