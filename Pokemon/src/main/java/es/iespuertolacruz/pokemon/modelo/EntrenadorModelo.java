package es.iespuertolacruz.pokemon.modelo;

import es.iespuertolacruz.pokemon.api.Entrenador;
import es.iespuertolacruz.pokemon.excepciones.PersistenciaException;

public class EntrenadorModelo {
    // Persistencia en MySql

    MySqlBdDd persistencia;

    // Constructores

    public EntrenadorModelo() {
        try {
            persistencia = new MySqlBdDd("com.mysql.jdbc.Driver", "jdbc:mysql://localhost/test", "minty", "greatsqldb");
        } catch (PersistenciaException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void insertar(Entrenador entrenador) throws PersistenciaException {
        persistencia.insertarEntrenador(entrenador);
    }

    public void eliminar(Entrenador entrenador) throws PersistenciaException {
        persistencia.eliminarEntrenador(entrenador);
    }

    public Entrenador buscar(int id) throws PersistenciaException {
        return persistencia.obtenerEntrenador(id);
    }

    public void modificar(Entrenador entrenador) throws PersistenciaException {
        persistencia.modificarEntrenador(entrenador);
    }

}
