package es.iespuertolacruz.pokemon.modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import es.iespuertolacruz.pokemon.api.Pertenece;
import es.iespuertolacruz.pokemon.excepciones.FicheroException;
import es.iespuertolacruz.pokemon.excepciones.PersistenciaException;

public class PerteneceModelo {

    // Variables de clase

    DdBbSqLite persistencia;
    private static final String TABLA = "PERTENECE";
    private static final String CLAVEPRI = "numero_pokedex";
    private static final String CLAVESEC = "tipo";

    // Constructores

    /**
     * Constructor de PerteneceModelo donde inicializa DdBbSqLite
     * 
     * @throws PersistenciaException con error controlado
     * @throws FicheroException      con error controlado
     */
    public PerteneceModelo() throws PersistenciaException, FicheroException {
        persistencia = new DdBbSqLite(TABLA, null, null);
    }

    // Metodos y funciones

    /**
     * Metodo que inserta pertenece en la base de datos
     * 
     * @param pertenece a insertar
     * @throws PersistenciaException error controlado
     */
    public void insertar(Pertenece pertenece) throws PersistenciaException {
        String sql = "INSERT INTO " + TABLA + " VALUES (" + pertenece.getNumeroPokedex() + ",'" + pertenece.getTipo()
                + "');";
        persistencia.update(sql);
    }

    /***
     * Metodo que elimina pertenece de la base de datos
     * 
     * @param pertenece a eliminar
     * @throws PersistenciaException error controlado
     */
    public void eliminar(Pertenece pertenece) throws PersistenciaException {
        String sql = "DELETE FROM " + TABLA + " WHERE " + CLAVEPRI + " = " + pertenece.getNumeroPokedex() + " AND "
                + CLAVESEC + " = '" + pertenece.getTipo() + "';";
        persistencia.update(sql);
    }

    /**
     * Metodo que modifica pertenece de la base de datos
     * 
     * @param pertenece a modificar
     * @throws PersistenciaException error controlado
     */
    public void modificar(Pertenece pertenece) throws PersistenciaException {
        String sql = "UPDATE " + TABLA + " SET " + CLAVEPRI + " = " + pertenece.getNumeroPokedex() + ", " + CLAVESEC
                + " = '" + pertenece.getTipo() + "' WHERE " + CLAVEPRI + " = " + pertenece.getNumeroPokedex() + " AND " + CLAVESEC + " = '" + pertenece.getTipo() + "';";
        persistencia.update(sql);
    }

    /**
     * Funcion encargada de obtener pertenece
     * 
     * @param numeroPokedex del pertenece
     * @param tipo del pertenece
     * @return pertenece buscado
     * @throws PersistenciaException con error controlado
     */
    public Pertenece buscar(int numeroPokedex, String tipo) throws PersistenciaException {
        Pertenece pertenece = null;
        String sql = "SELECT * FROM " + TABLA + " WHERE " + CLAVEPRI + " = " + numeroPokedex + " AND " + CLAVESEC + " = '" + tipo + "';";
        ArrayList<Pertenece> lista = transformarpertenece(sql);
        if (!lista.isEmpty()) {
            pertenece = lista.get(0);
        }
        return pertenece;
    }

    /**
     * Funcion que realiza una consulta sobre una sentencia sql dada
     * 
     * @param sql de la consulta
     * @return lista resultados (0..n) Usuasios
     * @throws PersistenciaException error controlado
     */
    private ArrayList<Pertenece> transformarpertenece(String sql) throws PersistenciaException {
        ArrayList<Pertenece> lista = new ArrayList<>();
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Connection connection = null;
        try {
            connection = persistencia.getConnection();
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Pertenece pertenece = new Pertenece();
                pertenece.setNumeroPokedex(resultSet.getInt(CLAVEPRI));
                pertenece.setTipo(resultSet.getString(CLAVESEC));
                lista.add(pertenece);
            }
        } catch (SQLException exception) {
            throw new PersistenciaException("Se ha producido un error en la busqueda", exception);
        } finally {
            persistencia.closeConecction(connection, statement, resultSet);
        }
        return lista;
    }
}
