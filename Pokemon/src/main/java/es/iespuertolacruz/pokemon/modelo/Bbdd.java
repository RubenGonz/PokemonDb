package es.iespuertolacruz.pokemon.modelo;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import es.iespuertolacruz.pokemon.api.Pokemon;
import es.iespuertolacruz.pokemon.excepciones.PersistenciaException;

public class Bbdd {

    // Variables de clase

    private String driver;
    private String url;
    private String usuario;
    private String password;

    ArrayList<String> listaTablas;

    // Constructores

    /**
     * 
     * @param driver
     * @param url
     * @param usuario
     * @param password
     * @param listaTablas
     * @throws PersistenciaException
     */
    public Bbdd(String driver, String url, String usuario, String password) throws PersistenciaException {
        this.driver = driver;
        this.url = url;
        this.usuario = usuario;
        this.password = password;
        init();
    }

    private void init() throws PersistenciaException {
        Connection connection = null;
        for (String nombreTabla : listaTablas) {
            boolean existe = existeTabla(nombreTabla);
            if (!existe) {
                // Obtenemos la sentencia sql del fichero
                String crearTabla = "Create table if not exist fruta (identificador INT PRIMARY KEY,nombre VARCHAR(30),precio float(3,2), coste float(3,2));";
                actualizar(crearTabla);
                // Para cada uno de los insert de nombreTabla
                String insertElemento = "";
                actualizar(insertElemento);
            }
        }
    }

    /**
     * Funcion encargada de verificar si una tabla existe
     * 
     * @param nombreTabla a verificar
     * @return true si existe o false si no existe
     * @throws PersistenciaException
     */
    private boolean existeTabla(String nombreTabla) throws PersistenciaException {
        Connection connection = null;
        boolean existe = false;
        ResultSet resultSet = null;

        try {
            connection = getConnection();
            DatabaseMetaData meta = connection.getMetaData();
            resultSet = meta.getTables(null, null, nombreTabla, new String[] { "TABLE" });
            existe = resultSet.next();
        } catch (Exception e) {
            // TODO: handle exception
        } finally {
            closeConecction(connection, null, resultSet);
        }
        return existe;

    }

    /**
     * Funcion encargada de realizar la conexion con la BBDD
     * 
     * @return la coneccion
     * @throws PersistenciaException controlado
     */
    private Connection getConnection() throws PersistenciaException {
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
     * Metodo encargado de realizar la insercion de un pokemon
     * 
     * @param pokemon a insertar
     * @throws PersistenciaException error controlado
     */
    public void insertar(Pokemon pokemon) throws PersistenciaException {
        String sql = "INSERT INTO POKEMON VALUES (" + pokemon.getNumeroPokedex() + ",'" + pokemon.getNombre() + "',"
                + pokemon.getCaracteristicas() + "," + pokemon.getEstadisticasBase() + ");";
        actualizar(sql);
    }

    /**
     * Metodo encargado de realizar la eliminacion
     * 
     * @param pokemon a eliminar
     * @throws PersistenciaException error controlado
     */
    public void eliminar(Pokemon pokemon) throws PersistenciaException {
        String sql = "DELETE POKEMON FROM POKEMON WHERE numero_pokedex = " + 
        pokemon.getNumeroPokedex() + ";";
        actualizar(sql);
    }

    /**
     * Metodo encargado de realizar la modificacion
     * 
     * @param pokemon a actualizar
     * @throws PersistenciaException error controlado
     */
    public void modificar(Pokemon pokemon) throws PersistenciaException {
        String sql = "UPDATE POKEMON SET " +
        "nombre = '" + pokemon.getNombre() + "', " + 
        "id_caracteristica = " + pokemon.getCaracteristicas() + ", " +
        "id_estadisticas_base = " + pokemon.getEstadisticasBase() + 
        " WHERE identificador = " + pokemon.getNumeroPokedex() + ";";
        actualizar(sql);
    }

    /**
     * Metodo encargado de realizar la actualizacion de la BBDD
     * 
     * @param sql a ejecutar
     * @throws PersistenciaException error controlado
     */
    private void actualizar(String sql) throws PersistenciaException {
        Statement statement = null;
        Connection connection = null;
        try {
            connection = getConnection();
            statement = connection.createStatement();
            statement.executeUpdate(sql);
        } catch (Exception exception) {
            throw new PersistenciaException("Se ha producido un error realizando la consulta", exception);
        } finally {
            closeConecction(connection, statement, null);
        }
    }

    /**
     * Funcion que realiza la consulta sobre la BBDD y la tabla Pokemon
     * 
     * @param sql de la consulta
     * @return lista de resultados
     * @throws PersistenciaException controlado
     */
    private ArrayList<Pokemon> obtenerListado(String sql) throws PersistenciaException {
        ArrayList<Pokemon> listaPokemons = new ArrayList<>();

        Pokemon pokemon = null;
        Statement statement = null;
        ResultSet resultSet = null;
        Connection connection = null;
        try {
            connection = getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                int numeroPokedex = resultSet.getInt("numero_pokedex");
                String nombre = resultSet.getString("nombre");
                int caracteristicas = resultSet.getInt("id_caracteristica");
                int estadisticasBase = resultSet.getInt("id_estadisticas_base");
                pokemon = new Pokemon(numeroPokedex, nombre, caracteristicas, estadisticasBase);
                listaPokemons.add(pokemon);
            }
        } catch (Exception exception) {
            throw new PersistenciaException("Se ha producido un error realizando la consulta", exception);
        } finally {
            closeConecction(connection, statement, resultSet);
        }
        return listaPokemons;
    }

    /**
     * Funcion que obtiene el listado de todos los pokemon
     * 
     * @return lisa total
     * @throws PersistenciaException controlado
     */
    public List<Pokemon> obtenerListado() throws PersistenciaException {
        String sql = "SELECT * FROM POKEMON";
        return obtenerListado(sql);
    }

    /**
     * Funcion que obtiene un pokemon por su numero de la pokedex
     * 
     * @param numero de la pokedex del pokemon
     * @return lista total
     * @throws PersistenciaException controlado
     */
    public Pokemon obtenerPokemon(int numeroPokedex) throws PersistenciaException {
        Pokemon pokemon = null;
        ArrayList<Pokemon> listaPokemons = null;
        String sql = "SELECT * FROM POKEMON WHERE numero_pokedex = ";
        sql = sql + "'" + numeroPokedex + "';";
        listaPokemons = obtenerListado(sql);
        if (!listaPokemons.isEmpty()) {
            pokemon = listaPokemons.get(0);
        }
        return pokemon;
    }

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
        } catch (Exception exception) {
            throw new PersistenciaException("Se ha producido un error cerrando la coneccion", exception);
        }
    }
}
