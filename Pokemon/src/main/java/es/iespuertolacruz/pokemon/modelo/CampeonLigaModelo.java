package es.iespuertolacruz.pokemon.modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import es.iespuertolacruz.pokemon.api.CampeonLiga;
import es.iespuertolacruz.pokemon.excepciones.FicheroException;
import es.iespuertolacruz.pokemon.excepciones.PersistenciaException;

/**
 * Clase modelo de CampeonLigaModelo
 */
public class CampeonLigaModelo {

    // Variables de clase

    DdBbSqLite persistencia;
    private static final String TABLA = "CAMPEON_LIGA";
    private static final String CLAVE = "id_entrenador";

    // Constructores

    /**
     * Constructor de AltoMandoModelo donde inicializa DdBbSqLite
     * 
     * @throws PersistenciaException con error controlado
     * @throws FicheroException      con error controlado
     */
    public CampeonLigaModelo() throws PersistenciaException, FicheroException {
        persistencia = new DdBbSqLite(TABLA, null, null);
    }

    // Metodos y funciones

    /**
     * Metodo que inserta CampeonLiga en la base de datos
     * 
     * @param campeonLiga a insertar
     * @throws PersistenciaException con error controlado
     */
    public void insertar(CampeonLiga campeonLiga) throws PersistenciaException {
        String sql = "INSERT INTO " + TABLA + " VALUES (" + campeonLiga.getIdEntrenador() + ",'"
                + campeonLiga.getRegion() + "');";
        persistencia.update(sql);
    }

    /***
     * Metodo que elimina campeonLiga de la base de datos
     * 
     * @param campeonLiga a eliminar
     * @throws PersistenciaException con error controlado
     */
    public void eliminar(CampeonLiga campeonLiga) throws PersistenciaException {
        String sql = "DELETE FROM " + TABLA + " WHERE " + CLAVE + " = " + campeonLiga.getIdEntrenador() + ";";
        persistencia.update(sql);
    }

    /**
     * Metodo que modifica campeonLiga de la base de datos
     * 
     * @param campeonLiga a modificar
     * @throws PersistenciaException error controlado
     */
    public void modificar(CampeonLiga campeonLiga) throws PersistenciaException {
        String sql = "UPDATE " + TABLA + " SET region = '" + campeonLiga.getRegion() + "' WHERE " + CLAVE + " = "
                + campeonLiga.getIdEntrenador() + ";";
        persistencia.update(sql);
    }

    /**
     * Funcion encargada de obtener campeonLiga
     * 
     * @param idEntrenador del altoMando
     * @return altoMando buscado
     * @throws PersistenciaException con error controlado
     */
    public CampeonLiga buscar(int idEntrenador) throws PersistenciaException {
        CampeonLiga campeonLiga = null;
        String sql = "SELECT * FROM " + TABLA + " WHERE " + CLAVE + " = " + idEntrenador + ";";
        ArrayList<CampeonLiga> lista = transformarACampeonLiga(sql);
        if (!lista.isEmpty()) {
            campeonLiga = lista.get(0);
        }
        return campeonLiga;
    }

    /**
     * Funcion que realiza una consulta sobre una sentencia sql dada
     * 
     * @param sql de la consulta
     * @return lista resultados (0..n) Usuasios
     * @throws PersistenciaException error controlado
     */
    private ArrayList<CampeonLiga> transformarACampeonLiga(String sql) throws PersistenciaException {
        ArrayList<CampeonLiga> lista = new ArrayList<>();
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Connection connection = null;
        try {
            connection = persistencia.getConnection();
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                CampeonLiga campeonLiga = new CampeonLiga();
                campeonLiga.setIdEntrenador(resultSet.getInt(CLAVE));
                campeonLiga.setRegion(resultSet.getString("region"));
                lista.add(campeonLiga);
            }
        } catch (SQLException exception) {
            throw new PersistenciaException("Se ha producido un error en la busqueda", exception);
        } finally {
            persistencia.closeConecction(connection, statement, resultSet);
        }
        return lista;
    }

}
