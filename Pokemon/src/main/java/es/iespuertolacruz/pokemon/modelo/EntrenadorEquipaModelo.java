package es.iespuertolacruz.pokemon.modelo;

import es.iespuertolacruz.pokemon.api.EntrenadorEquipa;
import es.iespuertolacruz.pokemon.excepciones.PersistenciaException;

public class EntrenadorEquipaModelo {
    // Persistencia en MySql

    MySqlBdDd persistencia;

    // Constructores

    public EntrenadorEquipaModelo() {
        try {
            persistencia = new MySqlBdDd("com.mysql.jdbc.Driver", "jdbc:mysql://localhost/test", "minty", "greatsqldb");
        } catch (PersistenciaException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void insertar(EntrenadorEquipa entrenadorEquipa) throws PersistenciaException {
        persistencia.insertarEntrenadorEquipa(entrenadorEquipa);
    }

    public void eliminar(EntrenadorEquipa entrenadorEquipa) throws PersistenciaException {
        persistencia.eliminarEntrenadorEquipa(entrenadorEquipa);
    }

    public EntrenadorEquipa buscar(int idEntrenador) throws PersistenciaException {
        return persistencia.obtenerEntrenadorEquipa(idEntrenador);
    }

    public void modificar(EntrenadorEquipa entrenadorEquipa) throws PersistenciaException {
        persistencia.modificarEntrenadorEquipa(entrenadorEquipa);
    }
}
