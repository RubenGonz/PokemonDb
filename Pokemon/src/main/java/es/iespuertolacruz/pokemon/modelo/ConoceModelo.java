package es.iespuertolacruz.pokemon.modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import es.iespuertolacruz.pokemon.api.Conoce;
import es.iespuertolacruz.pokemon.excepciones.FicheroException;
import es.iespuertolacruz.pokemon.excepciones.PersistenciaException;

/**
 * Clase principal del modelo de conoce
 */
public class ConoceModelo {
    
    // Variables de clase

    DdBbSqLite persistencia;
    private static final String TABLA = "CONOCE";
    private static final String CLAVE = "IdMovimiento";

    // Constructores
    
    public ConoceModelo() throws PersistenciaException, FicheroException {
        persistencia = new DdBbSqLite(TABLA, null, null);
    }

    // Metodos y funciones

    /**
     * Metodo que inserta conoce en la base de datos
     * 
     * @param conoce
     * @throws PersistenciaException
     */
    public void insertar(Conoce conoce) throws PersistenciaException {
        String sql = "INSERT INTO " + TABLA + " VALUES (" + conoce.getIdMovimiento() + ","
                + conoce.getNumeroPokedex() + "');";
        persistencia.update(sql);
    }

    /***
     * Metodo que elimina conoce de la base de datos
     * 
     * @param conoce
     * @throws PersistenciaException
     */
    public void eliminar(Conoce conoce) throws PersistenciaException {
        String sql = "DELETE FROM " + TABLA + " WHERE " + CLAVE + " = " + conoce.getIdMovimiento()+ conoce
                .getNumeroPokedex() + ";";
        persistencia.update(sql);
    }

    /**
     * Metodo que modifica conoce de la base de datos
     * 
     * @param conoce a modificar
     * @throws PersistenciaException error controlado
     */
    public void modificar(Conoce conoce) throws PersistenciaException {
        String sql = "UPDATE " + TABLA + " SET numero_pokedex = " + conoce.getNumeroPokedex() + "WHERE " + CLAVE
                + " = " + conoce.getIdMovimiento() + conoce.getNumeroPokedex() + ";";
        persistencia.update(sql);
    }

    /**
     * Funcion encargada de obtener conoce
     * 
     * @param IdMovimiento del conoce
     * @return conoce buscado
     * @throws PersistenciaException con error controlado
     */
    public Conoce buscar(int IdMovimiento) throws PersistenciaException {
        Conoce conoce = null;
        String sql = "SELECT * FROM " + TABLA + " WHERE " + CLAVE + " = " + IdMovimiento + ";";
        ArrayList<Conoce> lista = transformarAConoce(sql);
        if (!lista.isEmpty()) {
            conoce = lista.get(0);
        }
        return conoce;
    }
    
    /**
     * Funcion que realiza una consulta sobre una sentencia sql dada
     * 
     * @param sql de la consulta
     * @return lista resultados (0..n) Usuasios
     * @throws PersistenciaException error controlado
     */
    private ArrayList<Conoce> transformarAConoce(String sql) throws PersistenciaException {
        ArrayList<Conoce> lista = new ArrayList<>();
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Connection connection = null;
        try {
            connection = persistencia.getConnection();
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Conoce conoce= new Conoce();
                conoce.setIdMovimiento(resultSet.getInt(CLAVE));
                conoce.setIdMovimiento(resultSet.getInt(CLAVE));
                lista.add(conoce);
            }
        } catch (SQLException exception) {
            throw new PersistenciaException("Se ha producido un error en la busqueda", exception);
        } finally {
            persistencia.closeConecction(connection, statement, resultSet);
        }
        return lista;
    }

}
