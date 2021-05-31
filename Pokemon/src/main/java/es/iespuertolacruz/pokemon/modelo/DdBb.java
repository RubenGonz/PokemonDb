package es.iespuertolacruz.pokemon.modelo;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import es.iespuertolacruz.pokemon.excepciones.PersistenciaException;

public abstract class DdBb {

    // Variables de clase

    protected String nombreTabla;
    protected String clave;
    protected String driver;
    protected String urlConexion;
    protected String usuario;
    protected String password;

    // Constructor

    /**
     * Constructor con los parametros pasados por la clase DdBbSqlite
     * 
     * @param nombreTabla nombre de la tabla
     * @param driver de la conexion
     * @param urlConexion de la conexion
     * @param usuario de la base de datos
     * @param password contrasenia del usario
     * @throws PersistenciaException con error controlado
     */
    protected DdBb(String nombreTabla, String driver, String urlConexion, String usuario, String password) throws PersistenciaException {
        this.nombreTabla = nombreTabla;
        this.driver = driver;
        this.urlConexion = urlConexion;
        this.usuario = usuario;
        this.password = password;
        inicializarTabla(nombreTabla);
    }

    /**
     * Metodo que crea la tabla en la base de datos
     * 
     * @param nombreTabla nombre de la tabla a crear
     * @param sqlCreate sql de la creacion de la tabla
     * @throws PersistenciaException con error controlado
     */
    private void inicializarTabla(String nombreTabla) throws PersistenciaException {
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
                String estructuraTabla = new Fichero().leer("resources/sqlite/crear/" + nombreTabla + ".sql");
                update(estructuraTabla);/** 
                String scriptTabla = new Fichero().leer("resources/sqlite/insertar/" + nombreTabla + ".sql");
                hacerInserciones(scriptTabla);*/
            }
        } catch (Exception e) {
            throw new PersistenciaException("Se ha producido un error en la inicializacion de la tabla", e);
        } finally {
            closeConecction(connection, null, resultSet);
        }
    }

    /**
     * Metodo que separa las inserciones del script y las aniade una a una
     * 
     * @param scriptTabla script de la tabla
     * @throws PersistenciaException error controlado
     */
    protected void hacerInserciones(String scriptTabla) throws PersistenciaException {
        String[] insercionesSeparadas = scriptTabla.split(";");
        for (String insercion : insercionesSeparadas) {
            update(insercion);
        }
    }

    /**
     * Funcion encargada de realizar la conexion con la BBDD
     * 
     * @return conexion abierta
     * @throws PersistenciaException
     */
    public Connection getConnection() throws PersistenciaException {
        Connection connection = null;

        try {
            Class.forName(driver);
            if (usuario != null && password != null) {
                connection = DriverManager.getConnection(urlConexion, usuario, password);
            } else {
                connection = DriverManager.getConnection(urlConexion);
            }
        } catch (ClassNotFoundException | SQLException exception) {
            throw new PersistenciaException("No se ha podido estabalecer la conexion", exception);
        }
        return connection;
    }

    /**
     * Metodo encargado de realizar las inserciones/modificaciones/eliminacion de la
     * BBDD
     * 
     * @param sql con la sentencia
     * @throws PersistenciaException error controlado
     */
    public void update(String sql) throws PersistenciaException {
        PreparedStatement statement = null;
        Connection connection = null;
        try {
            connection = getConnection();
            statement = connection.prepareStatement(sql);
            statement.executeUpdate();
        } catch (SQLException exception) {
            throw new PersistenciaException("Se ha producido un error en la busqueda", exception);
        } finally {
            closeConecction(connection, statement, null);
        }
    }

    /**
     * Metodo encargado de realizar el cierre de la conexion con la BBDD
     * 
     * @param connection contra la BBDD
     * @param statement  de la operacion
     * @param resultSet  resultado
     * @throws PersistenciaException error controlado
     */
    public void closeConecction(Connection connection, Statement statement, ResultSet resultSet)
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
        } catch (Exception e) {
            throw new PersistenciaException("Se ha producido un error cerrando la sesion con la BBDD");
        }
    }
}
