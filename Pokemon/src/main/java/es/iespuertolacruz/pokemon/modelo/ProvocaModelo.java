package es.iespuertolacruz.pokemon.modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import es.iespuertolacruz.pokemon.api.Provoca;
import es.iespuertolacruz.pokemon.excepciones.FicheroException;
import es.iespuertolacruz.pokemon.excepciones.PersistenciaException;

public class ProvocaModelo {

    // Variables de clase

    DdBbSqLite persistencia;
    private static final String TABLA = "PROVOCA";
    private static final String CLAVEPRI = "id_movimiento";
    private static final String CLAVESEC = "id_estado";

    // Constructores

    /**
     * Constructor de ProvocaModelo donde inicializa DdBbSqLite
     * 
     * @throws PersistenciaException con error controlado
     * @throws FicheroException      con error controlado
     */
    public ProvocaModelo() throws PersistenciaException, FicheroException {
        persistencia = new DdBbSqLite(TABLA, null, null);
    }

    // Metodos y funciones

    /**
     * Metodo que inserta provoca en la base de datos
     * 
     * @param provoca a insertar
     * @throws PersistenciaException error controlado
     */
    public void insertar(Provoca provoca) throws PersistenciaException {
        String sql = "INSERT INTO " + TABLA + " VALUES (" + provoca.getIdMovimiento() + "," + provoca.getIdEstado()
                + ");";
        persistencia.update(sql);
    }

    /**
     * Metodo que elimina provoca de la base de datos
     * 
     * @param provoca a eliminar
     * @throws PersistenciaException error controlado
     */
    public void eliminar(Provoca provoca) throws PersistenciaException {
        String sql = "DELETE FROM " + TABLA + " WHERE " + CLAVEPRI + " = " + provoca.getIdMovimiento() + " AND "
                + CLAVESEC + " = " + provoca.getIdEstado() + ";";
        persistencia.update(sql);
    }

    /**
     * Metodo que modifica provoca de la base de datos
     * 
     * @param provoca a modificar
     * @throws PersistenciaException error controlado
     */
    public void modificar(Provoca provoca) throws PersistenciaException {
        String sql = "UPDATE " + TABLA + " SET " + CLAVEPRI + " = " + provoca.getIdMovimiento() + "," + CLAVESEC + " = "
                + provoca.getIdEstado() + " WHERE " + CLAVEPRI + " = " + provoca.getIdMovimiento() + " AND " + CLAVESEC
                + " = " + provoca.getIdEstado() + ";";
        persistencia.update(sql);
    }

    /**
     * Funcion encargada de obtener provoca
     * 
     * @param IdEntrenador del provoca
     * @return provoca buscado
     * @throws PersistenciaException con error controlado
     */
    public Provoca buscar(int idMovimiento, int idEstado) throws PersistenciaException {
        Provoca provoca = null;
        String sql = "SELECT * FROM " + TABLA + " WHERE " + CLAVEPRI + " = " + idMovimiento + " AND " + CLAVESEC + " = " + idEstado + ";";
        ArrayList<Provoca> lista = transformarAProvoca(sql);
        if (!lista.isEmpty()) {
            provoca = lista.get(0);
        }
        return provoca;
    }

    /**
     * Funcion que realiza una consulta sobre una sentencia sql dada
     * 
     * @param sql de la consulta
     * @return lista resultados (0..n) Usuasios
     * @throws PersistenciaException error controlado
     */
    private ArrayList<Provoca> transformarAProvoca(String sql) throws PersistenciaException {
        ArrayList<Provoca> lista = new ArrayList<>();
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Connection connection = null;
        try {
            connection = persistencia.getConnection();
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Provoca provoca = new Provoca();
                provoca.setIdEstado(resultSet.getInt(CLAVEPRI));
                provoca.setIdMovimiento(resultSet.getInt(CLAVESEC));
                lista.add(provoca);
            }
        } catch (SQLException exception) {
            throw new PersistenciaException("Se ha producido un error en la busqueda", exception);
        } finally {
            persistencia.closeConecction(connection, statement, resultSet);
        }
        return lista;
    }
}
