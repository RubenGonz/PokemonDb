package es.iespuertolacruz.pokemon.modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import es.iespuertolacruz.pokemon.api.Tiene;
import es.iespuertolacruz.pokemon.excepciones.PersistenciaException;

public class TieneModelo {

    // Variables de clase

    DdBbSqLite persistencia;
    private static final String TABLA = "TIENE";
    private static final String CLAVE = "id_entrenador";

    // Constructores
    public TieneModelo(DdBbSqLite persistencia) {
        this.persistencia = persistencia;
    }

    // Metodos y funciones

    /**
     * Metodo que inserta tiene en la base de datos
     * 
     * @param tiene
     * @throws PersistenciaException
     */
    public void insertar(Tiene tiene) throws PersistenciaException {
        String sql = "INSERT INTO " + TABLA + " VALUES (" + tiene.getIdEntrenador() + "," + tiene.getNumeroPokedex()
                + "," + tiene.getCantidad() + "');";
        persistencia.update(sql);
    }

    /***
     * Metodo que elimina tiene de la base de datos
     * 
     * @param tiene
     * @throws PersistenciaException
     */
    public void eliminar(Tiene tiene) throws PersistenciaException {
        String sql = "DELETE FROM " + TABLA + " WHERE " + CLAVE + " = " + tiene.getIdEntrenador()
                + tiene.getNumeroPokedex() + ";";
        persistencia.update(sql);
    }

    /**
     * Metodo que modifica tiene de la base de datos
     * 
     * @param tiene a modificar
     * @throws PersistenciaException error controlado
     */
    public void modificar(Tiene tiene) throws PersistenciaException {
        String sql = "UPDATE " + TABLA + " SET Cantidad = " + tiene.getCantidad() + "WHERE " + CLAVE + " = "
                + tiene.getIdEntrenador()+ tiene.getNumeroPokedex() + ";";
        persistencia.update(sql);
    }

    /**
     * Funcion encargada de obtener tiene
     * 
     * @param IdEntrenador del tiene
     * @return tiene buscado
     * @throws PersistenciaException con error controlado
     */
    public Tiene buscar(int IdEntrenador , int NumeroPokedex) throws PersistenciaException {
        Tiene tiene = null;
        String sql = "SELECT * FROM " + TABLA + " WHERE " + CLAVE + " = " + IdEntrenador+ NumeroPokedex + ";";
        ArrayList<Tiene> lista = transformarATiene(sql);
        if (!lista.isEmpty()) {
            tiene = lista.get(0);
        }
        return tiene;
    }

    /**
     * Funcion que realiza una consulta sobre una sentencia sql dada
     * 
     * @param sql de la consulta
     * @return lista resultados (0..n) Usuasios
     * @throws PersistenciaException error controlado
     */
    private ArrayList<Tiene> transformarATiene(String sql) throws PersistenciaException {
        ArrayList<Tiene> lista = new ArrayList<>();
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Connection connection = null;
        try {
            connection = persistencia.getConnection();
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Tiene tiene = new Tiene();
                tiene.setIdEntrenador(resultSet.getInt(CLAVE));
                tiene.setNumeroPokedex(resultSet.getInt(CLAVE));
                tiene.setCantidad(resultSet.getInt("Cantidad"));
                lista.add(tiene);
            }
        } catch (SQLException exception) {
            throw new PersistenciaException("Se ha producido un error en la busqueda", exception);
        } finally {
            persistencia.closeConecction(connection, statement, resultSet);
        }
        return lista;
    }
}
