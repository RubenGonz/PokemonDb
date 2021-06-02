package es.iespuertolacruz.pokemon.modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import es.iespuertolacruz.pokemon.api.Objeto;
import es.iespuertolacruz.pokemon.excepciones.FicheroException;
import es.iespuertolacruz.pokemon.excepciones.PersistenciaException;

/**
 * Clase principal del modelo objeto
 */
public class ObjetoModelo {

    // Variables de clase

    DdBbSqLite persistencia;
    private static final String TABLA = "OBJETO";
    private static final String CLAVE = "id_objeto";

    // Constructores

    /**
     * Constructor de ObjetoModelo donde inicializa DdBbSqLite
     * 
     * @throws PersistenciaException con error controlado
     * @throws FicheroException      con error controlado
     */
    public ObjetoModelo() throws PersistenciaException, FicheroException {
        persistencia = new DdBbSqLite(TABLA, null, null);
    }

    // Metodos y funciones

    /**
     * Metodo que inserta objetos en la base de datos
     * 
     * @param objeto a insertar
     * @throws PersistenciaException error controlado
     */
    public void insertar(Objeto objeto) throws PersistenciaException {
        String sql = "INSERT INTO " + TABLA + " VALUES (" + objeto.getId() + ",'" + objeto.getNombre() + "','"
                + objeto.getModoObtencion() + "');";
        persistencia.update(sql);
    }

    /**
     * Metodo que elimina una Objeto de la base de datos
     * 
     * @param objeto a eliminar
     * @throws PersistenciaException error controlado
     */
    public void eliminar(Objeto objeto) throws PersistenciaException {
        String sql = "DELETE FROM " + TABLA + " WHERE " + CLAVE + " = " + objeto.getId() + ";";
        persistencia.update(sql);
    }

    /**
     * Metodo que modifica una Objeto de la base de datos
     * 
     * @param objeto a modificar
     * @throws PersistenciaException error controlado
     */
    public void modificar(Objeto objeto) throws PersistenciaException {
        String sql = "UPDATE " + TABLA + " SET nombre = '" + objeto.getNombre() + "'," + "modo_obtencion = '"
                + objeto.getModoObtencion() + "'" + "WHERE " + CLAVE + " = " + objeto.getId() + ";";
        persistencia.update(sql);
    }

    /**
     * Funcion encargada de obtener una objeto
     * 
     * @param id_objeto del pokemon
     * @return Pokemon buscado
     * @throws PersistenciaException con error controlado
     */
    public Objeto buscar(int idObjeto) throws PersistenciaException {
        Objeto objeto = null;
        String sql = "SELECT * FROM " + TABLA + " WHERE " + CLAVE + " = " + idObjeto + ";";
        ArrayList<Objeto> lista = transformarAObjeto(sql);
        if (!lista.isEmpty()) {
            objeto = lista.get(0);
        }
        return objeto;
    }

    /**
     * Funcion que realiza una consulta sobre una sentencia sql dada
     * 
     * @param sql de la consulta
     * @return lista resultados (0..n) Usuasios
     * @throws PersistenciaException error controlado
     */
    private ArrayList<Objeto> transformarAObjeto(String sql) throws PersistenciaException {
        ArrayList<Objeto> lista = new ArrayList<>();
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Connection connection = null;
        try {
            connection = persistencia.getConnection();
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Objeto objeto = new Objeto();
                objeto.setId(resultSet.getInt(CLAVE));
                objeto.setNombre(resultSet.getString("nombre"));
                objeto.setModoObtencion(resultSet.getString("modo_obtencion"));
                lista.add(objeto);
            }
        } catch (SQLException exception) {
            throw new PersistenciaException("Se ha producido un error en la busqueda", exception);
        } finally {
            persistencia.closeConecction(connection, statement, resultSet);
        }
        return lista;
    }
}
