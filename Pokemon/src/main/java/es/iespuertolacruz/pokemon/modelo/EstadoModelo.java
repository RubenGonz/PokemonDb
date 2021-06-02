package es.iespuertolacruz.pokemon.modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import es.iespuertolacruz.pokemon.api.Estado;
import es.iespuertolacruz.pokemon.excepciones.FicheroException;
import es.iespuertolacruz.pokemon.excepciones.PersistenciaException;

/**
 * Clase principal del modelo estado
 */
public class EstadoModelo {

    // Variables de clase

    DdBbSqLite persistencia;
    private static final String TABLA = "ESTADO";
    private static final String CLAVE = "id_estado";

    // Constructores

    /**
     * Constructor de EstadoModelo donde inicializa DdBbSqLite
     * 
     * @throws PersistenciaException con error controlado
     * @throws FicheroException      con error controlado
     */
    public EstadoModelo() throws PersistenciaException, FicheroException {
        persistencia = new DdBbSqLite(TABLA, null, null);
    }

    // Metodos y funciones

    /**
     * Metodo que inserta estados en la base de datos
     * 
     * @param estado a insertar
     * @throws PersistenciaException error controlado
     */
    public void insertar(Estado estado) throws PersistenciaException {
        String sql = "INSERT INTO " + TABLA + " VALUES (" 
        + estado.getId() + ",'" 
        + estado.getNombre() + "'," 
        + estado.getPersistencia() + ",'" 
        + estado.getEfecto() + "');";
        persistencia.update(sql);
    }

    /**
     * Metodo que elimina una Estado de la base de datos
     * 
     * @param estado a eliminar
     * @throws PersistenciaException error controlado
     */
    public void eliminar(Estado estado) throws PersistenciaException {
        String sql = "DELETE FROM " + TABLA + " WHERE " + CLAVE + " = " + estado.getId() + ";";
        persistencia.update(sql);
    }

    /**
     * Metodo que modifica una Estado de la base de datos
     * 
     * @param estado a modificar
     * @throws PersistenciaException error controlado
     */
    public void modificar(Estado estado) throws PersistenciaException {
        String sql = "UPDATE " + TABLA + " SET nombre = '" + estado.getNombre() + "'," 
        + "persistencia = " + estado.getPersistencia() + "," 
        + "efecto = '" + estado.getEfecto() + "'"
        + "WHERE " + CLAVE + " = " + estado.getId() + ";";
        persistencia.update(sql);
    }

    /**
     * Funcion encargada de obtener una estado
     * 
     * @param id_estado del pokemon
     * @return Pokemon buscado
     * @throws PersistenciaException con error controlado
     */
    public Estado buscar(int idEstado) throws PersistenciaException {
        Estado estado = null;
        String sql = "SELECT * FROM " + TABLA + " WHERE " + CLAVE + " = " + idEstado + ";";
        ArrayList<Estado> lista = transformarAEstado(sql);
        if (!lista.isEmpty()) {
            estado = lista.get(0);
        }
        return estado;
    }

    /**
     * Funcion que realiza una consulta sobre una sentencia sql dada
     * 
     * @param sql de la consulta
     * @return lista resultados (0..n) Usuasios
     * @throws PersistenciaException error controlado
     */
    private ArrayList<Estado> transformarAEstado(String sql) throws PersistenciaException {
        ArrayList<Estado> lista = new ArrayList<>();
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Connection connection = null;
        try {
            connection = persistencia.getConnection();
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Estado estado = new Estado();
                estado.setId(resultSet.getInt(CLAVE));
                estado.setNombre(resultSet.getString("nombre"));
                estado.setPersistencia(resultSet.getInt("persistencia"));
                estado.setEfecto(resultSet.getString("efecto"));

                lista.add(estado);
            }
        } catch (SQLException exception) {
            throw new PersistenciaException("Se ha producido un error en la busqueda", exception);
        } finally {
            persistencia.closeConecction(connection, statement, resultSet);
        }
        return lista;
    }
}
