package es.iespuertolacruz.pokemon.modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import es.iespuertolacruz.pokemon.api.EntrenadorCasual;
import es.iespuertolacruz.pokemon.excepciones.FicheroException;
import es.iespuertolacruz.pokemon.excepciones.PersistenciaException;

/**
 * Clase principal del modelo de EntrenadorCasual
 */
public class EntrenadorCasualModelo {
    
    // Variables de clase

    DdBbSqLite persistencia;
    private static final String TABLA = "ENTRENADOR_CASUAL";
    private static final String CLAVE = "id_entrenador";

    // Constructores

    /**
     * Constructor de EntrenadorCasualModelo donde inicializa DdBbSqLite
     * 
     * @throws PersistenciaException con error controlado
     * @throws FicheroException      con error controlado
     */
    public EntrenadorCasualModelo() throws PersistenciaException, FicheroException {
        persistencia = new DdBbSqLite(TABLA, null, null);
    }

    // Metodos y funciones

    /**
     * Metodo que inserta entrenadorCasual en la base de datos
     * 
     * @param entrenadorCasual a insertar
     * @throws PersistenciaException con error controlado
     */
    public void insertar(EntrenadorCasual entrenadorCasual) throws PersistenciaException {
        String sql = "INSERT INTO " + TABLA + " VALUES (" + entrenadorCasual.getIdEntrenador() + ","
                + entrenadorCasual.getCantidadMedallas() + ");";
        persistencia.update(sql);
    }

    /***
     * Metodo que elimina entrenadorCasual de la base de datos
     * 
     * @param entrenadorCasual a eliminar
     * @throws PersistenciaException con error controlado
     */
    public void eliminar(EntrenadorCasual entrenadorCasual) throws PersistenciaException {
        String sql = "DELETE FROM " + TABLA + " WHERE " + CLAVE + " = " + entrenadorCasual.getIdEntrenador() + ";";
        persistencia.update(sql);
    }

    /**
     * Metodo que modifica entrenadorCasual de la base de datos
     * 
     * @param entrenadorCasual a modificar
     * @throws PersistenciaException error controlado
     */
    public void modificar(EntrenadorCasual entrenadorCasual) throws PersistenciaException {
        String sql = "UPDATE " + TABLA + " SET cantidad_medalla = " + entrenadorCasual.getCantidadMedallas() + " WHERE " + CLAVE
                + " = " + entrenadorCasual.getIdEntrenador() + ";";
        persistencia.update(sql);
    }

    /**
     * Funcion encargada de obtener entrenadorCasual
     * 
     * @param idEntrenador del entrenadorCasual
     * @return entrenadorCasual buscado
     * @throws PersistenciaException con error controlado
     */
    public EntrenadorCasual buscar(int idEntrenador) throws PersistenciaException {
        EntrenadorCasual entrenadorCasual = null;
        String sql = "SELECT * FROM " + TABLA + " WHERE " + CLAVE + " = " + idEntrenador + ";";
        ArrayList<EntrenadorCasual> lista = transformarAEntrenadorCasual(sql);
        if (!lista.isEmpty()) {
            entrenadorCasual = lista.get(0);
        }
        return entrenadorCasual;
    }

    /**
     * Funcion que realiza una consulta sobre una sentencia sql dada
     * 
     * @param sql de la consulta
     * @return lista resultados (0..n) Usuasios
     * @throws PersistenciaException error controlado
     */
    private ArrayList<EntrenadorCasual> transformarAEntrenadorCasual(String sql) throws PersistenciaException {
        ArrayList<EntrenadorCasual> lista = new ArrayList<>();
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Connection connection = null;
        try {
            connection = persistencia.getConnection();
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                EntrenadorCasual entrenadorCasual = new EntrenadorCasual();
                entrenadorCasual.setIdEntrenador(resultSet.getInt(CLAVE));
                entrenadorCasual.setCantidadMedallas(resultSet.getInt("cantidad_medalla"));
                lista.add(entrenadorCasual);
            }
        } catch (SQLException exception) {
            throw new PersistenciaException("Se ha producido un error en la busqueda", exception);
        } finally {
            persistencia.closeConecction(connection, statement, resultSet);
        }
        return lista;
    }
}
