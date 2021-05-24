package es.iespuertolacruz.pokemon.modelo;

import es.iespuertolacruz.pokemon.api.Maquina;
import es.iespuertolacruz.pokemon.excepciones.PersistenciaException;

public class MaquinaModelo {
    // Variables de clase

    DdBbSqLite persistencia;
    private static final String TABLA = "TIPO";
    private static final String CLAVE = "nombre";

    // Constructores

    public MaquinaModelo() throws PersistenciaException {
        persistencia = new DdBbSqLite(TABLA, CLAVE, null, null);
    }

    public void insertar(Maquina maquina) throws PersistenciaException {
        String sql = "INSERT INTO " + TABLA + " VALUES ('" + maquina.getIdObjeto() + "','" +
         maquina.getIdMovimiento() + "');";
        persistencia.update(sql);
    }

    public void eliminar(Maquina maquina) throws PersistenciaException {
        String sql = "DELETE FROM " + TABLA + " WHERE " + CLAVE + " = '" + maquina.getIdObjeto() + "';";
        persistencia.update(sql);
    }
/**
    public Maquina buscar(int idObjeto) throws PersistenciaException {
        return (Maquina) persistencia.buscarElemento(idObjeto);
    }
*/
    public void modificar(Maquina maquina) throws PersistenciaException {
        String sql = "UPDATE " + TABLA + " SET Tipo_Principal = '" + maquina.getIdMovimiento() + "' WHERE " + CLAVE
                + " = '" + maquina.getIdObjeto() + "';";
        persistencia.update(sql);
    }
}
