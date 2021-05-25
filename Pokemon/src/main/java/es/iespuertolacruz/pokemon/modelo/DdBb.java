package es.iespuertolacruz.pokemon.modelo;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import es.iespuertolacruz.pokemon.api.Caracteristicas;
import es.iespuertolacruz.pokemon.api.Pokemon;
import es.iespuertolacruz.pokemon.api.Tipo;
import es.iespuertolacruz.pokemon.excepciones.PersistenciaException;

public abstract class DdBb {

    // Variables de clase

    /**
     *
     */
    private static final String SE_HA_PRODUCIDO_UN_ERROR_EN_LA_BUSQUEDA = "Se ha producido un error en la busqueda";
    private static final String CARACTERISTICAS = "CARACTERISTICAS";
    private static final String ESTADISTICAS_BASE = "ESTADISTICAS_BASE";
    private static final String POKEMON = "POKEMON";
    private static final String EVOLUCIONA = "EVOLUCIONA";
    private static final String TIPO = "TIPO";
    private static final String PERTENECE = "PERTENECE";
    private static final String ESTADO = "ESTADO";
    private static final String MOVIMIENTO = "MOVIMIENTO";
    private static final String PROVOCA = "PROVOCA";
    private static final String CONOCE = "CONOCE";
    private static final String OBJETO = "OBJETO";
    private static final String POKEBALL = "POKEBALL";
    private static final String OBJETO_COMUN = "OBJETO_COMUN";
    private static final String MAQUINA = "MAQUINA";
    private static final String POKEMON_EQUIPA = "POKEMON_EQUIPA";
    private static final String ENTRENADOR = "ENTRENADOR";
    private static final String VILLANO = "VILLANO";
    private static final String LIDER_GIMNASIO = "LIDER_GIMNASIO";
    private static final String ENTRENADOR_CASUAL = "ENTRENADOR_CASUAL";
    private static final String CAMPEON_LIGA = "CAMPEON_LIGA";
    private static final String ALTO_MANDO = "ALTO_MANDO";
    private static final String ENTRENADOR_EQUIPA = "ENTRENADOR_EQUIPA";
    private static final String TIENE = "TIENE";

    protected String driver;
    protected String urlConexion;
    protected String usuario;
    protected String password;

    // Constructor

    protected DdBb(String driver, String urlConexion, String usuario, String password) throws PersistenciaException {
        this.driver = driver;
        this.urlConexion = urlConexion;
        this.usuario = usuario;
        this.password = password;
        inicializarDdBd();
    }

