package es.iespuertolacruz.pokemon.modelo;

import es.iespuertolacruz.pokemon.api.Provoca;
import es.iespuertolacruz.pokemon.excepciones.PersistenciaException;

public class ProvocaModelo {
    // Persistencia en MySql

    MySqlBdDd persistencia;

    // Constructores

    public ProvocaModelo() {
        try {
            persistencia = new MySqlBdDd("com.mysql.jdbc.Driver", "jdbc:mysql://localhost/test", "minty", "greatsqldb");
        } catch (PersistenciaException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void insertar(Provoca provoca) throws PersistenciaException {
        persistencia.insertarProvoca(provoca);
    }

    public void eliminar(Provoca provoca) throws PersistenciaException {
        persistencia.eliminarProvoca(provoca);
    }

    public Provoca buscar(int idMovimiento) throws PersistenciaException {
        return persistencia.obtenerProvoca(idMovimiento);
    }

    public void modificar(Provoca provoca) throws PersistenciaException {
        persistencia.modificarProvoca(provoca);
    }

}
