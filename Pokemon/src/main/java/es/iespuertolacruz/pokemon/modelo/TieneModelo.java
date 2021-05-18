package es.iespuertolacruz.pokemon.modelo;

import es.iespuertolacruz.pokemon.api.Tiene;
import es.iespuertolacruz.pokemon.excepciones.PersistenciaException;

public class TieneModelo {
    // Persistencia en MySql

    MySqlBdDd persistencia;

    // Constructores

    public TieneModelo() {
        try {
            persistencia = new MySqlBdDd("com.mysql.jdbc.Driver", "jdbc:mysql://localhost/test", "minty", "greatsqldb");
        } catch (PersistenciaException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void insertar(Tiene tiene) throws PersistenciaException {
        persistencia.insertarTiene(tiene);
    }

    public void eliminar(Tiene tiene) throws PersistenciaException {
        persistencia.eliminarTiene(tiene);
    }

    public Tiene buscar(int idEntrenador) throws PersistenciaException {
        return persistencia.obtenerTiene(idEntrenador);
    }

    public void modificar(Tiene tiene) throws PersistenciaException {
        persistencia.modificarTiene(tiene);
    }

}
