package es.iespuertolacruz.pokemon.modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import es.iespuertolacruz.pokemon.api.Tiene;
import es.iespuertolacruz.pokemon.excepciones.FicheroException;
import es.iespuertolacruz.pokemon.excepciones.PersistenciaException;

/**
 * Clase modelo de los pokemon de los entrenadores
 */
public class TieneModelo {

    // Variables de clase

    DdBbSqLite persistencia;
    private static final String TABLA = "TIENE";
    private static final String CLAVEPRI = "id_entrenador";
    private static final String CLAVESEC = "numero_pokedex";

    // Constructores

    /**
     * Constructor de ProvocaModelo donde inicializa DdBbSqLite
     * 
     * @throws PersistenciaException con error controlado
     * @throws FicheroException      con error controlado
     */
    public TieneModelo() throws PersistenciaException, FicheroException {
        persistencia = new DdBbSqLite(TABLA, null, null);
    }

    // Metodos y funciones

    /**
     * Metodo que inserta tiene en la base de datos
     * 
     * @param tiene a insertar
     * @throws PersistenciaException error controlado
     */
    public void insertar(Tiene tiene) throws PersistenciaException {
        String sql = "INSERT INTO " + TABLA + " VALUES (" + tiene.getIdEntrenador() + "," + tiene.getNumeroPokedex()
                + "," + tiene.getCantidad() + ");";
        persistencia.update(sql);
    }

    /**
     * Metodo que elimina tiene de la base de datos
     * 
     * @param tiene a eliminar
     * @throws PersistenciaException error controlado
     */
    public void eliminar(Tiene tiene) throws PersistenciaException {
        String sql = "DELETE FROM " + TABLA + " WHERE " + CLAVEPRI + " = " + tiene.getIdEntrenador() + " AND "
                + CLAVESEC + " = " + tiene.getNumeroPokedex() + ";";
        persistencia.update(sql);
    }

    /**
     * Metodo que modifica tiene de la base de datos
     * 
     * @param tiene a modificar
     * @throws PersistenciaException error controlado
     */
    public void modificar(Tiene tiene) throws PersistenciaException {
        String sql = "UPDATE " + TABLA + " SET cantidad = " + tiene.getCantidad() + " WHERE " + CLAVEPRI + " = "
                + tiene.getIdEntrenador() + " AND " + CLAVESEC + " = " + tiene.getNumeroPokedex() + ";";
        persistencia.update(sql);
    }

    /**
     * Funcion encargada de obtener tiene
     * 
     * @param idEntrenador  del tiene
     * @param numeroPokedex del tiene
     * @return tiene buscado
     * @throws PersistenciaException con error controlado
     */
    public Tiene buscar(int idEntrenador, int numeroPokedex) throws PersistenciaException {
        Tiene tiene = null;
        String sql = "SELECT * FROM " + TABLA + " WHERE " + CLAVEPRI + " = " + idEntrenador + " AND " + CLAVESEC + " = "
                + numeroPokedex + ";";
        ArrayList<Tiene> lista = transformarATiene(sql);
        if (!lista.isEmpty()) {
            tiene = lista.get(0);
        }
        return tiene;
    }

    /**
     * Funcion que realiza una consulta sobre una sentencia sql dada
     * 
     * @param sql de la consulta
     * @return lista resultados (0..n) Usuasios
     * @throws PersistenciaException error controlado
     */
    private ArrayList<Tiene> transformarATiene(String sql) throws PersistenciaException {
        ArrayList<Tiene> lista = new ArrayList<>();
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Connection connection = null;
        try {
            connection = persistencia.getConnection();
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Tiene tiene = new Tiene();
                tiene.setIdEntrenador(resultSet.getInt(CLAVEPRI));
                tiene.setNumeroPokedex(resultSet.getInt(CLAVESEC));
                tiene.setCantidad(resultSet.getInt("cantidad"));
                lista.add(tiene);
            }
        } catch (SQLException exception) {
            throw new PersistenciaException("Se ha producido un error en la busqueda", exception);
        } finally {
            persistencia.closeConecction(connection, statement, resultSet);
        }
        return lista;
    }
}
