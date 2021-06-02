package es.iespuertolacruz.pokemon.modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import es.iespuertolacruz.pokemon.api.ObjetoComun;
import es.iespuertolacruz.pokemon.excepciones.FicheroException;
import es.iespuertolacruz.pokemon.excepciones.PersistenciaException;

/**
 * Clase modelo de los objetos comunes
 */
public class ObjetoComunModelo {

    // Variables de clase

    DdBbSqLite persistencia;
    private static final String TABLA = "OBJETO_COMUN";
    private static final String CLAVE = "id_objeto";

    // Constructores

    /**
     * Constructor de ObjetoComunModelo donde inicializa DdBbSqLite
     * 
     * @throws PersistenciaException con error controlado
     * @throws FicheroException      con error controlado
     */
    public ObjetoComunModelo() throws PersistenciaException, FicheroException {
        persistencia = new DdBbSqLite(TABLA, null, null);
    }

    // Metodos y funciones

    /**
     * Metodo que inserta objetoComun en la base de datos
     * 
     * @param objetoComun a insertar
     * @throws PersistenciaException error controlado
     */
    public void insertar(ObjetoComun objetoComun) throws PersistenciaException {
        String sql = "INSERT INTO " + TABLA + " VALUES (" + objetoComun.getIdObjeto() + ",'" + objetoComun.getEfecto()
                + "');";
        persistencia.update(sql);
    }

    /***
     * Metodo que elimina objetoComun de la base de datos
     * 
     * @param objetoComun a eliminar
     * @throws PersistenciaException error controlado
     */
    public void eliminar(ObjetoComun objetoComun) throws PersistenciaException {
        String sql = "DELETE FROM " + TABLA + " WHERE " + CLAVE + " = " + objetoComun.getIdObjeto() + ";";
        persistencia.update(sql);
    }

    /**
     * Metodo que modifica objetoComun de la base de datos
     * 
     * @param objetoComun a modificar
     * @throws PersistenciaException error controlado
     */
    public void modificar(ObjetoComun objetoComun) throws PersistenciaException {
        String sql = "UPDATE " + TABLA + " SET efecto = '" + objetoComun.getEfecto() + "' WHERE " + CLAVE + " = "
                + objetoComun.getIdObjeto() + ";";
        persistencia.update(sql);
    }

    /**
     * Funcion encargada de obtener objetoComun
     * 
     * @param idObjeto del objetoComun
     * @return objetoComun buscado
     * @throws PersistenciaException con error controlado
     */
    public ObjetoComun buscar(int idObjeto) throws PersistenciaException {
        ObjetoComun objetoComun = null;
        String sql = "SELECT * FROM " + TABLA + " WHERE " + CLAVE + " = " + idObjeto + ";";
        ArrayList<ObjetoComun> lista = transformarAObjetoComun(sql);
        if (!lista.isEmpty()) {
            objetoComun = lista.get(0);
        }
        return objetoComun;
    }

    /**
     * Funcion que realiza una consulta sobre una sentencia sql dada
     * 
     * @param sql de la consulta
     * @return lista resultados (0..n) Usuasios
     * @throws PersistenciaException error controlado
     */
    private ArrayList<ObjetoComun> transformarAObjetoComun(String sql) throws PersistenciaException {
        ArrayList<ObjetoComun> lista = new ArrayList<>();
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Connection connection = null;
        try {
            connection = persistencia.getConnection();
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                ObjetoComun objetoComun = new ObjetoComun();
                objetoComun.setIdObjeto(resultSet.getInt(CLAVE));
                objetoComun.setEfecto(resultSet.getString("efecto"));
                lista.add(objetoComun);
            }
        } catch (SQLException exception) {
            throw new PersistenciaException("Se ha producido un error en la busqueda", exception);
        } finally {
            persistencia.closeConecction(connection, statement, resultSet);
        }
        return lista;
    }
}
