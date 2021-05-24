package es.iespuertolacruz.pokemon.modelo;

import es.iespuertolacruz.pokemon.api.Entrenador;
import es.iespuertolacruz.pokemon.excepciones.PersistenciaException;

public class EntrenadorModelo {
    
    // Variables de clase

    DdBbSqLite persistencia;
    private static final String TABLA = "TIPO";
    private static final String CLAVE = "nombre";

    // Constructores

    public EntrenadorModelo() throws PersistenciaException {
        persistencia = new DdBbSqLite(TABLA, CLAVE, null, null);
    }

    // Metodos y funciones

    public void insertar(Entrenador entrenador) throws PersistenciaException {
        String sql = "INSERT INTO " + TABLA + " VALUES ('" + entrenador.getId() + "','" + entrenador.getNombre() + "');";
        persistencia.update(sql);
    }

    public void eliminar(Entrenador entrenador) throws PersistenciaException {
        String sql = "DELETE FROM " + TABLA + " WHERE " + CLAVE + " = '" + entrenador.getId() + "';";
        persistencia.update(sql);
    }

    public Entrenador buscar(int id) throws PersistenciaException {
        return (Entrenador) persistencia.buscarElemento(id);
    }

    public void actualizar(Entrenador entrenador) throws PersistenciaException {
        String sql = "UPDATE " + TABLA + " SET nombre = '" +entrenador.getNombre() + "' WHERE " + CLAVE + " = '"
                + entrenador.getId() + "';";
        persistencia.update(sql);
    }

}
