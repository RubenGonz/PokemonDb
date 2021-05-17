package es.iespuertolacruz.pokemon.modelo;

import es.iespuertolacruz.pokemon.api.AltoMando;
import es.iespuertolacruz.pokemon.excepciones.PersistenciaException;

public class AltoMandoModelo {
    // Persistencia en MySql

    static MySqlBdDd persistencia;

    // Constructores

    public AltoMandoModelo() {
        try {
            persistencia = new MySqlBdDd("com.mysql.jdbc.Driver", "jdbc:mysql://localhost/test", "minty", "greatsqldb");
        } catch (PersistenciaException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static void insertar(AltoMando altoMando) throws PersistenciaException {
        persistencia.insertarAltoMando(altoMando);
    }

    public static void eliminar(AltoMando altoMando) throws PersistenciaException {
        persistencia.eliminarAltoMando(altoMando);
    }

    public static AltoMando buscar(int idEntrenador) throws PersistenciaException {
        return persistencia.obtenerAltoMando(idEntrenador);
    }

    public static void modificar(AltoMando altoMando) throws PersistenciaException {
        persistencia.modificarAltoMando(altoMando);
    }

}
