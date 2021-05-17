package es.iespuertolacruz.pokemon.modelo;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

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
    public void modificarObjeto(AltoMando altoMando) throws PersistenciaException {
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
    public void modificarObjeto(CampeonLiga campeonLiga) throws PersistenciaException {
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
    public void insertarCaracteristicas(Conoce conoce) throws PersistenciaException {

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
    public void eliminarObjeto(Conoce conoce) throws PersistenciaException {
        String sql = "DELETE CONOCE FROM CONOCE WHERE id_movimiento = " + conoce.getIdMovimiento() + ";";
        actualizar(sql);
    }

    /**
     * Metodo encargado de realizar la modificacion
     * 
     * @param conoce a actualizar
     * @throws PersistenciaException error controlado
     */
    public void modificarObjeto(Conoce conoce) throws PersistenciaException {
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
    public void eliminarObjeto(EntrenadorCasual entrenadorCasual) throws PersistenciaException {
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
        String sql = "SELECT * FROM POKEMON WHERE numero_pokedex = ";
        sql = sql + "'" + id + "';";
        listaEstado = obtenerListadoEstado(sql);
        if (!listaEstado.isEmpty()) {
            estado = listaEstado.get(0);
        }
        return estado;
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
