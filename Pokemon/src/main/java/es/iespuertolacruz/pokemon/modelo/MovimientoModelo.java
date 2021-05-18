package es.iespuertolacruz.pokemon.modelo;

import es.iespuertolacruz.pokemon.api.Movimiento;
import es.iespuertolacruz.pokemon.excepciones.PersistenciaException;

public class MovimientoModelo {
    // Persistencia en MySql

    MySqlBdDd persistencia;

    // Constructores

    public MovimientoModelo() {
        try {
            persistencia = new MySqlBdDd("com.mysql.jdbc.Driver", "jdbc:mysql://localhost/test", "minty", "greatsqldb");
        } catch (PersistenciaException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void insertar(Movimiento movimiento) throws PersistenciaException {
        persistencia.insertarMovimiento(movimiento);
    }

    public void eliminar(Movimiento movimiento) throws PersistenciaException {
        persistencia.eliminarMovimiento(movimiento);
    }

    public Movimiento buscar(int id) throws PersistenciaException {
        return persistencia.obtenerMovimiento(id);
    }

    public void modificar(Movimiento movimiento) throws PersistenciaException {
        persistencia.modificarMovimiento(movimiento);
    }
}