    private void inicializarDdBd() throws PersistenciaException {
        DatabaseMetaData databaseMetaData;
        Connection connection = null;
        ResultSet resultSet = null;
        ArrayList<String> listaTablas = new ArrayList<>();
        try {
            connection = getConnection();
            databaseMetaData = connection.getMetaData();
            resultSet = databaseMetaData.getTables(null, null, null,
                    new String[] { CARACTERISTICAS, ESTADISTICAS_BASE, POKEMON, EVOLUCIONA, TIPO, PERTENECE, ESTADO,
                            MOVIMIENTO, PROVOCA, CONOCE, OBJETO, POKEBALL, OBJETO_COMUN, MAQUINA, POKEMON_EQUIPA,
                            ENTRENADOR, VILLANO, LIDER_GIMNASIO, ENTRENADOR_CASUAL, CAMPEON_LIGA, ALTO_MANDO,
                            ENTRENADOR_EQUIPA, TIENE });
            while (resultSet.next()) {
                listaTablas.add(resultSet.getString(CARACTERISTICAS));
                listaTablas.add(resultSet.getString(ESTADISTICAS_BASE));
                listaTablas.add(resultSet.getString(POKEMON));
                listaTablas.add(resultSet.getString(EVOLUCIONA));
                listaTablas.add(resultSet.getString(TIPO));
                listaTablas.add(resultSet.getString(PERTENECE));
                listaTablas.add(resultSet.getString(ESTADO));
                listaTablas.add(resultSet.getString(MOVIMIENTO));
                listaTablas.add(resultSet.getString(PROVOCA));
                listaTablas.add(resultSet.getString(CONOCE));
                listaTablas.add(resultSet.getString(OBJETO));
                listaTablas.add(resultSet.getString(POKEBALL));
                listaTablas.add(resultSet.getString(OBJETO_COMUN));
                listaTablas.add(resultSet.getString(MAQUINA));
                listaTablas.add(resultSet.getString(POKEMON_EQUIPA));
                listaTablas.add(resultSet.getString(ENTRENADOR));
                listaTablas.add(resultSet.getString(VILLANO));
                listaTablas.add(resultSet.getString(LIDER_GIMNASIO));
                listaTablas.add(resultSet.getString(ENTRENADOR_CASUAL));
                listaTablas.add(resultSet.getString(CAMPEON_LIGA));
                listaTablas.add(resultSet.getString(ALTO_MANDO));
                listaTablas.add(resultSet.getString(ENTRENADOR_EQUIPA));
                listaTablas.add(resultSet.getString(TIENE));
            }
            if (!listaTablas.contains(CARACTERISTICAS) && !listaTablas.contains(ESTADISTICAS_BASE)
                    && !listaTablas.contains(POKEMON) && !listaTablas.contains(EVOLUCIONA)
                    && !listaTablas.contains(TIPO) && !listaTablas.contains(PERTENECE) && !listaTablas.contains(ESTADO)
                    && !listaTablas.contains(MOVIMIENTO) && !listaTablas.contains(PROVOCA)
                    && !listaTablas.contains(CONOCE) && !listaTablas.contains(OBJETO) && !listaTablas.contains(POKEBALL)
                    && !listaTablas.contains(OBJETO_COMUN) && !listaTablas.contains(MAQUINA)
                    && !listaTablas.contains(POKEMON_EQUIPA) && !listaTablas.contains(ENTRENADOR)
                    && !listaTablas.contains(VILLANO) && !listaTablas.contains(LIDER_GIMNASIO)
                    && !listaTablas.contains(ENTRENADOR_CASUAL) && !listaTablas.contains(CAMPEON_LIGA)
                    && !listaTablas.contains(ALTO_MANDO) && !listaTablas.contains(ENTRENADOR_EQUIPA)
                    && !listaTablas.contains(TIENE)) {
                
                String sqlCrearTablaEstadisticasBase = "CREATE TABLE IF NOT EXISTS CARACTERISTICAS (" +
                    "id_caracteristica INT CHECK (id_caracteristica > 0), " +
                    "peso FLOAT CHECK (peso > 0), " +
                    "altura FLOAT CHECK (altura > 0), " +
                    "especie VARCHAR (20), " +
                    "habilidad VARCHAR (20), " +
                    "categoria VARCHAR (20) CHECK (categoria IN ('Normal', 'Starter', 'Semi-legendario', 'Legendario')), " +
                    "PRIMARY KEY (id_caracteristica));";
                String sqlCrearTablaCaracteristicas = "CREATE TABLE IF NOT EXISTS ESTADISTICAS_BASE ( " +
                    "id_estadisticas_base INT CHECK (id_estadisticas_base > 0), " +
                    "ps_base INT CHECK (ps_base > 0), " +
                    "ataque_base INT CHECK (ataque_base > 0), " +
                    "defensa_base INT CHECK (defensa_base > 0), " +
                    "ataque_especial_base INT CHECK (ataque_especial_base > 0), " +
                    "defensa_especial_base INT CHECK (defensa_especial_base > 0), " +
                    "velocidad_base INT CHECK (velocidad_base > 0), " +
                    "PRIMARY KEY (id_estadisticas_base));";
                String sqlCrearTablaPokemon = "CREATE TABLE IF NOT EXISTS POKEMON ( " +
                    "numero_pokedex INT CHECK (numero_pokedex > 0), " +
                    "nombre VARCHAR (15), " +
                    "id_caracteristica INT CHECK (id_caracteristica > 0), " +
                    "id_estadisticas_base INT CHECK (id_estadisticas_base > 0), " +
                    "PRIMARY KEY (numero_pokedex), " +
                    "FOREIGN KEY (id_caracteristica) REFERENCES CARACTERISTICAS (id_caracteristica), " +
                    "FOREIGN KEY (id_estadisticas_base) REFERENCES ESTADISTICAS_BASE (id_estadisticas_base));";
                String sqlCrearTablaTipo = "CREATE TABLE IF NOT EXISTS TIPO( " + "nombre VARCHAR (15) CHECK (nombre IN " +
                    "('Agua', 'Bicho', 'Dragon', 'Electrico', 'Fantasma', " +
                    "'Fuego', 'Hielo', 'Lucha', 'Normal', 'Planta', " +
                    "'Psiquico', 'Roca', 'Tierra', 'Veneno', 'Pajaro')), " + "color VARCHAR (20), " +
                    "PRIMARY KEY (nombre));";
                update(sqlCrearTablaEstadisticasBase);
                update(sqlCrearTablaCaracteristicas);
                update(sqlCrearTablaPokemon);
                update(sqlCrearTablaTipo);
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
     * Funcion encargada de obtener una caracteristica
     * 
     * @param id_caracteristica del pokemon
     * @return Pokemon buscado
     * @throws PersistenciaException con error controlado
     */
    public Object buscarCaracteristicaPorId(int idCaracteristica) throws PersistenciaException {
        Object elemento = null;
        String sql = "SELECT * FROM CARACTERISTICAS WHERE id_caracteristica = " + idCaracteristica + ";";
        ArrayList<Caracteristicas> lista = buscarCaracteristica(sql);
        if (!lista.isEmpty()) {
            elemento = lista.get(0);
        }
        return elemento;
    }

    /**
     * Funcion encargada de obtener un pokemon
     * 
     * @param numeroPokedex del pokemon
     * @return Pokemon buscado
     * @throws PersistenciaException con error controlado
     */
    public Object buscarPokemonPorNumeroPokedex(int numeroPokedex) throws PersistenciaException {
        Object elemento = null;
        String sql = "SELECT * FROM POKEMON WHERE numero_pokedex = " + numeroPokedex + ";";
        ArrayList<Pokemon> lista = buscarPokemon(sql);
        if (!lista.isEmpty()) {
            elemento = lista.get(0);
        }
        return elemento;
    }

    /**
     * Funcion encargada de obtener un tipo
     * 
     * @param nombre del tipo
     * @return Tipo buscado
     * @throws PersistenciaException con error controlado
     */
    public Object buscarTipoPorNombre(String nombre) throws PersistenciaException {
        Object elemento = null;
        String sql = "SELECT * FROM TIPO WHERE nombre = '" + nombre + "';";
        ArrayList<Tipo> lista = buscarTipo(sql);
        if (!lista.isEmpty()) {
            elemento = lista.get(0);
        }
        return elemento;
    }

    /**
     * Funcion que realiza una consulta sobre una sentencia sql dada
     * 
     * @param sql de la consulta
     * @return lista resultados (0..n) Usuasios
     * @throws PersistenciaException error controlado
     */
    private ArrayList<Caracteristicas> buscarCaracteristica(String sql) throws PersistenciaException {
        ArrayList<Caracteristicas> lista = new ArrayList<>();
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Connection connection = null;
        try {
            connection = getConnection();
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Caracteristicas caracteristicas = new Caracteristicas();
                caracteristicas.setId(resultSet.getInt("id_caracteristica"));
                caracteristicas.setPeso(resultSet.getFloat("peso"));
                caracteristicas.setAltura(resultSet.getFloat("altura"));
                caracteristicas.setEspecie(resultSet.getString("especie"));               
                caracteristicas.setHabilidad(resultSet.getString("habilidad"));
                caracteristicas.setCategoria(resultSet.getString("categoria"));           
                lista.add(caracteristicas);
            }
        } catch (SQLException exception) {
            throw new PersistenciaException(SE_HA_PRODUCIDO_UN_ERROR_EN_LA_BUSQUEDA, exception);
        } finally {
            closeConecction(connection, statement, resultSet);
        }
        return lista;
    }

    /**
     * Funcion que realiza una consulta sobre una sentencia sql dada
     * 
     * @param sql de la consulta
     * @return lista resultados (0..n) Usuasios
     * @throws PersistenciaException error controlado
     */
    private ArrayList<Pokemon> buscarPokemon(String sql) throws PersistenciaException {
        ArrayList<Pokemon> lista = new ArrayList<>();
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Connection connection = null;
        try {
            connection = getConnection();
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Pokemon pokemon = new Pokemon();
                pokemon.setNumeroPokedex(resultSet.getInt("numero_pokedex"));
                pokemon.setNombre(resultSet.getString("nombre"));
                pokemon.setCaracteristicas(resultSet.getInt("id_caracteristica"));
                pokemon.setEstadisticasBase(resultSet.getInt("id_estadisticas_base"));
                lista.add(pokemon);
            }
        } catch (SQLException exception) {
            throw new PersistenciaException(SE_HA_PRODUCIDO_UN_ERROR_EN_LA_BUSQUEDA, exception);
        } finally {
            closeConecction(connection, statement, resultSet);
        }
        return lista;
    }

    /**
     * Funcion que realiza una consulta sobre una sentencia sql dada
     * 
     * @param sql de la consulta
     * @return lista resultados (0..n) Usuasios
     * @throws PersistenciaException error controlado
     */
    private ArrayList<Tipo> buscarTipo(String sql) throws PersistenciaException {
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
            throw new PersistenciaException(SE_HA_PRODUCIDO_UN_ERROR_EN_LA_BUSQUEDA, exception);
        } finally {
            closeConecction(connection, statement, resultSet);
        }
        return lista;
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
            throw new PersistenciaException(SE_HA_PRODUCIDO_UN_ERROR_EN_LA_BUSQUEDA, exception);
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
