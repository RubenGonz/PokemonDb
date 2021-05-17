package es.iespuertolacruz.pokemon.modelo;

import es.iespuertolacruz.pokemon.api.Conoce;
import es.iespuertolacruz.pokemon.excepciones.PersistenciaException;

public class ConoceModelo {
    // Persistencia en MySql

    MySqlBdDd persistencia;

    // Constructores

    public ConoceModelo() {
        try {
            persistencia = new MySqlBdDd("com.mysql.jdbc.Driver", "jdbc:mysql://localhost/test", "minty", "greatsqldb");
        } catch (PersistenciaException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void insertar(Conoce conoce) throws PersistenciaException {
        persistencia.insertarConoce(conoce);
    }

    public void eliminar(Conoce conoce) throws PersistenciaException {
        persistencia.eliminarConoce(conoce);
    }

    public Conoce buscar(int numeroPokedex) throws PersistenciaException {
        return persistencia.obtenerConoce(numeroPokedex);
    }

    public void modificar(Conoce conoce) throws PersistenciaException {
        persistencia.modificarConoce(conoce);
    }

}
