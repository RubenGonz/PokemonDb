package es.iespuertolacruz.pokemon.modelo;

import es.iespuertolacruz.pokemon.api.Villano;
import es.iespuertolacruz.pokemon.excepciones.PersistenciaException;

public class VillanoModelo {
    // Persistencia en MySql

    MySqlBdDd persistencia;

    // Constructores

    public VillanoModelo() {
        try {
            persistencia = new MySqlBdDd("com.mysql.jdbc.Driver", "jdbc:mysql://localhost/test", "minty", "greatsqldb");
        } catch (PersistenciaException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void insertar(Villano villano) throws PersistenciaException {
        persistencia.insertarVillano(villano);
    }

    public void eliminar(Villano villano) throws PersistenciaException {
        persistencia.eliminarVillano(villano);
    }

    public Villano buscar(int numeroPokedex) throws PersistenciaException {
        return persistencia.obtenerVillano(numeroPokedex);
    }

    public void modificar(Villano villano) throws PersistenciaException {
        persistencia.modificarVillano(villano);
    }

}
