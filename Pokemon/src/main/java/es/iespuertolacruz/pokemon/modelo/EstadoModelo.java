package es.iespuertolacruz.pokemon.modelo;

import es.iespuertolacruz.pokemon.api.Estado;
import es.iespuertolacruz.pokemon.excepciones.PersistenciaException;

public class EstadoModelo {
    // Persistencia en MySql

    MySqlBdDd persistencia;

    // Constructores

    public EstadoModelo() {
        try {
            persistencia = new MySqlBdDd("com.mysql.jdbc.Driver", "jdbc:mysql://localhost/test", "minty", "greatsqldb");
        } catch (PersistenciaException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void insertar(Estado estado) throws PersistenciaException {
        persistencia.insertarEstado(estado);
    }

    public void eliminar(Estado estado) throws PersistenciaException {
        persistencia.eliminarEstado(estado);
    }

    public Estado buscar(int id) throws PersistenciaException {
        return persistencia.obtenerEstado(id);
    }

    public void modificar(Estado estado) throws PersistenciaException {
        persistencia.modificarEstado(estado);
    }
}
