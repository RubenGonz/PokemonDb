package es.iespuertolacruz.pokemon.modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import es.iespuertolacruz.pokemon.api.AltoMando;
import es.iespuertolacruz.pokemon.excepciones.FicheroException;
import es.iespuertolacruz.pokemon.excepciones.PersistenciaException;

/**
 * Clase principal del modelo de AltoMando
 */
public class AltoMandoModelo {

    // Variables de clase

    DdBbSqLite persistencia;
    private static final String TABLA = "ALTO_MANDO";
    private static final String CLAVE = "id_entrenador";

    // Constructores
    public AltoMandoModelo() throws PersistenciaException, FicheroException {
        persistencia = new DdBbSqLite(TABLA, null, null);
    }

    // Metodos y funciones

    /**
     * Metodo que inserta altoMando en la base de datos
     * 
     * @param altoMando
     * @throws PersistenciaException
     */
    public void insertar(AltoMando altoMando)throws PersistenciaException  {
        String sql = "INSERT INTO " + TABLA + " VALUES (" 
        + altoMando.getIdEntrenador() + ","
        + altoMando.getTipoPrincipal() + "');";
        persistencia.update(sql);
    }
    
    /***
     * Metodo que elimina altoMando de la base de datos
     * 
     * @param altoMando
     * @throws PersistenciaException
     */
    public void eliminar(AltoMando altoMando) throws PersistenciaException {
        String sql = "DELETE FROM " + TABLA + " WHERE " + CLAVE + " = " + altoMando.getIdEntrenador() + ";";
        persistencia.update(sql);
    }

    /**
     * Metodo que modifica altoMando de la base de datos
     * 
     * @param altoMando a modificar
     * @throws PersistenciaException error controlado
     */
    public void modificar(AltoMando altoMando) throws PersistenciaException {
        String sql = "UPDATE " + TABLA + " SET TipoPrincipal = " + altoMando.getTipoPrincipal() +
         "WHERE " + CLAVE + " = " + altoMando.getIdEntrenador() + ";";
        persistencia.update(sql);
    }

    /**
     * Funcion encargada de obtener altoMando
     * 
     * @param IdEntrenador del altoMando
     * @return altoMando buscado
     * @throws PersistenciaException con error controlado
     */
    public AltoMando buscar(int IdEntrenador) throws PersistenciaException {
        AltoMando altoMando = null;
        String sql = "SELECT * FROM " + TABLA + " WHERE " + CLAVE + " = " + IdEntrenador+ ";";
        ArrayList<AltoMando> lista = transformarAltoMando(sql);
        if (!lista.isEmpty()) {
            altoMando = lista.get(0);
        }
        return altoMando;
    }

    /**
     * Funcion que realiza una consulta sobre una sentencia sql dada
     * 
     * @param sql de la consulta
     * @return lista resultados (0..n) Usuasios
     * @throws PersistenciaException error controlado
     */
    private ArrayList<AltoMando> transformarAltoMando(String sql) throws PersistenciaException {
        ArrayList<AltoMando> lista = new ArrayList<>();
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Connection connection = null;
        try {
            connection = persistencia.getConnection();
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
               AltoMando altoMando = new AltoMando();
               altoMando.setIdEntrenador(resultSet.getInt(CLAVE));
               altoMando.setTipoPrincipal(resultSet.getString("TipoPrincipal"));
                lista.add(altoMando);
            }
        } catch (SQLException exception) {
            throw new PersistenciaException("Se ha producido un error en la busqueda", exception);
        } finally {
            persistencia.closeConecction(connection, statement, resultSet);
        }
        return lista;
    }
}
