package es.iespuertolacruz.pokemon.modelo;

import es.iespuertolacruz.pokemon.api.Maquina;
import es.iespuertolacruz.pokemon.excepciones.PersistenciaException;

public class MaquinaModelo {
    // Persistencia en MySql

    MySqlBdDd persistencia;

    // Constructores

    public MaquinaModelo() {
        try {
            persistencia = new MySqlBdDd("com.mysql.jdbc.Driver", "jdbc:mysql://localhost/test", "minty", "greatsqldb");
        } catch (PersistenciaException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void insertar(Maquina maquina) throws PersistenciaException {
        persistencia.insertarMaquina(maquina);
    }

    public void eliminar(Maquina maquina) throws PersistenciaException {
        persistencia.eliminarMaquina(maquina);
    }

    public Maquina buscar(int idObjeto) throws PersistenciaException {
        return persistencia.obtenerMaquina(idObjeto);
    }

    public void modificar(Maquina maquina) throws PersistenciaException {
        persistencia.modificarMaquina(maquina);
    }
}
