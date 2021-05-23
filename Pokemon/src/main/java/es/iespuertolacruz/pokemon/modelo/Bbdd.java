package es.iespuertolacruz.pokemon.modelo;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import es.iespuertolacruz.pokemon.api.Tipo;
import es.iespuertolacruz.pokemon.excepciones.PersistenciaException;

public class Bbdd {

    // Variables de clase

    private static final String TABLE = "TABLE";
    private static final String TABLE_NAME = "TIPO";

    protected String driver;
    protected String urlConexion;
    protected String usuario;
    protected String password;

    // Constructores

    public Bbdd(String driver, String urlConexion, String usuario, String password) throws PersistenciaException {
        this.driver = driver;
        this.urlConexion = urlConexion;
        this.usuario = usuario;
        this.password = password;
        inicializarDdBd();
    }

    // Metodos de la clase

    private void inicializarDdBd() throws PersistenciaException {
        DatabaseMetaData databaseMetaData;
        Connection connection = null;
        ResultSet resultSet = null;
        ArrayList<String> listaTablas = new ArrayList<>();
        try {
            connection = getConnection();
            databaseMetaData = connection.getMetaData();
            resultSet = databaseMetaData.getTables(null, null, null, new String[] { TABLE });
            while (resultSet.next()) {
                listaTablas.add(resultSet.getString("TABLE_NAME"));
            }
            if (!listaTablas.contains(TABLE_NAME)) {
                String sqlCrearTabla = "CREATE TABLE IF NOT EXISTS TIPO( " + "nombre VARCHAR (15) CHECK (nombre IN "
                        + "('Agua', 'Bicho', 'Dragon', 'Electrico', 'Fantasma', "
                        + "'Fuego', 'Hielo', 'Lucha', 'Normal', 'Planta', "
                        + "'Psiquico', 'Roca', 'Tierra', 'Veneno', 'Pajaro')), " + "color VARCHAR (20), "
                        + "PRIMARY KEY (nombre));";
                update(sqlCrearTabla);
            }
        } catch (Exception e) {
            throw new PersistenciaException("Se ha producido un error en la inicializacion de la BBDD", e);
        } finally {
            closeConecction(connection, null, resultSet);
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
     * Funcion encargada de obtener un tipo
     * 
     * @param nombre del tipo
     * @return Objeto Tipo
     * @throws PersistenciaException
     */
    public Tipo buscarTipo(String nombre) throws PersistenciaException {
        Tipo tipo = null;
        String sql = "SELECT * FROM " + TABLE_NAME + " WHERE nombre = '" + nombre + "';";
        ArrayList<Tipo> lista = buscar(sql);
        if (!lista.isEmpty()) {
            tipo = lista.get(0);
        }
        return tipo;
    }

    /**
     * Funcion que obtiene todos los tipos de la BBDD
     * 
     * @return lista de tipos
     * @throws PersistenciaException error controlado
     */
    public ArrayList<Tipo> buscarTodos() throws PersistenciaException {
        String sql = "SELECT * FROM " + TABLE_NAME + ";";
        return buscar(sql);
    }

    /**
     * Funcion que realiza una consulta sobre una sentencia sql dada
     * 
     * @param sql de la consulta
     * @return lista resultados (0..n) Tipos
     * @throws PersistenciaException error controlado
     */
    private ArrayList<Tipo> buscar(String sql) throws PersistenciaException {
        ArrayList<Tipo> lista = new ArrayList<>();
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Connection connection = null;
        try {
            connection = getConnection();
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Tipo tipo = new Tipo();
                tipo.setNombre(resultSet.getString("nombre"));
                tipo.setColor(resultSet.getString("color"));
                lista.add(tipo);
            }
        } catch (SQLException exception) {
            throw new PersistenciaException("Se ha producido un error en la busqueda", exception);
        } finally {
            closeConecction(connection, statement, resultSet);
        }
        return lista;
    }

    /**
     * Metodo encargado de realizar la insercion en la BBDD
     * 
     * @param tipo a insertar
     * @throws PersistenciaException
     */
    public void insertar(Tipo tipo) throws PersistenciaException {
        String sql = "INSERT INTO TIPO VALUES ('" + tipo.getNombre() + "','" + tipo.getColor() + "');";
        update(sql);
    }

    /**
     * Metodo encargado de realizar la actualizacion de un tipo
     * 
     * @param tipo a actualizar
     * @throws PersistenciaException error controlado
     */
    public void update(Tipo tipo) throws PersistenciaException {
        String sql = "UPDATE TIPO SET color = '" + tipo.getColor() + 
        "' WHERE nombre = '" + tipo.getNombre() + "';";
        update(sql);
    }

    /**
     * Metodo encargado de realizar la eliminacion de un tipo
     * 
     * @param tipo a eliminar
     * @throws PersistenciaException
     */
    public void eliminar(String nombre) throws PersistenciaException {
        String sql = "DELETE FROM TIPO WHERE nombre = '" + nombre + "';";
        update(sql);
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
    private void closeConecction(Connection connection, Statement statement, ResultSet resultSet)
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