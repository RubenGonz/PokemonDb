package es.iespuertolacruz.pokemon.modelo;

import es.iespuertolacruz.pokemon.api.Pertenece;
import es.iespuertolacruz.pokemon.excepciones.PersistenciaException;

public class PerteneceModelo {
    // Persistencia en MySql

    MySqlBdDd persistencia;

    // Constructores

    public PerteneceModelo() {
        try {
            persistencia = new MySqlBdDd("com.mysql.jdbc.Driver", "jdbc:mysql://localhost/test", "minty", "greatsqldb");
        } catch (PersistenciaException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void insertar(Pertenece pertenece) throws PersistenciaException {
        persistencia.insertarPertenece(pertenece);
    }

    public void eliminar(Pertenece pertenece) throws PersistenciaException {
        persistencia.eliminarPertenece(pertenece);
    }

    public Pertenece buscar(int numeroPokedex) throws PersistenciaException {
        return persistencia.obtenerPertenece(numeroPokedex);
    }

    public void modificar(Pertenece pertenece) throws PersistenciaException {
        persistencia.modificarPertenece(pertenece);
    }

}
