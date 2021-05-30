package es.iespuertolacruz.pokemon.modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import es.iespuertolacruz.pokemon.api.Entrenador;
import es.iespuertolacruz.pokemon.excepciones.PersistenciaException;

/**
 * Clase principal del modelo entrenador
 */
public class EntrenadorModelo {

    // Variables de clase

    DdBbSqLite persistencia;
    private static final String TABLA = "ENTRENADOR";
    private static final String CLAVE = "id_entrenador";

    // Constructores

    public EntrenadorModelo() throws PersistenciaException {
        persistencia = new DdBbSqLite(TABLA, null, null);
    }

    // Metodos y funciones

    /**
     * Metodo que inserta entrenador en la base de datos
     * 
     * @param entrenador a insertar
     * @throws PersistenciaException error controlado
     */
    public void insertar(Entrenador entrenador) throws PersistenciaException {
        String sql = "INSERT INTO " + TABLA + " VALUES (" 
        + entrenador.getId() + ",'" 
        + entrenador.getNombre() + "');";
        persistencia.update(sql);
    }

    /**
     * Metodo que elimina una Entrenador de la base de datos
     * 
     * @param entrenador a eliminar
     * @throws PersistenciaException error controlado
     */
    public void eliminar(Entrenador entrenador) throws PersistenciaException {
        String sql = "DELETE FROM " + TABLA + " WHERE " + CLAVE + " = " + entrenador.getId() + ";";
        persistencia.update(sql);
    }

    /**
     * Metodo que modifica una Entrenador de la base de datos
     * 
     * @param entrenador a modificar
     * @throws PersistenciaException error controlado
     */
    public void modificar(Entrenador entrenador) throws PersistenciaException {
        String sql = "UPDATE " + TABLA + " SET nombre = '" + entrenador.getNombre() + "'" 
        + "WHERE " + CLAVE + " = " + entrenador.getId() + ";";
        persistencia.update(sql);
    }

    /**
     * Funcion encargada de obtener una entrenador
     * 
     * @param id_entrenador del pokemon
     * @return Pokemon buscado
     * @throws PersistenciaException con error controlado
     */
    public Entrenador buscar(int idEntrenador) throws PersistenciaException {
        Entrenador entrenador = null;
        String sql = "SELECT * FROM " + TABLA + " WHERE " + CLAVE + " = " + idEntrenador + ";";
        ArrayList<Entrenador> lista = transformarAEntrenador(sql);
        if (!lista.isEmpty()) {
            entrenador = lista.get(0);
        }
        return entrenador;
    }

    /**
     * Funcion que realiza una consulta sobre una sentencia sql dada
     * 
     * @param sql de la consulta
     * @return lista resultados (0..n) Usuasios
     * @throws PersistenciaException error controlado
     */
    private ArrayList<Entrenador> transformarAEntrenador(String sql) throws PersistenciaException {
        ArrayList<Entrenador> lista = new ArrayList<>();
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Connection connection = null;
        try {
            connection = persistencia.getConnection();
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Entrenador entrenador = new Entrenador();
                entrenador.setId(resultSet.getInt(CLAVE));
                entrenador.setNombre(resultSet.getString("nombre"));
                lista.add(entrenador);
            }
        } catch (SQLException exception) {
            throw new PersistenciaException("Se ha producido un error en la busqueda", exception);
        } finally {
            persistencia.closeConecction(connection, statement, resultSet);
        }
        return lista;
    }
}
