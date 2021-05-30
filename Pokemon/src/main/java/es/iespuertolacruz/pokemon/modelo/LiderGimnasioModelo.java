package es.iespuertolacruz.pokemon.modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import es.iespuertolacruz.pokemon.api.LiderGimnasio;
import es.iespuertolacruz.pokemon.excepciones.PersistenciaException;

public class LiderGimnasioModelo {
    // Variables de clase

    DdBbSqLite persistencia;
    private static final String TABLA = "ALTO_MANDO";
    private static final String CLAVE = "id_entrenador";

    // Constructores
    public LiderGimnasioModelo(DdBbSqLite persistencia) {
        this.persistencia = persistencia;
    }

    // Metodos y funciones

    /**
     * Metodo que inserta liderGimnasio en la base de datos
     * 
     * @param liderGimnasio
     * @throws PersistenciaException
     */
    public void insertar(LiderGimnasio liderGimnasio) throws PersistenciaException {
        String sql = "INSERT INTO " + TABLA + " VALUES (" + liderGimnasio.getIdEntrenador() + ","
                + liderGimnasio.getMedalla() + "');";
        persistencia.update(sql);
    }

    /***
     * Metodo que elimina liderGimnasio de la base de datos
     * 
     * @param liderGimnasio
     * @throws PersistenciaException
     */
    public void eliminar(LiderGimnasio liderGimnasio) throws PersistenciaException {
        String sql = "DELETE FROM " + TABLA + " WHERE " + CLAVE + " = " + liderGimnasio.getIdEntrenador() + ";";
        persistencia.update(sql);
    }

    /**
     * Metodo que modifica liderGimnasio de la base de datos
     * 
     * @param liderGimnasio a modificar
     * @throws PersistenciaException error controlado
     */
    public void modificar(LiderGimnasio liderGimnasio) throws PersistenciaException {
        String sql = "UPDATE " + TABLA + " SET Medalla = " + liderGimnasio.getMedalla() + "WHERE " + CLAVE
                + " = " + liderGimnasio.getIdEntrenador() + ";";
        persistencia.update(sql);
    }

    /**
     * Funcion encargada de obtener liderGimnasio
     * 
     * @param IdEntrenador del liderGimnasio
     * @return liderGimnasio buscado
     * @throws PersistenciaException con error controlado
     */
    public LiderGimnasio buscar(int IdEntrenador) throws PersistenciaException {
        LiderGimnasio liderGimnasio = null;
        String sql = "SELECT * FROM " + TABLA + " WHERE " + CLAVE + " = " + IdEntrenador + ";";
        ArrayList<LiderGimnasio> lista = transformarALiderGimnasio(sql);
        if (!lista.isEmpty()) {
            liderGimnasio = lista.get(0);
        }
        return liderGimnasio;
    }

    /**
     * Funcion que realiza una consulta sobre una sentencia sql dada
     * 
     * @param sql de la consulta
     * @return lista resultados (0..n) Usuasios
     * @throws PersistenciaException error controlado
     */
    private ArrayList<LiderGimnasio> transformarALiderGimnasio(String sql) throws PersistenciaException {
        ArrayList<LiderGimnasio> lista = new ArrayList<>();
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Connection connection = null;
        try {
            connection = persistencia.getConnection();
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                LiderGimnasio liderGimnasio = new LiderGimnasio();
                liderGimnasio.setIdEntrenador(resultSet.getInt(CLAVE));
                liderGimnasio.setMedalla(resultSet.getInt("Medalla"));
                lista.add(liderGimnasio);
            }
        } catch (SQLException exception) {
            throw new PersistenciaException("Se ha producido un error en la busqueda", exception);
        } finally {
            persistencia.closeConecction(connection, statement, resultSet);
        }
        return lista;
    }
}
