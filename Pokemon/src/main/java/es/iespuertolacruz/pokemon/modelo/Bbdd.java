package es.iespuertolacruz.pokemon.modelo;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Popup;

import es.iespuertolacruz.pokemon.api.*;
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
        String sql = "DELETE POKEMON FROM POKEMON WHERE numero_pokedex = " + pokemon.getNumeroPokedex() + ";";
        actualizar(sql);
    }

    /**
     * Metodo encargado de realizar la modificacion
     * 
     * @param pokemon a actualizar
     * @throws PersistenciaException error controlado
     */
    public void modificar(Pokemon pokemon) throws PersistenciaException {
        String sql = "UPDATE POKEMON SET " + "nombre = '" + pokemon.getNombre() + "', " + "id_caracteristica = "
                + pokemon.getCaracteristicas() + ", " + "id_estadisticas_base = " + pokemon.getEstadisticasBase()
                + " WHERE identificador = " + pokemon.getNumeroPokedex() + ";";
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

    /**
     * Metodo encargado de realizar la insercion de un pokemon
     * 
     * @param pokemon a insertar
     * @throws PersistenciaException error controlado
     */
    public void insertarObjeto(Objeto objeto) throws PersistenciaException {
        String sql = "INSERT INTO OBJETO VALUES (" + objeto.getId() + ",'" + objeto.getNombre() + "',"
                + objeto.getModoObtencion() + ");";
        actualizar(sql);
    }

    /**
     * Metodo encargado de realizar la eliminacion
     * 
     * @param objeto a eliminar
     * @throws PersistenciaException error controlado
     */
    public void eliminarObjeto(Objeto objeto) throws PersistenciaException {
        String sql = "DELETE OBJETO FROM OBJETO WHERE id_objeto = " + objeto.getId() + ";";
        actualizar(sql);
    }

    /**
     * Metodo encargado de realizar la modificacion
     * 
     * @param objeto a actualizar
     * @throws PersistenciaException error controlado
     */
    public void modificarObjeto(Objeto objeto) throws PersistenciaException {
        String sql = "UPDATE OBJETO SET " + "nombre = '" + objeto.getNombre() + "', " + "modo_obtencion = "
                + objeto.getModoObtencion() + ", " + " WHERE id_objeto = " + objeto.getId() + ";";
        actualizar(sql);
    }

    /**
     * Funcion que realiza la consulta sobre la BBDD y la tabla OBJECTO
     * 
     * @param sql de la consulta
     * @return lista de resultados
     * @throws PersistenciaException controlado
     */
    private ArrayList<Objeto> obtenerListadoObjeto(String sql) throws PersistenciaException {
        ArrayList<Objeto> listaObjeto = new ArrayList<>();

        Objeto objeto = null;
        Statement statement = null;
        ResultSet resultSet = null;
        Connection connection = null;
        try {
            connection = getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                int id = resultSet.getInt("id_objeto");
                String nombre = resultSet.getString("nombre");
                String modoObtencion = resultSet.getString("modo Obtencion");
                objeto = new Objeto(id, nombre, modoObtencion);
                listaObjeto.add(objeto);
            }
        } catch (Exception exception) {
            throw new PersistenciaException("Se ha producido un error realizando la consulta", exception);
        } finally {
            closeConecction(connection, statement, resultSet);
        }
        return listaObjeto;
    }

    /**
     * Funcion que obtiene el listado de todos los objeto
     * 
     * @return lisa total
     * @throws PersistenciaException controlado
     */
    public List<Objeto> obtenerListadoObjeto() throws PersistenciaException {
        String sql = "SELECT * FROM OBJETO";
        return obtenerListadoObjeto(sql);
    }

    /**
     * Funcion que obtiene id de objeto
     * 
     * @param id del objeto
     * @return lista total
     * @throws PersistenciaException controlado
     */
    public Objeto obtenerObjeto(int id) throws PersistenciaException {
        Objeto objeto = null;
        ArrayList<Objeto> listaObjeto = null;
        String sql = "SELECT * FROM OBJETO WHERE  = id_objeto";
        sql = sql + "'" + id + "';";
        listaObjeto = obtenerListadoObjeto(sql);
        if (!listaObjeto.isEmpty()) {
            objeto = listaObjeto.get(0);
        }
        return objeto;
    }

    /**
     * Metodo encargado de realizar la insercion de altoMando
     * 
     * @param altoMando a insertar
     * @throws PersistenciaException error controlado
     */
    public void insertarAltoMando(AltoMando altoMando) throws PersistenciaException {
        int idEntrenador;
        String tipoPrincipal;
        String sql = "INSERT INTO ALTO_MANDO VALUES (" + altoMando.getIdEntrenador() + ",'"
                + altoMando.getTipoPrincipal() + ");";
        actualizar(sql);
    }

    /**
     * Metodo encargado de realizar la eliminacion
     * 
     * @param altoMando a eliminar
     * @throws PersistenciaException error controlado
     */
    public void eliminarAltoMando(AltoMando altoMando) throws PersistenciaException {
        String sql = "DELETE ALTO_MANDO FROM ALTO_MANDO WHERE id_entrenador = " + altoMando.getIdEntrenador() + ";";
        actualizar(sql);
    }

    /**
     * Metodo encargado de realizar la modificacion
     * 
     * @param altoMando a actualizar
     * @throws PersistenciaException error controlado
     */
    public void modificarAltoMando(AltoMando altoMando) throws PersistenciaException {
        String sql = "UPDATE POKEMON SET " + "id_entrenador = '" + altoMando.getIdEntrenador() + "', "
                + "tipo_principal = " + altoMando.getTipoPrincipal() + " WHERE id_entrenador = "
                + altoMando.getIdEntrenador() + ";";
        actualizar(sql);
    }

    /**
     * Funcion que realiza la consulta sobre la BBDD y la tabla ALTO_MANDO
     * 
     * @param sql de la consulta
     * @return lista de resultados
     * @throws PersistenciaException controlado
     */
    private ArrayList<AltoMando> obtenerListadoAltoMando(String sql) throws PersistenciaException {
        ArrayList<AltoMando> listaAltoMando = new ArrayList<>();

        AltoMando altoMando = null;
        Statement statement = null;
        ResultSet resultSet = null;
        Connection connection = null;
        try {
            connection = getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                int idEntrenador = resultSet.getInt(" id_entrenador");
                String tipoPrincipal = resultSet.getString("tipo_principal");
                altoMando = new AltoMando(idEntrenador, tipoPrincipal);
                listaAltoMando.add(altoMando);
            }
        } catch (Exception exception) {
            throw new PersistenciaException("Se ha producido un error realizando la consulta", exception);
        } finally {
            closeConecction(connection, statement, resultSet);
        }
        return listaAltoMando;
    }

    /**
     * Funcion que obtiene el listado de todos los objeto
     * 
     * @return lisa total
     * @throws PersistenciaException controlado
     */
    public List<AltoMando> obtenerListadoAltoMando() throws PersistenciaException {
        String sql = "SELECT * FROM ALTO_MANDO ";
        return obtenerListadoAltoMando(sql);
    }

    /**
     * Funcion que obtiene un pokemon por su numero de la pokedex
     * 
     * @param numero de la pokedex del pokemon
     * @return lista total
     * @throws PersistenciaException controlado
     */
    public AltoMando obtenerAltoMando(int idEntrenador) throws PersistenciaException {
        AltoMando altoMando = null;
        ArrayList<AltoMando> listaAltoMando = null;
        String sql = "SELECT * FROM POKEMON WHERE numero_pokedex = ";
        sql = sql + "'" + idEntrenador + "';";
        listaAltoMando = obtenerListadoAltoMando(sql);
        if (!listaAltoMando.isEmpty()) {
            altoMando = listaAltoMando.get(0);
        }
        return altoMando;
    }

    /**
     * Metodo encargado de realizar la insercion de un campeonLiga
     * 
     * @param campeonLiga a insertar
     * @throws PersistenciaException error controlado
     */
    public void insertarCampeonLiga(CampeonLiga campeonLiga) throws PersistenciaException {
        int idEntrenador;
        String region;
        String sql = "INSERT INTO ALTO_MANDO VALUES (" + campeonLiga.getIdEntrenador() + ",'" + campeonLiga.getRegion()
                + ");";
        actualizar(sql);
    }

    /**
     * Metodo encargado de realizar la eliminacion
     * 
     * @param campeonLiga a eliminar
     * @throws PersistenciaException error controlado
     */
    public void eliminarCampeonLiga(CampeonLiga campeonLiga) throws PersistenciaException {
        String sql = "DELETE CAMPEON_LIGA FROM CAMPEON_LIGA WHERE id_entrenador = " + campeonLiga.getIdEntrenador()
                + ";";
        actualizar(sql);
    }

    /**
     * Metodo encargado de realizar la modificacion
     * 
     * @param campeonLiga a actualizar
     * @throws PersistenciaException error controlado
     */
    public void modificarCampeonLiga(CampeonLiga campeonLiga) throws PersistenciaException {
        String sql = "UPDATE CAMPEON_LIGA SET " + "id_entrenador = '" + campeonLiga.getIdEntrenador() + ", "
                + "region = " + campeonLiga.getRegion() + " WHERE id_entrenador = " + campeonLiga.getIdEntrenador()
                + ";";
        actualizar(sql);
    }

    /**
     * Funcion que realiza la consulta sobre la BBDD y la tabla Campeon_Liga
     * 
     * @param sql de la consulta
     * @return lista de resultados
     * @throws PersistenciaException controlado
     */
    private ArrayList<CampeonLiga> obtenerListadoCampeonLiga(String sql) throws PersistenciaException {
        ArrayList<CampeonLiga> listaCampeonLiga = new ArrayList<>();

        CampeonLiga campeonLiga = null;
        Statement statement = null;
        ResultSet resultSet = null;
        Connection connection = null;
        try {
            connection = getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                int idEntrenador = resultSet.getInt(" id_entrenador");
                String region = resultSet.getString("region");
                campeonLiga = new CampeonLiga(idEntrenador, region);
                listaCampeonLiga.add(campeonLiga);
            }
        } catch (Exception exception) {
            throw new PersistenciaException("Se ha producido un error realizando la consulta", exception);
        } finally {
            closeConecction(connection, statement, resultSet);
        }
        return listaCampeonLiga;
    }

    /**
     * Funcion que obtiene el listado de todos los objeto
     * 
     * @return lisa total
     * @throws PersistenciaException controlado
     */
    public List<CampeonLiga> obtenerListadoCampeonLiga() throws PersistenciaException {
        String sql = "SELECT * FROM CAMPEON_LIGA";
        return obtenerListadoCampeonLiga(sql);
    }

    /**
     * Funcion que obtiene un CampeonLiga
     * 
     * @param id de CampeonLiga
     * @return lista total
     * @throws PersistenciaException controlado
     */
    public CampeonLiga obtenerCampeonLiga(int idEntrenador) throws PersistenciaException {
        CampeonLiga campeonLiga = null;
        ArrayList<CampeonLiga> listaCampeonLiga = null;
        String sql = "SELECT * FROM CAMPEON_LIGA WHERE idEntrenador = ";
        sql = sql + "'" + idEntrenador + "';";
        listaCampeonLiga = obtenerListadoCampeonLiga(sql);
        if (!listaCampeonLiga.isEmpty()) {
            campeonLiga = listaCampeonLiga.get(0);
        }
        return campeonLiga;
    }

    /**
     * Metodo encargado de realizar la insercion de caracteristicas
     * 
     * @param caracteristicas a insertar
     * @throws PersistenciaException error controlado
     */
    public void insertarCaracteristicas(Caracteristicas caracteristicas) throws PersistenciaException {

        String sql = "INSERT INTO CARACTERISTICAS VALUES (" + caracteristicas.getId() + ",'"
                + caracteristicas.getAltura() + "'," + caracteristicas.getEspecie() + "',"
                + caracteristicas.getHabilidad() + "'," + caracteristicas.getPeso() + "',"
                + caracteristicas.getCategoria() + ");";
        actualizar(sql);
    }

    /**
     * Metodo encargado de realizar la eliminacion
     * 
     * @param caracteristicas a eliminar
     * @throws PersistenciaException error controlado
     */
    public void eliminarCaracteristicas(Caracteristicas caracteristicas) throws PersistenciaException {
        String sql = "DELETE POKEMON FROM POKEMON WHERE id_caracteristica = " + caracteristicas.getId() + ";";
        actualizar(sql);
    }

    /**
     * Metodo encargado de realizar la modificacion
     * 
     * @param caracteristicas a actualizar
     * @throws PersistenciaException error controlado
     */
    public void modificarCaracteristicas(Caracteristicas caracteristicas) throws PersistenciaException {
        String sql = "UPDATE CARACTERISTICAS SET " + "id_caracteristica = " + caracteristicas.getId() + "peso"
                + caracteristicas.getPeso() + "altura" + caracteristicas.getAltura() + "especie"
                + caracteristicas.getEspecie() + "habilidad" + caracteristicas.getHabilidad() + "categoria"
                + caracteristicas.getCategoria() + " WHERE id_caracteristica = " + caracteristicas.getId() + ";";
        actualizar(sql);
    }

    /**
     * Funcion que realiza la consulta sobre la BBDD y la tabla CARACTERISTICAS
     * 
     * @param sql de la consulta
     * @return lista de resultados
     * @throws PersistenciaException controlado
     */
    private ArrayList<Caracteristicas> obtenerListadoCaracteristicas(String sql) throws PersistenciaException {
        ArrayList<Caracteristicas> listaCaracteristicas = new ArrayList<>();

        Caracteristicas caracteristicas = null;
        Statement statement = null;
        ResultSet resultSet = null;
        Connection connection = null;
        try {
            connection = getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                int numeroPokedex = resultSet.getInt("numero_pokedex");
                int id = resultSet.getInt("id");
                float peso = resultSet.getFloat("peso");
                float altura = resultSet.getFloat("altura");
                ;
                String especie = resultSet.getString("especie");
                String habilidad = resultSet.getString("habilidad");
                String categoria = resultSet.getString("categoria");
                caracteristicas = new Caracteristicas(id, peso, altura, especie, habilidad, categoria);
                listaCaracteristicas.add(caracteristicas);
            }
        } catch (Exception exception) {
            throw new PersistenciaException("Se ha producido un error realizando la consulta", exception);
        } finally {
            closeConecction(connection, statement, resultSet);
        }
        return listaCaracteristicas;
    }

    /**
     * Funcion que obtiene el listado de todas las Caracteristica
     * 
     * @return lisa total
     * @throws PersistenciaException controlado
     */
    public List<Caracteristicas> obtenerListadoCaracteristica() throws PersistenciaException {
        String sql = "SELECT * FROM CARACTERISTICAS";
        return obtenerListadoCaracteristicas(sql);
    }

    /**
     * Funcion que obtiene un pokemon por su numero de la pokedex
     * 
     * @param numero de la pokedex del pokemon
     * @return lista total
     * @throws PersistenciaException controlado
     */
    public Caracteristicas obtenerCaracteristica(int Id_caracteristica) throws PersistenciaException {
        Caracteristicas caracteristicas = null;
        ArrayList<Caracteristicas> listaCaracteristica = null;
        String sql = "SELECT * FROM CARACTERISTICAS WHERE numero_pokedex = ";
        sql = sql + "'" + Id_caracteristica + "';";
        listaCaracteristica = obtenerListadoCaracteristicas(sql);
        if (!listaCaracteristica.isEmpty()) {
            caracteristicas = listaCaracteristica.get(0);
        }
        return caracteristicas;
    }

    /**
     * Metodo encargado de realizar la insercion de conoce
     * 
     * @param conoce a insertar
     * @throws PersistenciaException error controlado
     */
    public void insertarConoce(Conoce conoce) throws PersistenciaException {

        String sql = "INSERT INTO CARACTERISTICAS VALUES (" + conoce.getIdMovimiento() + ",'"
                + conoce.getNumeroPokedex() + ");";
        actualizar(sql);
    }

    /**
     * Metodo encargado de realizar la eliminacion
     * 
     * @param conoce a eliminar
     * @throws PersistenciaException error controlado
     */
    public void eliminarConoce(Conoce conoce) throws PersistenciaException {
        String sql = "DELETE CONOCE FROM CONOCE WHERE id_movimiento = " + conoce.getIdMovimiento() + ";";
        actualizar(sql);
    }

    /**
     * Metodo encargado de realizar la modificacion
     * 
     * @param conoce a actualizar
     * @throws PersistenciaException error controlado
     */
    public void modificarConoce(Conoce conoce) throws PersistenciaException {
        String sql = "UPDATE POKEMON SET " + "id_movimiento = '" + conoce.getIdMovimiento() + "numero_pokedex"
                + conoce.getNumeroPokedex() + ";";
        actualizar(sql);
    }

    /**
     * Funcion que realiza la consulta sobre la BBDD y la tabla OBJECTO
     * 
     * @param sql de la consulta
     * @return lista de resultados
     * @throws PersistenciaException controlado
     */
    private ArrayList<Conoce> obtenerListadoConoce(String sql) throws PersistenciaException {
        ArrayList<Conoce> listaConoce = new ArrayList<>();

        Conoce conoce = null;
        Statement statement = null;
        ResultSet resultSet = null;
        Connection connection = null;
        try {
            connection = getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                int numeroPokedex = resultSet.getInt("numero_pokedex");
                int idMovimiento = resultSet.getInt("idMovimiento");
                conoce = new Conoce(numeroPokedex, idMovimiento);
                listaConoce.add(conoce);
            }
        } catch (Exception exception) {
            throw new PersistenciaException("Se ha producido un error realizando la consulta", exception);
        } finally {
            closeConecction(connection, statement, resultSet);
        }
        return listaConoce;
    }

    /**
     * Funcion que obtiene el listado de todos los conoce
     * 
     * @return lisa total
     * @throws PersistenciaException controlado
     */
    public List<Conoce> obtenerListadoConoce() throws PersistenciaException {
        String sql = "SELECT * FROM  CONOCE ";
        return obtenerListadoConoce(sql);
    }

    /**
     * Funcion que obtiene un pokemon por su numero de la pokedex
     * 
     * @param conoce de la pokedex del pokemon
     * @return lista total
     * @throws PersistenciaException controlado
     */
    public Conoce obtenerConoce(int idMovimiento) throws PersistenciaException {
        Conoce conoce = null;
        ArrayList<Conoce> listaConoce = null;
        String sql = "SELECT * FROM POKEMON WHERE numero_pokedex = ";
        sql = sql + "'" + idMovimiento + "';";
        listaConoce = obtenerListadoConoce(sql);
        if (!listaConoce.isEmpty()) {
            conoce = listaConoce.get(0);
        }
        return conoce;
    }

    /**
     * Metodo encargado de realizar la insercion de un entrenador
     * 
     * @param entrenador a insertar
     * @throws PersistenciaException error controlado
     */
    public void insertarEntrenador(Entrenador entrenador) throws PersistenciaException {

        String sql = "INSERT INTO ENTRENADOR VALUES (" + entrenador.getId() + ",'" + entrenador.getNombre() + ");";
        actualizar(sql);
    }

    /**
     * Metodo encargado de realizar la eliminacion
     * 
     * @param entrenador a eliminar
     * @throws PersistenciaException error controlado
     */
    public void eliminarEntrenador(Entrenador entrenador) throws PersistenciaException {
        String sql = "DELETE ENTRENADOR FROM ENTRENADOR WHERE id_entrenador = " + entrenador.getId() + ";";
        actualizar(sql);
    }

    /**
     * Metodo encargado de realizar la modificacion
     * 
     * @param entrenador a actualizar
     * @throws PersistenciaException error controlado
     */
    public void modificarEntrenador(Entrenador entrenador) throws PersistenciaException {
        String sql = "UPDATE ENTRENADOR SET " + "nombre = '" + entrenador.getNombre() + ", " + "id_entrenador = "
                + entrenador.getId() + " WHERE id_entrenador = " + entrenador.getId() + ";";
        actualizar(sql);
    }

    /**
     * Funcion que realiza la consulta sobre la BBDD y la tabla OBJECTO
     * 
     * @param sql de la consulta
     * @return lista de resultados
     * @throws PersistenciaException controlado
     */
    private ArrayList<Entrenador> obtenerListadoEntrenador(String sql) throws PersistenciaException {
        ArrayList<Entrenador> listaEntrenador = new ArrayList<>();

        Entrenador entrenador = null;
        Statement statement = null;
        ResultSet resultSet = null;
        Connection connection = null;
        try {
            connection = getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                int id = resultSet.getInt("id_entrenador");
                String nombre = resultSet.getString("nombre");
                entrenador = new Entrenador(id, nombre);
                listaEntrenador.add(entrenador);
            }
        } catch (Exception exception) {
            throw new PersistenciaException("Se ha producido un error realizando la consulta", exception);
        } finally {
            closeConecction(connection, statement, resultSet);
        }
        return listaEntrenador;
    }

    /**
     * Funcion que obtiene el listado de todos los entrenador
     * 
     * @return lisa total
     * @throws PersistenciaException controlado
     */
    public List<Entrenador> obtenerListadoEntrenador() throws PersistenciaException {
        String sql = "SELECT * FROM ENTRENADOR";
        return obtenerListadoEntrenador(sql);
    }

    /**
     * Funcion que obtiene un entrenador
     * 
     * @param entrenador de la pokedex del pokemon
     * @return lista total
     * @throws PersistenciaException controlado
     */
    public Entrenador obtenerEntrenador(int id) throws PersistenciaException {
        Entrenador entrenador = null;
        ArrayList<Entrenador> listaEntrenador = null;
        String sql = "SELECT * FROM POKEMON WHERE numero_pokedex = ";
        sql = sql + "'" + id + "';";
        listaEntrenador = obtenerListadoEntrenador(sql);
        if (!listaEntrenador.isEmpty()) {
            entrenador = listaEntrenador.get(0);
        }
        return entrenador;
    }

    /**
     * Metodo encargado de realizar la insercion de un entrenadorCasual
     * 
     * @param entrenadorCasual a insertar
     * @throws PersistenciaException error controlado
     */
    public void insertarEntrenadorCasual(EntrenadorCasual entrenadorCasual) throws PersistenciaException {

        String sql = "INSERT INTO CARACTERISTICAS VALUES (" + entrenadorCasual.getIdEntrenador() + ",'"
                + entrenadorCasual.getCantidadMedallas() + "'," + ");";
        actualizar(sql);
    }

    /**
     * Metodo encargado de realizar la eliminacion
     * 
     * @param entrenadorCasual a eliminar
     * @throws PersistenciaException error controlado
     */
    public void eliminarEntrenadorCasual(EntrenadorCasual entrenadorCasual) throws PersistenciaException {
        String sql = "DELETE ENTRENADOR_CASUAL FROM ENTRENADOR_CASUAL WHERE numero_pokedex = "
                + entrenadorCasual.getIdEntrenador() + ";";
        actualizar(sql);
    }

    /**
     * Metodo encargado de realizar la modificacion
     * 
     * @param entrenadorCasual a actualizar
     * @throws PersistenciaException error controlado
     */
    public void modificarEntrenadorCasual(EntrenadorCasual entrenadorCasual) throws PersistenciaException {
        String sql = "UPDATE ENTRENADOR_CASUAL SET " + "id_entrenador = '" + entrenadorCasual.getIdEntrenador() + "', "
                + "cantidad_medalla = " + entrenadorCasual.getCantidadMedallas() + " WHERE id_entrenador = "
                + entrenadorCasual.getIdEntrenador() + ";";
        actualizar(sql);
    }

    /**
     * Funcion que realiza la consulta sobre la BBDD y la tabla OBJECTO
     * 
     * @param sql de la consulta
     * @return lista de resultados
     * @throws PersistenciaException controlado
     */
    private ArrayList<EntrenadorCasual> obtenerListadoEntrenadorCasual(String sql) throws PersistenciaException {
        ArrayList<EntrenadorCasual> listaEntrenadorCasual = new ArrayList<>();

        EntrenadorCasual entrenadorCasual = null;
        Statement statement = null;
        ResultSet resultSet = null;
        Connection connection = null;
        try {
            connection = getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                int idEntrenador = resultSet.getInt("id_Entrenador");
                int cantidadMedallas = resultSet.getInt("cantidad_Medallas");
                entrenadorCasual = new EntrenadorCasual(idEntrenador, cantidadMedallas);
                listaEntrenadorCasual.add(entrenadorCasual);
            }
        } catch (Exception exception) {
            throw new PersistenciaException("Se ha producido un error realizando la consulta", exception);
        } finally {
            closeConecction(connection, statement, resultSet);
        }
        return listaEntrenadorCasual;
    }

    /**
     * Funcion que obtiene el listado de todos los entrenadorCasuales
     * 
     * @return lisa total
     * @throws PersistenciaException controlado
     */
    public List<EntrenadorCasual> obtenerListadoEntrenadorCasual() throws PersistenciaException {
        String sql = "SELECT * FROM ENTRENADOR_CASUAL";
        return obtenerListadoEntrenadorCasual(sql);
    }

    /**
     * Funcion que obtiene un pokemon por su numero de la pokedex
     * 
     * @param numero de la pokedex del pokemon
     * @return lista total
     * @throws PersistenciaException controlado
     */
    public EntrenadorCasual obtenerEntrenadorCasual(int id) throws PersistenciaException {
        EntrenadorCasual entrenadorCasual = null;
        ArrayList<EntrenadorCasual> listaEntrenadorCasual = null;
        String sql = "SELECT * FROM POKEMON WHERE numero_pokedex = ";
        sql = sql + "'" + id + "';";
        listaEntrenadorCasual = obtenerListadoEntrenadorCasual(sql);
        if (!listaEntrenadorCasual.isEmpty()) {
            entrenadorCasual = listaEntrenadorCasual.get(0);
        }
        return entrenadorCasual;
    }

    /**
     * Metodo encargado de realizar la insercion de un entrenadorEquipa
     * 
     * @param entrenadorEquipa a insertar
     * @throws PersistenciaException error controlado
     */
    public void insertarEntrenadorEquipa(EntrenadorEquipa entrenadorEquipa) throws PersistenciaException {

        String sql = "INSERT INTO ENTRENADOR_EQUIPA VALUES (" + entrenadorEquipa.getIdObjeto() + ",'"
                + entrenadorEquipa.getIdEntrenador() + "'," + entrenadorEquipa.getCantidad() + ");";
        actualizar(sql);
    }

    /**
     * Metodo encargado de realizar la eliminacion
     * 
     * @param entrenadorEquipa a eliminar
     * @throws PersistenciaException error controlado
     */
    public void eliminarEntrenadorEquipa(EntrenadorEquipa entrenadorEquipa) throws PersistenciaException {
        String sql = "DELETE ENTRENADOR_EQUIPA FROM ENTRENADOR_EQUIPA WHERE Id_Entrenador = "
                + entrenadorEquipa.getIdEntrenador() + ";";
        actualizar(sql);
    }

    /**
     * Metodo encargado de realizar la modificacion
     * 
     * @param entrenadorEquipa a actualizar
     * @throws PersistenciaException error controlado
     */
    public void modificarEntrenadorEquipa(EntrenadorEquipa entrenadorEquipa) throws PersistenciaException {
        String sql = "UPDATE  ENTRENADOR_EQUIPA SET " + "id_entrenador = '" + entrenadorEquipa.getIdEntrenador() + "', "
                + " id_objeto= " + entrenadorEquipa.getIdEntrenador() + ", " + "cantidad = "
                + entrenadorEquipa.getCantidad() + " WHERE id_entrenador = " + entrenadorEquipa.getIdEntrenador() + ";";
        actualizar(sql);
    }

    /**
     * Funcion que realiza la consulta sobre la BBDD y la tabla ENTRENADOR_EQUIPA
     * 
     * @param sql de la consulta
     * @return lista de resultados
     * @throws PersistenciaException controlado
     */
    private ArrayList<EntrenadorEquipa> obtenerListadoEntrenadorEquipa(String sql) throws PersistenciaException {
        ArrayList<EntrenadorEquipa> listaEntrenadorEquipa = new ArrayList<>();

        EntrenadorEquipa entrenadorEquipa = null;
        Statement statement = null;
        ResultSet resultSet = null;
        Connection connection = null;
        try {
            connection = getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                int idEntrenador = resultSet.getInt("id_Entrenador");
                int idObjeto = resultSet.getInt("id_Objeto");
                int cantidad = resultSet.getInt("cantidad");
                entrenadorEquipa = new EntrenadorEquipa(idEntrenador, idObjeto, cantidad);
                listaEntrenadorEquipa.add(entrenadorEquipa);
            }
        } catch (Exception exception) {
            throw new PersistenciaException("Se ha producido un error realizando la consulta", exception);
        } finally {
            closeConecction(connection, statement, resultSet);
        }
        return listaEntrenadorEquipa;
    }

    /**
     * Funcion que obtiene el listado de entrenadorEquipa
     * 
     * @return lisa total
     * @throws PersistenciaException controlado
     */
    public List<EntrenadorEquipa> obtenerListadoEntrenadorEquipa() throws PersistenciaException {
        String sql = "SELECT * FROM  ENTRENADOR_EQUIPA";
        return obtenerListadoEntrenadorEquipa(sql);
    }

    /**
     * Funcion que obtiene entrenadorEquipa
     * 
     * @param id de la pokedex del pokemon
     * @return lista total
     * @throws PersistenciaException controlado
     */
    public EntrenadorEquipa obtenerEntrenadorEquipa(int id) throws PersistenciaException {
        EntrenadorEquipa entrenadorEquipa = null;
        ArrayList<EntrenadorEquipa> listaEntrenadorEquipa = null;
        String sql = "SELECT * FROM POKEMON WHERE numero_pokedex = ";
        sql = sql + "'" + id + "';";
        listaEntrenadorEquipa = obtenerListadoEntrenadorEquipa(sql);
        if (!listaEntrenadorEquipa.isEmpty()) {
            entrenadorEquipa = listaEntrenadorEquipa.get(0);
        }
        return entrenadorEquipa;
    }

    /**
     * Metodo encargado de realizar la insercion de estadisticasBase de un pokemon
     * 
     * @param estadisticasBase a insertar
     * @throws PersistenciaException error controlado
     */
    public void insertarEstadisticasBase(EstadisticasBase estadisticasBase) throws PersistenciaException {

        String sql = "INSERT INTO ESTADISTICAS_BASE VALUES (" + estadisticasBase.getId() + ",'"
                + estadisticasBase.getPsBase() + "'," + estadisticasBase.getAtaqueBase() + "',"
                + estadisticasBase.getDefensaBase() + "'," + estadisticasBase.getAtaqueEspecialBase() + "',"
                + estadisticasBase.getDefensaEspecialBase() + "'," + estadisticasBase.getVelocidadBase() + ");";
        actualizar(sql);
    }

    /**
     * Metodo encargado de realizar la eliminacion
     * 
     * @param estadisticasBase a eliminar
     * @throws PersistenciaException error controlado
     */
    public void eliminarEstadisticasBase(EstadisticasBase estadisticasBase) throws PersistenciaException {
        String sql = "DELETE ESTADISTICAS_BASE FROM ESTADISTICAS_BASE WHERE id_estadisticas_base = "
                + estadisticasBase.getId() + ";";
        actualizar(sql);
    }

    /**
     * Metodo encargado de realizar la modificacion
     * 
     * @param estadisticasBase a actualizar
     * @throws PersistenciaException error controlado
     */
    public void modificarEstadisticasBase(EstadisticasBase estadisticasBase) throws PersistenciaException {
        String sql = "UPDATE ESTADISTICAS_BASE SET " + "psBase = '" + estadisticasBase.getPsBase() + "', "
                + "ataque_Base = " + estadisticasBase.getAtaqueBase() + ", " + "defensaBase = "
                + estadisticasBase.getDefensaBase() + "ataqueEspecialBase = " + estadisticasBase.getAtaqueEspecialBase()
                + "defensaEspecial_Base=" + estadisticasBase.getDefensaEspecialBase() + "velocidad_Base= "
                + estadisticasBase.getVelocidadBase() + "id_estadisticas_base" + estadisticasBase.getId()
                + " WHERE id_estadisticas_base = " + estadisticasBase.getId() + ";";
        actualizar(sql);
    }

    /**
     * Funcion que realiza la consulta sobre la BBDD y la tabla ESTADISTICAS_BASE
     * 
     * @param sql de la consulta
     * @return lista de resultados
     * @throws PersistenciaException controlado
     */
    private ArrayList<EstadisticasBase> obtenerListadoEstadisticasBase(String sql) throws PersistenciaException {
        ArrayList<EstadisticasBase> listaEstadisticasBase = new ArrayList<>();

        EstadisticasBase estadisticasBase = null;
        Statement statement = null;
        ResultSet resultSet = null;
        Connection connection = null;
        try {
            connection = getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                int id = resultSet.getInt("id_estadisticas_base");
                int psBase = resultSet.getInt("ps_base");
                int ataqueBase = resultSet.getInt("ataque_base");
                int defensaBase = resultSet.getInt("defensa_base");
                int ataqueEspecialBase = resultSet.getInt("ataque_especial_base");
                int defensaEspecialBase = resultSet.getInt("defensa_especial_base");
                int velocidadBase = resultSet.getInt("velocidad_base");
                estadisticasBase = new EstadisticasBase(id, psBase, ataqueBase, defensaBase, ataqueEspecialBase,
                        defensaEspecialBase, velocidadBase);
                listaEstadisticasBase.add(estadisticasBase);
            }
        } catch (Exception exception) {
            throw new PersistenciaException("Se ha producido un error realizando la consulta", exception);
        } finally {
            closeConecction(connection, statement, resultSet);
        }
        return listaEstadisticasBase;
    }

    /**
     * Funcion que obtiene el listado de todas estadisticasBase
     * 
     * @return lisa total
     * @throws PersistenciaException controlado
     */
    public List<EstadisticasBase> obtenerListadoEstadisticasBase() throws PersistenciaException {
        String sql = "SELECT * FROM  ESTADISTICAS_BASE";
        return obtenerListadoEstadisticasBase(sql);
    }

    /**
     * Funcion que obtiene estadisticasBase de un pokemon
     * 
     * @param estadisticasBase del pokemon
     * @return lista total
     * @throws PersistenciaException controlado
     */
    public EstadisticasBase obtenerEstadisticasBase(int id) throws PersistenciaException {
        EstadisticasBase estadisticasBase = null;
        ArrayList<EstadisticasBase> listaEstadisticasBase = null;
        String sql = "SELECT * FROM ESTADISTICAS_BASE WHERE id_estadisticas_base = ";
        sql = sql + "'" + id + "';";
        listaEstadisticasBase = obtenerListadoEstadisticasBase(sql);
        if (!listaEstadisticasBase.isEmpty()) {
            estadisticasBase = listaEstadisticasBase.get(0);
        }
        return estadisticasBase;
    }

    /**
     * Metodo encargado de realizar la insercion de un estado
     * 
     * @param estado a insertar
     * @throws PersistenciaException error controlado
     */
    public void insertarEstado(Estado estado) throws PersistenciaException {

        String sql = "INSERT INTO ESTADO VALUES (" + estado.getId() + ",'" + estado.getNombre() + "',"
                + estado.getPersistencia() + estado.getEfecto() + ");";
        actualizar(sql);
    }

    /**
     * Metodo encargado de realizar la eliminacion
     * 
     * @param estado a eliminar
     * @throws PersistenciaException error controlado
     */
    public void eliminarEstado(Estado estado) throws PersistenciaException {
        String sql = "DELETE ESTADO FROM ESTADO WHERE id_estado = " + estado.getId() + ";";
        actualizar(sql);
    }

    /**
     * Metodo encargado de realizar la modificacion
     * 
     * @param estado a actualizar
     * @throws PersistenciaException error controlado
     */
    public void modificarEstado(Estado estado) throws PersistenciaException {
        String sql = "UPDATE ESTADO SET " + "nombre = '" + estado.getNombre() + "', " + " persitencia = "
                + estado.getPersistencia() + ", " + "efecto =" + estado.getEfecto() + "id_estado = " + estado.getId()
                + " WHERE id_estado = " + estado.getId() + ";";
        actualizar(sql);
    }

    /**
     * Funcion que realiza la consulta sobre la BBDD y la tabla ESTADO
     * 
     * @param sql de la consulta
     * @return lista de resultados
     * @throws PersistenciaException controlado
     */
    private ArrayList<Estado> obtenerListadoEstado(String sql) throws PersistenciaException {
        ArrayList<Estado> listaEstado = new ArrayList<>();

        Estado estado = null;
        Statement statement = null;
        ResultSet resultSet = null;
        Connection connection = null;
        try {
            connection = getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                int id = resultSet.getInt("id_estado");
                String nombre = resultSet.getString("nombre");
                int persistencia = resultSet.getInt("persistencia");
                String efecto = resultSet.getString("efecto");
                estado = new Estado(id, nombre, persistencia, efecto);
                listaEstado.add(estado);
            }
        } catch (Exception exception) {
            throw new PersistenciaException("Se ha producido un error realizando la consulta", exception);
        } finally {
            closeConecction(connection, statement, resultSet);
        }
        return listaEstado;
    }

    /**
     * Funcion que obtiene el listado de todos los estado
     * 
     * @return lisa total
     * @throws PersistenciaException controlado
     */
    public List<Estado> obtenerListadoEstado() throws PersistenciaException {
        String sql = "SELECT * FROM ESTADO";
        return obtenerListadoEstado(sql);
    }

    /**
     * Funcion que obtiene el estado
     * 
     * @param estado
     * @return lista total
     * @throws PersistenciaException controlado
     */
    public Estado obtenerEstado(int id) throws PersistenciaException {
        Estado estado = null;
        ArrayList<Estado> listaEstado = null;
        String sql = "SELECT * FROM ESTADO WHERE  numero_pokedex_origen = ";
        sql = sql + "'" + id + "';";
        listaEstado = obtenerListadoEstado(sql);
        if (!listaEstado.isEmpty()) {
            estado = listaEstado.get(0);
        }
        return estado;
    }

    /**
     * Metodo encargado de realizar la insercion de una evoluciona
     * 
     * @param evoluciona a insertar
     * @throws PersistenciaException error controlado
     */
    public void insertarEvoluciona(Evoluciona evoluciona) throws PersistenciaException {

        String sql = "INSERT INTO EVOLUCIONA VALUES (" + evoluciona.getNumeroPokedexOrigen() + ",'"
                + evoluciona.getNumeroPokedexDestino() + "'," + evoluciona.getModoEvoluciona() + ");";
        actualizar(sql);
    }

    /**
     * Metodo encargado de realizar la eliminacion
     * 
     * @param evoluciona a eliminar
     * @throws PersistenciaException error controlado
     */
    public void eliminarEvoluciona(Evoluciona evoluciona) throws PersistenciaException {
        String sql = "DELETE EVOLUCIONA FROM EVOLUCIONA WHERE  numero_pokedex_origen = "
                + evoluciona.getNumeroPokedexOrigen() + ";";
        actualizar(sql);
    }

    /**
     * Metodo encargado de realizar la modificacion
     * 
     * @param evoluciona a actualizar
     * @throws PersistenciaException error controlado
     */
    public void modificarEvoluciona(Evoluciona evoluciona) throws PersistenciaException {
        String sql = "UPDATE EVOLUCIONA SET " + " numero_pokedex_origen= '" + evoluciona.getNumeroPokedexOrigen()
                + "', " + " modo_evoluciona= " + evoluciona.getModoEvoluciona() + ", "
                + " WHERE numero_pokedex_origen = " + evoluciona.getNumeroPokedexOrigen() + ";";
        actualizar(sql);
    }

    /**
     * Funcion que realiza la consulta sobre la BBDD y la tabla EVOLUCIONA
     * 
     * @param sql de la consulta
     * @return lista de resultados
     * @throws PersistenciaException controlado
     */
    private ArrayList<Evoluciona> obtenerListadoEvoluciona(String sql) throws PersistenciaException {
        ArrayList<Evoluciona> listaEvoluciona = new ArrayList<>();

        Evoluciona evoluciona = null;
        Statement statement = null;
        ResultSet resultSet = null;
        Connection connection = null;
        try {
            connection = getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                int numeroPokedexOrigen = resultSet.getInt("numero_pokedex_Origen");
                int numeroPokedexDestino = resultSet.getInt("numero_pokedex_Destino");
                String modoEvoluciona = resultSet.getString("modoEvoluciona");
                evoluciona = new Evoluciona(numeroPokedexOrigen, numeroPokedexDestino, modoEvoluciona);
                listaEvoluciona.add(evoluciona);
            }
        } catch (Exception exception) {
            throw new PersistenciaException("Se ha producido un error realizando la consulta", exception);
        } finally {
            closeConecction(connection, statement, resultSet);
        }
        return listaEvoluciona;
    }

    /**
     * Funcion que obtiene el listado de todos los objeto
     * 
     * @return lisa total
     * @throws PersistenciaException controlado
     */
    public List<Evoluciona> obtenerEvoluciona() throws PersistenciaException {
        String sql = "SELECT * FROM EVOLUCIONA";
        return obtenerListadoEvoluciona(sql);
    }

    /**
     * Funcion que obtiene un pokemon por su numero de la pokedex
     * 
     * @param numero de la pokedex del pokemon
     * @return lista total
     * @throws PersistenciaException controlado
     */
    public Evoluciona obtenerEvoluciona(int numeroPokedexOrigen) throws PersistenciaException {
        Evoluciona evoluciona = null;
        ArrayList<Evoluciona> listaEvoluciona = null;
        String sql = "SELECT * FROM POKEMON WHERE numero_pokedex = ";
        sql = sql + "'" + numeroPokedexOrigen + "';";
        listaEvoluciona = obtenerListadoEvoluciona(sql);
        if (!listaEvoluciona.isEmpty()) {
            evoluciona = listaEvoluciona.get(0);
        }
        return evoluciona;
    }

    /**
     * Metodo encargado de realizar la insercion del liderGimnasio
     * 
     * @param liderGimnasio a insertar
     * @throws PersistenciaException error controlado
     */
    public void insertarLiderGimnasio(LiderGimnasio liderGimnasio) throws PersistenciaException {

        String sql = "INSERT INTO LIDER_GIMNASIO VALUES (" + liderGimnasio.getIdEntrenador() + ",'"
                + liderGimnasio.getMedalla() + "'," + ");";
        actualizar(sql);
    }

    /**
     * Metodo encargado de realizar la eliminacion
     * 
     * @param liderGimnasio a eliminar
     * @throws PersistenciaException error controlado
     */
    public void eliminarObjeto(LiderGimnasio liderGimnasio) throws PersistenciaException {
        String sql = "DELETE LIDER_GIMNASIO FROM LIDER_GIMNASIO WHERE Id_Entrenador = "
                + liderGimnasio.getIdEntrenador() + ";";
        actualizar(sql);
    }

    /**
     * Metodo encargado de realizar la modificacion
     * 
     * @param liderGimnasio a actualizar
     * @throws PersistenciaException error controlado
     */
    public void modificarObjeto(LiderGimnasio liderGimnasio) throws PersistenciaException {
        String sql = "UPDATE LIDER_GIMNASIO SET " + "medalla=" + liderGimnasio.getMedalla() + " WHERE Id_Entrenador = "
                + liderGimnasio.getIdEntrenador() + ";";
        actualizar(sql);
    }

    /**
     * Funcion que realiza la consulta sobre la BBDD y la tabla LIDER_GIMNASIO
     * 
     * @param sql de la consulta
     * @return lista de resultados
     * @throws PersistenciaException controlado
     */
    private ArrayList<LiderGimnasio> obtenerListadoLiderGimnasio(String sql) throws PersistenciaException {
        ArrayList<LiderGimnasio> listaLiderGimnasio = new ArrayList<>();

        LiderGimnasio liderGimnasio = null;
        Statement statement = null;
        ResultSet resultSet = null;
        Connection connection = null;
        try {
            connection = getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                int idEntrenador = resultSet.getInt("idEntrenador");
                int medalla = resultSet.getInt("medalla");
                liderGimnasio = new LiderGimnasio(idEntrenador, medalla);
                listaLiderGimnasio.add(liderGimnasio);
            }
        } catch (Exception exception) {
            throw new PersistenciaException("Se ha producido un error realizando la consulta", exception);
        } finally {
            closeConecction(connection, statement, resultSet);
        }
        return listaLiderGimnasio;
    }

    /**
     * Funcion que obtiene el listado de LiderGimnasio
     * 
     * @return lisa total
     * @throws PersistenciaException controlado
     */
    public List<LiderGimnasio> obtenerListadoLiderGimnasio() throws PersistenciaException {
        String sql = "SELECT * FROM LIDER_GIMNASIO";
        return obtenerListadoLiderGimnasio(sql);
    }

    /**
     * Funcion que obtiene un liderGimnasio
     * 
     * @param liderGimnasio
     * @return lista total
     * @throws PersistenciaException controlado
     */
    public LiderGimnasio obtenerLiderGimnasio(int idEntrenador) throws PersistenciaException {
        LiderGimnasio liderGimnasio = null;
        ArrayList<LiderGimnasio> listaLiderGimnasio = null;
        String sql = "SELECT * FROM POKEMON WHERE numero_pokedex = ";
        sql = sql + "'" + idEntrenador + "';";
        listaLiderGimnasio = obtenerListadoLiderGimnasio(sql);
        if (!listaLiderGimnasio.isEmpty()) {
            liderGimnasio = listaLiderGimnasio.get(0);
        }
        return liderGimnasio;
    }

    /**
     * Metodo encargado de realizar la insercion de maquina
     * 
     * @param maquina a insertar
     * @throws PersistenciaException error controlado
     */
    public void insertarMaquina(Maquina maquina) throws PersistenciaException {

        String sql = "INSERT INTO MAQUINA VALUES (" + maquina.getIdObjeto() + ",'" + maquina.getIdMovimiento() + "',"
                + ");";
        actualizar(sql);
    }

    /**
     * Metodo encargado de realizar la eliminacion
     * 
     * @param maquina a eliminar
     * @throws PersistenciaException error controlado
     */
    public void eliminarObjeto(Maquina maquina) throws PersistenciaException {
        String sql = "DELETE MAQUINA FROM MAQUINA WHERE id_objeto = " + maquina.getIdObjeto() + ";";
        actualizar(sql);
    }

    /**
     * Metodo encargado de realizar la modificacion
     * 
     * @param maquina a actualizar
     * @throws PersistenciaException error controlado
     */
    public void modificarObjeto(Maquina maquina) throws PersistenciaException {
        String sql = "UPDATE MAQUINA SET " + "id_objeto = '" + maquina.getIdObjeto() + "', " + "Id_Objeto= "
                + maquina.getIdObjeto() + ", " + " WHERE id_objeto = " + maquina.getIdObjeto() + ";";
        actualizar(sql);
    }

    /**
     * Funcion que realiza la consulta sobre la BBDD y la tabla MAQUINA
     * 
     * @param sql de la consulta
     * @return lista de resultados
     * @throws PersistenciaException controlado
     */
    private ArrayList<Maquina> obtenerListadoMaquina(String sql) throws PersistenciaException {
        ArrayList<Maquina> listaMaquina = new ArrayList<>();

        Maquina maquina = null;
        Statement statement = null;
        ResultSet resultSet = null;
        Connection connection = null;
        try {
            connection = getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                int idObjeto = resultSet.getInt("id_Objeto");
                int idMovimiento = resultSet.getInt("id_Movimiento");
                maquina = new Maquina(idObjeto, idMovimiento);
                listaMaquina.add(maquina);
            }
        } catch (Exception exception) {
            throw new PersistenciaException("Se ha producido un error realizando la consulta", exception);
        } finally {
            closeConecction(connection, statement, resultSet);
        }
        return listaMaquina;
    }

    /**
     * Funcion que obtiene el listado de todos las Maquina
     * 
     * @return lisa total
     * @throws PersistenciaException controlado
     */
    public List<Maquina> obtenerListadoMaquina() throws PersistenciaException {
        String sql = "SELECT * FROM MAQUINA";
        return obtenerListadoMaquina(sql);
    }

    /**
     * Funcion que obtiene una Maquina
     * 
     * @param maquina
     * @return lista total
     * @throws PersistenciaException controlado
     */
    public Maquina obtenerMaquina(int idObjeto) throws PersistenciaException {
        Maquina maquina = null;
        ArrayList<Maquina> listaMaquina = null;
        String sql = "SELECT * FROM MAQUINA WHERE id_objeto = ";
        sql = sql + "'" + idObjeto + "';";
        listaMaquina = obtenerListadoMaquina(sql);
        if (!listaMaquina.isEmpty()) {
            maquina = listaMaquina.get(0);
        }
        return maquina;
    }

    /**
     * Metodo encargado de realizar la insercion de un movimiento de un pokemon
     * 
     * @param movimiento a insertar
     * @throws PersistenciaException error controlado
     */
    public void insertarMovimiento(Movimiento movimiento) throws PersistenciaException {

        String sql = "INSERT INTO MOVIMIENTO VALUES (" + movimiento.getId() + ",'" + movimiento.getNombre() + "',"
                + movimiento.getTipo() + "'," + movimiento.getCategoria() + "'," + movimiento.getPp() + "',"
                + movimiento.getPotencia() + "'," + movimiento.getCerteza() + ");";
        actualizar(sql);
    }

    /**
     * Metodo encargado de realizar la eliminacion
     * 
     * @param movimiento a eliminar
     * @throws PersistenciaException error controlado
     */
    public void eliminarMovimiento(Movimiento movimiento) throws PersistenciaException {
        String sql = "DELETE MOVIMIENTO FROM MOVIMIENTO WHERE id_movimiento = " + movimiento.getId() + ";";
        actualizar(sql);
    }

    /**
     * Metodo encargado de realizar la modificacion
     * 
     * @param movimiento a actualizar
     * @throws PersistenciaException error controlado
     */
    public void modificarMovimiento(Movimiento movimiento) throws PersistenciaException {
        String sql = "UPDATE MOVIMIENTO SET " + "nombre = '" + movimiento.getNombre() + "', " + " tipo= "
                + movimiento.getTipo() + ", " + "categoria = " + movimiento.getCategoria() + "'," + movimiento.getPp()
                + "'," + movimiento.getPotencia() + "'," + movimiento.getCerteza() + " WHERE id_movimiento = "
                + movimiento.getId() + ";";
        actualizar(sql);
    }

    /**
     * Funcion que realiza la consulta sobre la BBDD y la tabla MOVIMIENTO
     * 
     * @param sql de la consulta
     * @return lista de resultados
     * @throws PersistenciaException controlado
     */
    private ArrayList<Movimiento> obtenerListadoMovimiento(String sql) throws PersistenciaException {
        ArrayList<Movimiento> listaMovimiento = new ArrayList<>();

        Movimiento movimiento = null;
        Statement statement = null;
        ResultSet resultSet = null;
        Connection connection = null;
        try {
            connection = getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                int id = resultSet.getInt("numero_pokedex");
                String nombre = resultSet.getString("nombre");
                String tipo = resultSet.getString("tipo");
                String categoria = resultSet.getString("n categoria");
                int pp = resultSet.getInt("pp");
                int potencia = resultSet.getInt("potencia");
                int certeza = resultSet.getInt("certeza");
                movimiento = new Movimiento(id, nombre, tipo, categoria, pp, potencia, certeza);
                listaMovimiento.add(movimiento);
            }
        } catch (Exception exception) {
            throw new PersistenciaException("Se ha producido un error realizando la consulta", exception);
        } finally {
            closeConecction(connection, statement, resultSet);
        }
        return listaMovimiento;
    }

    /**
     * Funcion que obtiene el listado de todos Movimiento
     * 
     * @return lisa total
     * @throws PersistenciaException controlado
     */
    public List<Movimiento> obtenerListadoMovimiento() throws PersistenciaException {
        String sql = "SELECT * FROM  MOVIMIENTO";
        return obtenerListadoMovimiento(sql);
    }

    /**
     * Funcion que obtiene un Movimiento
     * 
     * @param movimiento del pokemon
     * @return lista total
     * @throws PersistenciaException controlado
     */
    public Movimiento obtenerMovimiento(int id) throws PersistenciaException {
        Movimiento movimiento = null;
        ArrayList<Movimiento> listaMovimiento = null;
        String sql = "SELECT * FROM MOVIMIENTO WHERE id_movimiento = ";
        sql = sql + "'" + id + "';";
        listaMovimiento = obtenerListadoMovimiento(sql);
        if (!listaMovimiento.isEmpty()) {
            movimiento = listaMovimiento.get(0);
        }
        return movimiento;
    }

    /**
     * Metodo encargado de realizar la insercion de un pobjetoComun
     * 
     * @param objetoComun a insertar
     * @throws PersistenciaException error controlado
     */
    public void insertarOBJETO_COMUN(ObjetoComun objetoComun) throws PersistenciaException {

        String sql = "INSERT INTO OBJETO_COMUN VALUES (" + objetoComun.getIdObjeto() + ",'" + objetoComun.getEfecto()
                + ");";
        actualizar(sql);
    }

    /**
     * Metodo encargado de realizar la eliminacion
     * 
     * @param objetoComun a eliminar
     * @throws PersistenciaException error controlado
     */
    public void eliminarObjetoComun(ObjetoComun objetoComun) throws PersistenciaException {
        String sql = "DELETE OBJETO_COMUN FROM OBJETO_COMUN WHERE  id_objeto = " + objetoComun.getIdObjeto() + ";";
        actualizar(sql);
    }

    /**
     * Metodo encargado de realizar la modificacion
     * 
     * @param pokemon a actualizar
     * @throws PersistenciaException error controlado
     */
    public void modificarObjetoComun(ObjetoComun objetoComun) throws PersistenciaException {
        String sql = "UPDATE OBJETO_COMUN SET " + "efecto '" + objetoComun.getEfecto() + "', " + " idObjeto= "
                + objetoComun.getIdObjeto() + " WHERE id_Objeto = " + objetoComun.getIdObjeto() + ";";
        actualizar(sql);
    }

    /**
     * Funcion que realiza la consulta sobre la BBDD y la tabla OBJETO_COMUN
     * 
     * @param sql de la consulta
     * @return lista de resultados
     * @throws PersistenciaException controlado
     */
    private ArrayList<ObjetoComun> obtenerListadoObjetoComun(String sql) throws PersistenciaException {
        ArrayList<ObjetoComun> listaObjetoComun = new ArrayList<>();

        ObjetoComun objetoComun = null;
        Statement statement = null;
        ResultSet resultSet = null;
        Connection connection = null;
        try {
            connection = getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                int idObjeto = resultSet.getInt("id_Objeto");
                String efecto = resultSet.getString("efecto");
                objetoComun = new ObjetoComun(idObjeto, efecto);
                listaObjetoComun.add(objetoComun);
            }
        } catch (Exception exception) {
            throw new PersistenciaException("Se ha producido un error realizando la consulta", exception);
        } finally {
            closeConecction(connection, statement, resultSet);
        }
        return listaObjetoComun;
    }

    /**
     * Funcion que obtiene el listado de todos los ObjetoComun
     * 
     * @return lisa total
     * @throws PersistenciaException controlado
     */
    public List<ObjetoComun> obtenerListadoObjetoComun() throws PersistenciaException {
        String sql = "SELECT * FROM OBJETO_COMUN";
        return obtenerListadoObjetoComun(sql);
    }

    /**
     * Funcion que obtiene un objetoComun
     * 
     * @param objetoComun
     * @return lista total
     * @throws PersistenciaException controlado
     */
    public ObjetoComun obtenerObjetoComun(int idObjeto) throws PersistenciaException {
        ObjetoComun objetoComun = null;
        ArrayList<ObjetoComun> listaObjetoComun = null;
        String sql = "SELECT * FROM POKEMON WHERE numero_pokedex = ";
        sql = sql + "'" + idObjeto + "';";
        listaObjetoComun = obtenerListadoObjetoComun(sql);
        if (!listaObjetoComun.isEmpty()) {
            objetoComun = listaObjetoComun.get(0);
        }
        return objetoComun;
    }

    /**
     * Metodo encargado de realizar la insercion de pertenece
     * 
     * @param pertenece a insertar
     * @throws PersistenciaException error controlado
     */
    public void insertarPertenece(Pertenece pertenece) throws PersistenciaException {

        String sql = "INSERT INTO PERTENECE VALUES (" + pertenece.getNumeroPokedex() + ",'" + pertenece.getTipo()
                + ");";
        actualizar(sql);
    }

    /**
     * Metodo encargado de realizar la eliminacion
     * 
     * @param pertenece a eliminar
     * @throws PersistenciaException error controlado
     */
    public void eliminarObjeto(Pertenece pertenece) throws PersistenciaException {
        String sql = "DELETE PERTENECE FROM PERTENECE WHERE numeroPokedex = " + pertenece.getNumeroPokedex() + ";";
        actualizar(sql);
    }

    /**
     * Metodo encargado de realizar la modificacion
     * 
     * @param pertenece a actualizar
     * @throws PersistenciaException error controlado
     */
    public void modificarObjeto(Pertenece pertenece) throws PersistenciaException {
        String sql = "UPDATE POKEMON SET " + " tipo = '" + pertenece.getTipo() + " WHERE Numero_Pokedex = "
                + pertenece.getNumeroPokedex() + ";";
        actualizar(sql);
    }

    /**
     * Funcion que realiza la consulta sobre la BBDD y la tabla PERTENECE
     * 
     * @param sql de la consulta
     * @return lista de resultados
     * @throws PersistenciaException controlado
     */
    private ArrayList<Pertenece> obtenerListadoPertenece(String sql) throws PersistenciaException {
        ArrayList<Pertenece> listaPertenece = new ArrayList<>();

        Pertenece pertenece = null;
        Statement statement = null;
        ResultSet resultSet = null;
        Connection connection = null;
        try {
            connection = getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                int numeroPokedex = resultSet.getInt("numero_pokedex");
                String tipo = resultSet.getString("tipo");
                pertenece = new Pertenece(numeroPokedex, tipo);
                listaPertenece.add(pertenece);
            }
        } catch (Exception exception) {
            throw new PersistenciaException("Se ha producido un error realizando la consulta", exception);
        } finally {
            closeConecction(connection, statement, resultSet);
        }
        return listaPertenece;
    }

    /**
     * Funcion que obtiene el listado de Pertenece
     * 
     * @return lisa total
     * @throws PersistenciaException controlado
     */
    public List<Pertenece> obtenerListadoPertenece() throws PersistenciaException {
        String sql = "SELECT * FROM  PERTENECE";
        return obtenerListadoPertenece(sql);
    }

    /**
     * Funcion que obtiene pertenece
     * 
     * @param pertenece a pokedex del pokemon
     * @return lista total
     * @throws PersistenciaException controlado
     */
    public Pertenece obtenerPertenece(int numeroPokedex) throws PersistenciaException {
        Pertenece pertenece = null;
        ArrayList<Pertenece> listaPertenece = null;
        String sql = "SELECT * FROM POKEMON WHERE numero_pokedex = ";
        sql = sql + "'" + numeroPokedex + "';";
        listaPertenece = obtenerListadoPertenece(sql);
        if (!listaPertenece.isEmpty()) {
            pertenece = listaPertenece.get(0);
        }
        return pertenece;
    }

    /**
     * Metodo encargado de realizar la insercion de una Pokeball
     * 
     * @param Pokeball a insertar
     * @throws PersistenciaException error controlado
     */
    public void insertarPokeball(Pokeball pokeball) throws PersistenciaException {

        String sql = "INSERT INTO CARACTERISTICAS VALUES (" + pokeball.getIdObjeto() + ",'" + pokeball.getRatio()
                + ");";
        actualizar(sql);
    }

    /**
     * Metodo encargado de realizar la eliminacion
     * 
     * @param pokeball a eliminar
     * @throws PersistenciaException error controlado
     */
    public void eliminarPokeball(Pokeball pokeball) throws PersistenciaException {
        String sql = "DELETE POKEBALL FROM POKEBALL WHERE id_Objeto = " + pokeball.getIdObjeto() + ";";
        actualizar(sql);
    }

    /**
     * Metodo encargado de realizar la modificacion
     * 
     * @param pokeball a actualizar
     * @throws PersistenciaException error controlado
     */
    public void modificarPokeball(Pokeball pokeball) throws PersistenciaException {
        String sql = "UPDATE POKEBALL  SET " + "ratio = '" + pokeball.getRatio() + "', " + "modo_obtencion = "
                + " WHERE id_objeto = " + pokeball.getIdObjeto() + ";";
        actualizar(sql);
    }

    /**
     * Funcion que realiza la consulta sobre la BBDD y la tabla POKEBALL
     * 
     * @param sql de la consulta
     * @return lista de resultados
     * @throws PersistenciaException controlado
     */
    private ArrayList<Pokeball> obtenerListadoPokeball(String sql) throws PersistenciaException {
        ArrayList<Pokeball> listaPokeball = new ArrayList<>();

        Pokeball pokeball = null;
        Statement statement = null;
        ResultSet resultSet = null;
        Connection connection = null;
        try {
            connection = getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                int idObjeto = resultSet.getInt("id_Objeto");
                float ratio = resultSet.getFloat("ratio");
                pokeball = new Pokeball(idObjeto, ratio);
                listaPokeball.add(pokeball);
            }
        } catch (Exception exception) {
            throw new PersistenciaException("Se ha producido un error realizando la consulta", exception);
        } finally {
            closeConecction(connection, statement, resultSet);
        }
        return listaPokeball;
    }

    /**
     * Funcion que obtiene un pokemon por su numero de la pokedex
     * 
     * @param numero de la pokedex del pokemon
     * @return lista total
     * @throws PersistenciaException controlado
     */
    public Pokeball obtenerPokeball(int idObjeto) throws PersistenciaException {
        Pokeball pokeball = null;
        ArrayList<Pokeball> listaPokeball = null;
        String sql = "SELECT * FROM Pokeball WHERE id_Objeto = ";
        sql = sql + "'" + idObjeto + "';";
        listaPokeball = obtenerListadoPokeball(sql);
        if (!listaPokeball.isEmpty()) {
            pokeball = listaPokeball.get(0);
        }
        return pokeball;
    }

    /**
     * Metodo encargado de realizar la insercion de pokemonEquipa
     * 
     * @param pokemonEquipa a insertar
     * @throws PersistenciaException error controlado
     */
    public void insertarPokemonEquipa(PokemonEquipa pokemonEquipa) throws PersistenciaException {

        String sql = "INSERT INTO POKEMON_EQUIPA VALUES (" + pokemonEquipa.getIdObjeto() + ",'"
                + pokemonEquipa.getNumeroPokedex() + ");";
        actualizar(sql);
    }

    /**
     * Metodo encargado de realizar la eliminacion
     * 
     * @param pokemonEquipa a eliminar
     * @throws PersistenciaException error controlado
     */
    public void eliminarPokemonEquipa(PokemonEquipa pokemonEquipa) throws PersistenciaException {
        String sql = "DELETE POKEMON_EQUIPA FROM POKEMON_EQUIPA WHERE numero_pokedex = "
                + pokemonEquipa.getNumeroPokedex() + ";";
        actualizar(sql);
    }

    /**
     * Metodo encargado de realizar la modificacion
     * 
     * @param pokemonEquipa a actualizar
     * @throws PersistenciaException error controlado
     */
    public void modificarObjeto(PokemonEquipa pokemonEquipa) throws PersistenciaException {
        String sql = "UPDATE POKEMON_EQUIPA SET " + "id_e = " + pokemonEquipa.getIdObjeto() + " WHERE Numero_Pokedex = "
                + pokemonEquipa.getNumeroPokedex() + ";";
        actualizar(sql);
    }

    /**
     * Funcion que realiza la consulta sobre la BBDD y la tabla POKEMON_EQUIPA
     * 
     * @param sql de la consulta
     * @return lista de resultados
     * @throws PersistenciaException controlado
     */
    private ArrayList<PokemonEquipa> obtenerListadoPokemonEquipa(String sql) throws PersistenciaException {
        ArrayList<PokemonEquipa> listaPokemonEquipa = new ArrayList<>();

        PokemonEquipa pokemonEquipa = null;
        Statement statement = null;
        ResultSet resultSet = null;
        Connection connection = null;
        try {
            connection = getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                int numeroPokedex = resultSet.getInt("numero_pokedex");
                int idObjeto = resultSet.getInt("idObjeto");
                pokemonEquipa = new PokemonEquipa(numeroPokedex, idObjeto);
                listaPokemonEquipa.add(pokemonEquipa);
            }
        } catch (Exception exception) {
            throw new PersistenciaException("Se ha producido un error realizando la consulta", exception);
        } finally {
            closeConecction(connection, statement, resultSet);
        }
        return listaPokemonEquipa;
    }

    /**
     * Funcion que obtiene el listado pokemonEquipa
     * 
     * @return lisa total
     * @throws PersistenciaException controlado
     */
    public List<PokemonEquipa> obtenerListadoPokemonEquipa() throws PersistenciaException {
        String sql = "SELECT * FROM  POKEMON_EQUIPA";
        return obtenerListadoPokemonEquipa(sql);
    }

    /**
     * Funcion que obtiene pokemonEquipa
     * 
     * @param pokemonEquipa
     * @return lista total
     * @throws PersistenciaException controlado
     */
    public PokemonEquipa obtenerPokemonEquipa(int numeroPokedex) throws PersistenciaException {
        PokemonEquipa pokemonEquipa = null;
        ArrayList<PokemonEquipa> listaPokemonEquipa = null;
        String sql = "SELECT * FROM POKEMON WHERE numero_pokedex = ";
        sql = sql + "'" + numeroPokedex + "';";
        listaPokemonEquipa = obtenerListadoPokemonEquipa(sql);
        if (!listaPokemonEquipa.isEmpty()) {
            pokemonEquipa = listaPokemonEquipa.get(0);
        }
        return pokemonEquipa;
    }

    /**
     * Metodo encargado de realizar la insercion de provoca
     * 
     * @param provoca a insertar
     * @throws PersistenciaException error controlado
     */
    public void insertarProvoca(Provoca provoca) throws PersistenciaException {

        String sql = "INSERT INTO PROVOCA  VALUES (" + provoca.getIdMovimiento() + ",'" + provoca.getIdEstado() + ");";
        actualizar(sql);
    }

    /**
     * Metodo encargado de realizar la eliminacion
     * 
     * @param provoca a eliminar
     * @throws PersistenciaException error controlado
     */
    public void eliminarProvoca(Provoca provoca) throws PersistenciaException {
        String sql = "DELETE PROVOCA  FROM PROVOCA  WHERE Id_Movimiento = " + provoca.getIdMovimiento() + ";";
        actualizar(sql);
    }

    /**
     * Metodo encargado de realizar la modificacion
     * 
     * @param provoca a actualizar
     * @throws PersistenciaException error controlado
     */
    public void modificarProvoca(Provoca provoca) throws PersistenciaException {
        String sql = "UPDATE PROVOCA  SET " + "Id_Estado = '" + provoca.getIdEstado() + " WHERE Id_Movimiento = "
                + provoca.getIdMovimiento() + ";";
        actualizar(sql);
    }

    /**
     * Funcion que realiza la consulta sobre la BBDD y la tabla PROVOCA
     * 
     * @param sql de la consulta
     * @return lista de resultados
     * @throws PersistenciaException controlado
     */
    private ArrayList<Provoca> obtenerListadoProvoca(String sql) throws PersistenciaException {
        ArrayList<Provoca> listaProvoca = new ArrayList<>();

        Provoca provoca = null;
        Statement statement = null;
        ResultSet resultSet = null;
        Connection connection = null;
        try {
            connection = getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                int idMovimiento = resultSet.getInt("id_Movimiento");
                int idEstado = resultSet.getInt("id_Estado");
                provoca = new Provoca(idMovimiento, idEstado);
                listaProvoca.add(provoca);
            }
        } catch (Exception exception) {
            throw new PersistenciaException("Se ha producido un error realizando la consulta", exception);
        } finally {
            closeConecction(connection, statement, resultSet);
        }
        return listaProvoca;
    }

    /**
     * Funcion que obtiene el listado de todos los provoca
     * 
     * @return lisa total
     * @throws PersistenciaException controlado
     */
    public List<Provoca> obtenerListadoProvoca() throws PersistenciaException {
        String sql = "SELECT * FROM PROVOCA  ";
        return obtenerListadoProvoca(sql);
    }

    /**
     * Funcion que obtiene un pokemon por su numero de la pokedex
     * 
     * @param numero de la pokedex del pokemon
     * @return lista total
     * @throws PersistenciaException controlado
     */
    public Provoca obtenerProvoca(int idMovimiento) throws PersistenciaException {
        Provoca provoca = null;
        ArrayList<Provoca> listaProvoca = null;
        String sql = "SELECT * FROM POKEMON WHERE numero_pokedex = ";
        sql = sql + "'" + idMovimiento + "';";
        listaProvoca = obtenerListadoProvoca(sql);
        if (!listaProvoca.isEmpty()) {
            provoca = listaProvoca.get(0);
        }
        return provoca;
    }

    /**
     * Metodo encargado de realizar la insercion de tiene
     * 
     * @param tiene a insertar
     * @throws PersistenciaException error controlado
     */
    public void insertarTiene(Tiene tiene) throws PersistenciaException {

        String sql = "INSERT INTO TIENE VALUES (" + tiene.getIdEntrenador() + ",'" + tiene.getNumeroPokedex() + "',"
                + tiene.getCantidad() + ");";
        actualizar(sql);
    }

    /**
     * Metodo encargado de realizar la eliminacion
     * 
     * @param pokemon a eliminar
     * @throws PersistenciaException error controlado
     */
    public void eliminarTiene(Tiene tiene) throws PersistenciaException {
        String sql = "DELETE TIENE FROM TIENE WHERE id_entrenador = " + tiene.getIdEntrenador() + ";";
        actualizar(sql);
    }

    /**
     * Metodo encargado de realizar la modificacion
     * 
     * @param pokemon a actualizar
     * @throws PersistenciaException error controlado
     */
    public void modificarTiene(Tiene tiene) throws PersistenciaException {
        String sql = "UPDATE TIENE SET " + "numero_pokedex = '" + tiene.getNumeroPokedex() + "', " + "cantidad = "
                + tiene.getCantidad() + " WHERE id_entrenador = " + tiene.getIdEntrenador() + ";";
        actualizar(sql);
    }

    /**
     * Funcion que realiza la consulta sobre la BBDD y la tabla TIENE
     * 
     * @param sql de la consulta
     * @return lista de resultados
     * @throws PersistenciaException controlado
     */
    private ArrayList<Tiene> obtenerListadoTiene(String sql) throws PersistenciaException {
        ArrayList<Tiene> listaTiene = new ArrayList<>();

        Tiene tiene = null;
        Statement statement = null;
        ResultSet resultSet = null;
        Connection connection = null;
        try {
            connection = getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                int idEntrenador = resultSet.getInt("id_Entrenador");
                int numeroPokedex = resultSet.getInt("numero_Pokedex");
                int cantidad = resultSet.getInt("cantidad");
                tiene = new Tiene(idEntrenador, numeroPokedex, cantidad);
                listaTiene.add(tiene);
            }
        } catch (Exception exception) {
            throw new PersistenciaException("Se ha producido un error realizando la consulta", exception);
        } finally {
            closeConecction(connection, statement, resultSet);
        }
        return listaTiene;
    }

    /**
     * Funcion que obtiene el listado de todos los tiene
     * 
     * @return lisa total
     * @throws PersistenciaException controlado
     */
    public List<Tiene> obtenerListadoTiene() throws PersistenciaException {
        String sql = "SELECT * FROM TIENE ";
        return obtenerListadoTiene(sql);
    }

    /**
     * Funcion que obtiene tiene
     * 
     * @param tiene
     * @return lista total
     * @throws PersistenciaException controlado
     */
    public Tiene obtenerTiene(int idEntrenador) throws PersistenciaException {
        Tiene tiene = null;
        ArrayList<Tiene> listaTiene = null;
        String sql = "SELECT * FROM POKEMON WHERE numero_pokedex = ";
        sql = sql + "'" + idEntrenador + "';";
        listaTiene = obtenerListadoTiene(sql);
        if (!listaTiene.isEmpty()) {
            tiene = listaTiene.get(0);
        }
        return tiene;
    }

    /**
     * Metodo encargado de realizar la insercion de tipo
     * 
     * @param tipo a insertar
     * @throws PersistenciaException error controlado
     */
    public void insertarTipo(Tipo tipo) throws PersistenciaException {

        String sql = "INSERT INTO TIPO VALUES (" + tipo.getNombre() + ",'" + tipo.getColor() + ");";
        actualizar(sql);
    }

    /**
     * Metodo encargado de realizar la eliminacion
     * 
     * @param tipo a eliminar
     * @throws PersistenciaException error controlado
     */
    public void eliminarTipo(Tipo tipo) throws PersistenciaException {
        String sql = "DELETE TIPO FROM TIPO WHERE nombre = " + tipo.getNombre() + ";";
        actualizar(sql);
    }

    /**
     * Metodo encargado de realizar la modificacion
     * 
     * @param tipo a actualizar
     * @throws PersistenciaException error controlado
     */
    public void modificarTipo(Tipo tipo) throws PersistenciaException {
        String sql = "UPDATE TIPO SET " + "Color = '" + tipo.getColor() + " WHERE nombre = " + tipo.getNombre() + ";";
        actualizar(sql);
    }

    /**
     * Funcion que realiza la consulta sobre la BBDD y la tabla TIPO
     * 
     * @param sql de la consulta
     * @return lista de resultados
     * @throws PersistenciaException controlado
     */
    private ArrayList<Tipo> obtenerListadoTipo(String sql) throws PersistenciaException {
        ArrayList<Tipo> listaTipo = new ArrayList<>();

        Tipo tipo = null;
        Statement statement = null;
        ResultSet resultSet = null;
        Connection connection = null;
        try {
            connection = getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                String nombre = resultSet.getString("nombre");
                String color = resultSet.getString("color");
                tipo = new Tipo(nombre, color);
                listaTipo.add(tipo);
            }
        } catch (Exception exception) {
            throw new PersistenciaException("Se ha producido un error realizando la consulta", exception);
        } finally {
            closeConecction(connection, statement, resultSet);
        }
        return listaTipo;
    }

    /**
     * Funcion que obtiene el listado de todos los tipo
     * 
     * @return lisa total
     * @throws PersistenciaException controlado
     */
    public List<Tipo> obtenerListadoTipo() throws PersistenciaException {
        String sql = "SELECT * FROM TIPO";
        return obtenerListadoTipo(sql);
    }

    /**
     * Funcion que obtiene los tipo de un pokemon
     * 
     * @param numero de la pokedex del pokemon
     * @return lista total
     * @throws PersistenciaException controlado
     */
    public Tipo obtenerTipo(String nombre) throws PersistenciaException {
        Tipo tipo = null;
        ArrayList<Tipo> listaTipo = null;
        String sql = "SELECT * FROM TIPO WHERE nombre = ";
        sql = sql + "'" + nombre + "';";
        listaTipo = obtenerListadoTipo(sql);
        if (!listaTipo.isEmpty()) {
            tipo = listaTipo.get(0);
        }
        return tipo;
    }

    /**
     * Metodo encargado de realizar la insercion de un villano
     * 
     * @param villano a insertar
     * @throws PersistenciaException error controlado
     */
    public void insertarVillano(Villano villano) throws PersistenciaException {

        String sql = "INSERT INTO VILLANO VALUES (" + villano.getIdEntrenador() + ",'" + villano.getProposito() + "',"
                + ");";
        actualizar(sql);
    }

    /**
     * Metodo encargado de realizar la modificacion
     * 
     * @param villano a actualizar
     * @throws PersistenciaException error controlado
     */
    public void modificarVillano(Villano villano) throws PersistenciaException {
        String sql = "UPDATE VILLANO SET " + "propocito = '" + villano.getProposito() + " WHERE Id_Entrenador = "
                + villano.getIdEntrenador() + ";";
        actualizar(sql);
    }

    /**
     * Funcion que realiza la consulta sobre la BBDD y la tabla VILLANO
     * 
     * @param sql de la consulta
     * @return lista de resultados
     * @throws PersistenciaException controlado
     */
    private ArrayList<Villano> obtenerListadoVillano(String sql) throws PersistenciaException {
        ArrayList<Villano> listaVillano = new ArrayList<>();

        Villano villano = null;
        Statement statement = null;
        ResultSet resultSet = null;
        Connection connection = null;
        try {
            connection = getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                int idEntrenador = resultSet.getInt("id_Entrenador");
                String proposito = resultSet.getString("proposito");
                villano = new Villano(idEntrenador, proposito);
                listaVillano.add(villano);
            }
        } catch (Exception exception) {
            throw new PersistenciaException("Se ha producido un error realizando la consulta", exception);
        } finally {
            closeConecction(connection, statement, resultSet);
        }
        return listaVillano;
    }

    /**
     * Funcion que obtiene el listado de todos los villano
     * 
     * @return lisa total
     * @throws PersistenciaException controlado
     */
    public List<Villano> obtenerListadoVillano() throws PersistenciaException {
        String sql = "SELECT * FROMVILLANO ";
        return obtenerListadoVillano(sql);
    }

    /**
     * Funcion que obtiene un  villano
     * 
     * @param villano
     * @return lista total
     * @throws PersistenciaException controlado
     */
    public Villano obtenerVillano(int idEntrenador) throws PersistenciaException {
        Villano villano = null;
        ArrayList<Villano> listaVillano = null;
        String sql = "SELECT * FROM VILLANO WHERE id_entrenador = ";
        sql = sql + "'" + idEntrenador + "';";
        listaVillano = obtenerListadoVillano(sql);
        if (!listaVillano.isEmpty()) {
            villano = listaVillano.get(0);
        }
        return villano;
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
