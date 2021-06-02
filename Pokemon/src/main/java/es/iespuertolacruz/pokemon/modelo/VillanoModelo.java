package es.iespuertolacruz.pokemon.modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import es.iespuertolacruz.pokemon.api.Villano;
import es.iespuertolacruz.pokemon.excepciones.FicheroException;
import es.iespuertolacruz.pokemon.excepciones.PersistenciaException;

/**
 * Clase principal del modelo de villano
 */
public class VillanoModelo {

    // Variables de clase

    DdBbSqLite persistencia;
    private static final String TABLA = "VILLANO";
    private static final String CLAVE = "id_entrenador";

    // Constructores

    /**
     * Constructor de VillanoModelo donde inicializa DdBbSqLite
     * 
     * @throws PersistenciaException con error controlado
     * @throws FicheroException      con error controlado
     */
    public VillanoModelo() throws PersistenciaException, FicheroException {
        persistencia = new DdBbSqLite(TABLA, null, null);
    }

    // Metodos y funciones

    /**
     * Metodo que inserta villano en la base de datos
     * 
     * @param villano a insertar
     * @throws PersistenciaException error controlado
     */
    public void insertar(Villano villano) throws PersistenciaException {
        String sql = "INSERT INTO " + TABLA + " VALUES (" + villano.getIdEntrenador() + ",'" + villano.getProposito()
                + "');";
        persistencia.update(sql);
    }

    /***
     * Metodo que elimina villano de la base de datos
     * 
     * @param villano a eliminar
     * @throws PersistenciaException error controlado
     */
    public void eliminar(Villano villano) throws PersistenciaException {
        String sql = "DELETE FROM " + TABLA + " WHERE " + CLAVE + " = " + villano.getIdEntrenador() + ";";
        persistencia.update(sql);
    }

    /**
     * Metodo que modifica villano de la base de datos
     * 
     * @param villano a modificar
     * @throws PersistenciaException error controlado
     */
    public void modificar(Villano villano) throws PersistenciaException {
        String sql = "UPDATE " + TABLA + " SET proposito = '" + villano.getProposito() + "' WHERE " + CLAVE + " = "
                + villano.getIdEntrenador() + ";";
        persistencia.update(sql);
    }

    /**
     * Funcion encargada de obtener villano
     * 
     * @param idEntrenador del villano
     * @return villano buscado
     * @throws PersistenciaException con error controlado
     */
    public Villano buscar(int idEntrenador) throws PersistenciaException {
        Villano villano = null;
        String sql = "SELECT * FROM " + TABLA + " WHERE " + CLAVE + " = " + idEntrenador + ";";
        ArrayList<Villano> lista = transformarAVillano(sql);
        if (!lista.isEmpty()) {
            villano = lista.get(0);
        }
        return villano;
    }

    /**
     * Funcion que realiza una consulta sobre una sentencia sql dada
     * 
     * @param sql de la consulta
     * @return lista resultados (0..n) Usuasios
     * @throws PersistenciaException error controlado
     */
    private ArrayList<Villano> transformarAVillano(String sql) throws PersistenciaException {
        ArrayList<Villano> lista = new ArrayList<>();
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Connection connection = null;
        try {
            connection = persistencia.getConnection();
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Villano villano = new Villano();
                villano.setIdEntrenador(resultSet.getInt(CLAVE));
                villano.setProposito(resultSet.getString("proposito"));
                lista.add(villano);
            }
        } catch (SQLException exception) {
            throw new PersistenciaException("Se ha producido un error en la busqueda", exception);
        } finally {
            persistencia.closeConecction(connection, statement, resultSet);
        }
        return lista;
    }
}
