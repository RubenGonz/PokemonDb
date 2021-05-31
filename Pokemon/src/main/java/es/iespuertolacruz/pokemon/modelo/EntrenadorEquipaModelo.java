package es.iespuertolacruz.pokemon.modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import es.iespuertolacruz.pokemon.api.EntrenadorEquipa;
import es.iespuertolacruz.pokemon.excepciones.PersistenciaException;

public class EntrenadorEquipaModelo {
    // Variables de clase

    DdBbSqLite persistencia;
    private static final String TABLA = "ENTRENADOR_EQUIPA";
    private static final String CLAVE = "id_entrenador";

    // Constructores
    public EntrenadorEquipaModelo() throws PersistenciaException {
        persistencia = new DdBbSqLite(TABLA, null, null);
    }

    // Metodos y funciones

    /**
     * Metodo que inserta entrenadorEquipa en la base de datos
     * 
     * @param entrenadorEquipa
     * @throws PersistenciaException
     */
    public void insertar(EntrenadorEquipa entrenadorEquipa) throws PersistenciaException {
        String sql = "INSERT INTO " + TABLA + " VALUES (" + entrenadorEquipa.getIdObjeto() + ","
                + entrenadorEquipa.getIdEntrenador() + ","+ entrenadorEquipa.getCantidad() + "');";
        persistencia.update(sql);
    }

    /***
     * Metodo que elimina entrenadorEquipa de la base de datos
     * 
     * @param entrenadorEquipa
     * @throws PersistenciaException
     */
    public void eliminar(EntrenadorEquipa entrenadorEquipa) throws PersistenciaException {
        String sql = "DELETE FROM " + TABLA + " WHERE " + CLAVE + " = " + entrenadorEquipa.getIdEntrenador()+ entrenadorEquipa
                .getIdObjeto() + ";";
        persistencia.update(sql);
    }

    /**
     * Metodo que modifica entrenadorEquipa de la base de datos
     * 
     * @param entrenadorEquipa a modificar
     * @throws PersistenciaException error controlado
     */
    public void modificar(EntrenadorEquipa entrenadorEquipa) throws PersistenciaException {
        String sql = "UPDATE " + TABLA + " SET TipoPrincipal = " + entrenadorEquipa.getCantidad() + "WHERE " + CLAVE
                + " = " + entrenadorEquipa.getIdEntrenador()+ entrenadorEquipa.getIdObjeto() + ";";
        persistencia.update(sql);
    }

    /**
     * Funcion encargada de obtener entrenadorEquipa
     * 
     * @param IdEntrenador y IdObjeto del entrenadorEquipa
     * @return entrenadorEquipa buscado
     * @throws PersistenciaException con error controlado
     */
    public EntrenadorEquipa buscar(int IdEntrenador , int IdObjeto) throws PersistenciaException {
        EntrenadorEquipa entrenadorEquipa = null;
        String sql = "SELECT * FROM " + TABLA + " WHERE " + CLAVE + " = " + IdEntrenador+ IdObjeto + ";";
        ArrayList<EntrenadorEquipa> lista = transformarAEntrenadorEquipa(sql);
        if (!lista.isEmpty()) {
            entrenadorEquipa = lista.get(0);
        }
        return entrenadorEquipa;
    }

    /**
     * Funcion que realiza una consulta sobre una sentencia sql dada
     * 
     * @param sql de la consulta
     * @return lista resultados (0..n) Usuasios
     * @throws PersistenciaException error controlado
     */
    private ArrayList<EntrenadorEquipa> transformarAEntrenadorEquipa(String sql) throws PersistenciaException {
        ArrayList<EntrenadorEquipa> lista = new ArrayList<>();
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Connection connection = null;
        try {
            connection = persistencia.getConnection();
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                EntrenadorEquipa entrenadorEquipa = new EntrenadorEquipa();
                entrenadorEquipa.setIdEntrenador(resultSet.getInt(CLAVE));
                entrenadorEquipa.setIdObjeto(resultSet.getInt(CLAVE));
                entrenadorEquipa.setCantidad(resultSet.getInt("Cantidad"));
                lista.add(entrenadorEquipa);
            }
        } catch (SQLException exception) {
            throw new PersistenciaException("Se ha producido un error en la busqueda", exception);
        } finally {
            persistencia.closeConecction(connection, statement, resultSet);
        }
        return lista;
    }
}
