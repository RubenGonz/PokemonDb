package es.iespuertolacruz.pokemon.modelo;

import es.iespuertolacruz.pokemon.api.Tipo;
import es.iespuertolacruz.pokemon.excepciones.PersistenciaException;

public class TipoModelo {
    // Persistencia en MySql

    MySqlBdDd persistencia;

    // Constructores

    public TipoModelo() {
        try {
            persistencia = new MySqlBdDd("com.mysql.jdbc.Driver", "jdbc:mysql://localhost/test", "minty", "greatsqldb");
        } catch (PersistenciaException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void insertar(Tipo tipo) throws PersistenciaException {
        persistencia.insertarTipo(tipo);
    }

    public void eliminar(Tipo tipo) throws PersistenciaException {
        persistencia.eliminarTipo(tipo);
    }

    public Tipo buscar(String nombre) throws PersistenciaException {
        return persistencia.obtenerTipo(nombre);
    }

    public void modificar(Tipo tipo) throws PersistenciaException {
        persistencia.modificarTipo(tipo);
    }

}
