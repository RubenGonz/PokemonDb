package es.iespuertolacruz.pokemon.modelo;

import es.iespuertolacruz.pokemon.api.Caracteristicas;
import es.iespuertolacruz.pokemon.excepciones.PersistenciaException;

public class CaracteristicasModelo {
    // Persistencia en MySql

    MySqlBdDd persistencia;

    // Constructores

    public CaracteristicasModelo() {
            try {
                persistencia = new MySqlBdDd("com.mysql.jdbc.Driver", "jdbc:mysql://localhost/test", "minty",
                        "greatsqldb");
            } catch (PersistenciaException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

    public void insertar(Caracteristicas caracteristicas) throws PersistenciaException {
        persistencia.insertarCaracteristicas(caracteristicas);
    }

    public void eliminar(Caracteristicas caracteristicas) throws PersistenciaException {
        persistencia.eliminarCaracteristicas(caracteristicas);
    }

    public Caracteristicas buscar(int Id_caracteristica) throws PersistenciaException {
        return persistencia.obtenerCaracteristica(Id_caracteristica);
    }

    public void modificar(Caracteristicas caracteristicas) throws PersistenciaException {
        persistencia.modificarCaracteristicas(caracteristicas);
    }

}
