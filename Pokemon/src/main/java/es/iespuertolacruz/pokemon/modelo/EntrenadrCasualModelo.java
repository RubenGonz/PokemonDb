package es.iespuertolacruz.pokemon.modelo;

import es.iespuertolacruz.pokemon.api.EntrenadorCasual;
import es.iespuertolacruz.pokemon.excepciones.PersistenciaException;

public class EntrenadrCasualModelo {
    // Persistencia en MySql

    MySqlBdDd persistencia;

    // Constructores

    public EntrenadrCasualModelo() {
        try {
            persistencia = new MySqlBdDd("com.mysql.jdbc.Driver", "jdbc:mysql://localhost/test", "minty", "greatsqldb");
        } catch (PersistenciaException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void insertar(EntrenadorCasual entrenadorCasual) throws PersistenciaException {
        persistencia.insertarEntrenadorCasual(entrenadorCasual);
    }

    public void eliminar(EntrenadorCasual entrenadorCasual) throws PersistenciaException {
        persistencia.eliminarEntrenadorCasual(entrenadorCasual);
    }

    public EntrenadorCasual buscar(int numeroPokedex) throws PersistenciaException {
        return persistencia.obtenerEntrenadorCasual(numeroPokedex);
    }

    public void modificar(EntrenadorCasual entrenadorCasual) throws PersistenciaException {
        persistencia.modificarEntrenadorCasual(entrenadorCasual);
    }
}
