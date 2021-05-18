package es.iespuertolacruz.pokemon.modelo;

import es.iespuertolacruz.pokemon.api.Evoluciona;
import es.iespuertolacruz.pokemon.excepciones.PersistenciaException;

public class EvolucionaModelo {
    // Persistencia en MySql

    MySqlBdDd persistencia;

    // Constructores

    public EvolucionaModelo() {
        try {
            persistencia = new MySqlBdDd("com.mysql.jdbc.Driver", "jdbc:mysql://localhost/test", "minty", "greatsqldb");
        } catch (PersistenciaException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void insertar(Evoluciona evoluciona) throws PersistenciaException {
        persistencia.insertarEvoluciona(evoluciona);
    }

    public void eliminar(Evoluciona evolucionan) throws PersistenciaException {
        persistencia.eliminarEvoluciona(evolucionan);
    }

    public Evoluciona buscar(int numeroPokedexOrigen) throws PersistenciaException {
        return persistencia.obtenerEvoluciona(numeroPokedexOrigen);
    }

    public void modificar(Evoluciona evoluciona) throws PersistenciaException {
        persistencia.modificarEvoluciona(evoluciona);
    }
}
