package es.iespuertolacruz.pokemon.modelo;

import es.iespuertolacruz.pokemon.api.EstadisticasBase;
import es.iespuertolacruz.pokemon.excepciones.PersistenciaException;

public class EstadisticasBaseModelo {
    // Persistencia en MySql

    MySqlBdDd persistencia;

    // Constructores

    public EstadisticasBaseModelo() {
        try {
            persistencia = new MySqlBdDd("com.mysql.jdbc.Driver", "jdbc:mysql://localhost/test", "minty", "greatsqldb");
        } catch (PersistenciaException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void insertar(EstadisticasBase estadisticasBase) throws PersistenciaException {
        persistencia.insertarEstadisticasBase(estadisticasBase);
    }

    public void eliminar(EstadisticasBase estadisticasBase) throws PersistenciaException {
        persistencia.eliminarEstadisticasBase(estadisticasBase);
    }

    public EstadisticasBase buscar(int id) throws PersistenciaException {
        return persistencia.obtenerEstadisticasBase(id);
    }

    public void modificar(EstadisticasBase estadisticasBase) throws PersistenciaException {
        persistencia.modificarEstadisticasBase(estadisticasBase);
    }
}
