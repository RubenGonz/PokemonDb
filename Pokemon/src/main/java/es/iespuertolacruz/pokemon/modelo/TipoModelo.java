package es.iespuertolacruz.pokemon.modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import es.iespuertolacruz.pokemon.api.Tipo;
import es.iespuertolacruz.pokemon.excepciones.PersistenciaException;

public class TipoModelo {

    //Variables de clase

    DdBbSqLite persistencia;
    private static final String TABLA = "TIPO";
    private static final String CLAVE = "nombre";

    // Constructores

    public TipoModelo() throws PersistenciaException {
        persistencia = new DdBbSqLite(null, null);
    }

    //Metodos y funciones
    
    /**
     * Metodo que inserta tipos en la base de datos
     * 
     * @param tipo a insertar
     * @throws PersistenciaException error controlado
     */
    public void insertar(Tipo tipo) throws PersistenciaException {
        String sql = "INSERT INTO " + TABLA + " VALUES ('" + 
        tipo.getNombre() + "','" + 
        tipo.getColor()+ "');";
        persistencia.update(sql);
    }

    /**
     * Metodo que elimina un tipo de la base de datos
     * 
     * @param tipo a eliminar
     * @throws PersistenciaException errror controlado
     */
    public void eliminar(Tipo tipo) throws PersistenciaException {
        String sql = "DELETE FROM " + TABLA + " WHERE " + CLAVE + " = '" + 
        tipo.getNombre() + "';";
        persistencia.update(sql);
    }
    
    /**
     * Metodo que modifica un Tipo de la base de datos
     * 
     * @param tipo a modificar
     * @throws PersistenciaException error controlado
     */
    public void modificar(Tipo tipo) throws PersistenciaException {
        String sql = "UPDATE " + TABLA + 
        " SET color = '" + tipo.getColor() +
        "' WHERE " + CLAVE + " = '" + tipo.getNombre()+"';";
        persistencia.update(sql);
    }

    /**
     * Funcion encargada de obtener un tipo
     * 
     * @param nombre del tipo
     * @return Tipo buscado
     * @throws PersistenciaException con error controlado
     */
    public Tipo buscar(String nombre) throws PersistenciaException {
        Tipo tipo = null;
        String sql = "SELECT * FROM TIPO WHERE nombre = '" + nombre + "';";
        ArrayList<Tipo> lista = transformarATipo(sql);
        if (!lista.isEmpty()) {
            tipo = lista.get(0);
        }
        return tipo;
    }

    /**
     * Funcion que transforma el resultado de la consulta a una lista de tipos
     * 
     * @param sql de la consulta
     * @return lista resultados (0..n) tipos
     * @throws PersistenciaException error controlado
     */
    private ArrayList<Tipo> transformarATipo(String sql) throws PersistenciaException {
        ArrayList<Tipo> lista = new ArrayList<>();
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Connection connection = null;
        try {
            connection = persistencia.getConnection();
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Tipo tipo = new Tipo();
                tipo.setNombre(resultSet.getString(CLAVE));
                tipo.setColor(resultSet.getString("color"));
                lista.add(tipo);
            }
        } catch (SQLException exception) {
            throw new PersistenciaException("Se ha producido un error en la busqueda", exception);
        } finally {
            persistencia.closeConecction(connection, statement, resultSet);
        }
        return lista;
    }

}
