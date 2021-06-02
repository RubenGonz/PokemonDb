package es.iespuertolacruz.pokemon.modelo;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import es.iespuertolacruz.pokemon.excepciones.FicheroException;
import es.iespuertolacruz.pokemon.excepciones.PersistenciaException;

/**
 * Clase que trabaja con la base de datos
 */
public abstract class DdBb {

    // Variables de clase

    protected String nombreTabla;
    protected String clave;
    protected String driver;
    protected String url;
    protected String usuario;
    protected String password;

    // Constructores

    /**
     * Constructor de DdBb con todos los parametros
     * 
     * @param nombreTabla nombre de la tabla a contruir
     * @param driver      de la conxion
     * @param url         de la conxion
     * @param usuario     de la conxion
     * @param password    del usuario
     * @throws PersistenciaException error controlado
     * @throws FicheroException      error controlado
     */
    protected DdBb(String nombreTabla, String driver, String url, String usuario, String password)
            throws PersistenciaException, FicheroException {
        this.nombreTabla = nombreTabla;
        this.driver = driver;
        this.url = url;
        this.usuario = usuario;
        this.password = password;
        inicializarTabla(nombreTabla);
    }

    // Metodos y funciones

    /**
     * Metodo que inicializa una tabla
     * 
     * @param nombreTabla nombre de la tabla a inicializar
     * @throws PersistenciaException error controlado
     * @throws FicheroException      error controlado
     */
    private void inicializarTabla(String nombreTabla) throws PersistenciaException, FicheroException {
        DatabaseMetaData databaseMetaData;
        Connection connection = null;
        ResultSet resultSet = null;
        ArrayList<String> listaTablas = new ArrayList<>();
        try {
            connection = getConnection();
            databaseMetaData = connection.getMetaData();
            resultSet = databaseMetaData.getTables(null, null, null, new String[] { "TABLE" });
            while (resultSet.next()) {
                listaTablas.add(resultSet.getString("TABLE_NAME"));
            }
            if (!listaTablas.contains(nombreTabla)) {
                String crearTabla = new Fichero().leer("resources/sqlite/crear/" + nombreTabla + ".sql");
                update(crearTabla);
                String scriptInserciones = new Fichero().leer("resources/sqlite/insertar/" + nombreTabla + ".sql");
                hacerInserciones(scriptInserciones);
            }
        } catch (Exception e) {
            throw new PersistenciaException("Se ha producido un error en la inicializacion de la BBDD", e);
        } finally {
            closeConecction(connection, null, resultSet);
        }
    }

    /**
     * Metodo que realiza las inserciones en las tablas
     * 
     * @param scriptInserciones script con todas las inserciones
     * @throws PersistenciaException error controlado
     */
    protected void hacerInserciones(String scriptInserciones) throws PersistenciaException {
        String[] insercionesSeparadas = scriptInserciones.split(";");
        for (String insercion : insercionesSeparadas) {
            update(insercion);
        }
    }

    /**
     * Funcion encargada de realizar la conexion con la BBDD
     * 
     * @return la coneccion
     * @throws BbddException controlado
     */
    protected Connection getConnection() throws PersistenciaException {
        Connection connection = null;

        try {
            Class.forName(driver);
            if (usuario == null || password == null) {
                connection = DriverManager.getConnection(url);
            } else {
                DriverManager.getConnection(url, usuario, password);
            }
        } catch (Exception exception) {
            throw new PersistenciaException("No se ha podido establecer la coneccion con la BBDD", exception);
        }
        return connection;
    }

    /**
     * Metodo encargado de realizar la actualizacion de la BBDD
     * 
     * @param sql a ejecutar
     * @throws PersistenciaException error controlado
     */
    protected void update(String sql) throws PersistenciaException {
        Statement statement = null;
        Connection connection = null;
        try {
            connection = getConnection();
            statement = connection.createStatement();
            statement.executeUpdate(sql);
        } catch (Exception e) {
            throw new PersistenciaException("Se ha producido un error realizando la consulta", e);
        } finally {
            closeConecction(connection, statement, null);
        }
    }

    /**
     * Metodo que cierra la conexcion con la base de datos
     * 
     * @param connection
     * @param statement
     * @param resultSet
     */
    protected void closeConecction(Connection connection, Statement statement, ResultSet resultSet)
            throws PersistenciaException {
        try {
            if (resultSet != null) {
                resultSet.close();
            }
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        } catch (Exception exception) {
            throw new PersistenciaException("Se ha producido un error cerrando la conexion", exception);
        }
    }
}