package es.iespuertolacruz.pokemon.modelo;

import es.iespuertolacruz.pokemon.api.LiderGimnasio;
import es.iespuertolacruz.pokemon.excepciones.PersistenciaException;

public class LiderGimnasioModelo {
    // Persistencia en MySql

    MySqlBdDd persistencia;

    // Constructores

    public LiderGimnasioModelo() {
        try {
            persistencia = new MySqlBdDd("com.mysql.jdbc.Driver", "jdbc:mysql://localhost/test", "minty", "greatsqldb");
        } catch (PersistenciaException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void insertar(LiderGimnasio liderGimnasio) throws PersistenciaException {
        persistencia.insertarLiderGimnasio(liderGimnasio);
    }

    public void eliminar(LiderGimnasio liderGimnasio) throws PersistenciaException {
        persistencia.eliminarLiderGimnasio(liderGimnasio);
    }

    public LiderGimnasio buscar(int idEntrenador) throws PersistenciaException {
        return persistencia.obtenerLiderGimnasio(idEntrenador);
    }

    public void modificar(LiderGimnasio liderGimnasio) throws PersistenciaException {
        persistencia.modificarLiderGimnasio(liderGimnasio);
    }
}
