package es.iespuertolacruz.pokemon.modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import es.iespuertolacruz.pokemon.api.Maquina;
import es.iespuertolacruz.pokemon.excepciones.PersistenciaException;

public class MaquinaModelo {
    // Variables de clase

    DdBbSqLite persistencia;
    private static final String TABLA = "MAQUINA";
    private static final String CLAVE = "id_objeto";

    // Constructores
    public MaquinaModelo(DdBbSqLite persistencia) {
        this.persistencia = persistencia;
    }

    // Metodos y funciones

    /**
     * Metodo que inserta maquina en la base de datos
     * 
     * @param maquina
     * @throws PersistenciaException
     */
    public void insertar(Maquina maquina) throws PersistenciaException {
        String sql = "INSERT INTO " + TABLA + " VALUES (" + maquina.getIdMovimiento() + ","
                + maquina.getIdObjeto() + "');";
        persistencia.update(sql);
    }

    /***
     * Metodo que elimina maquina de la base de datos
     * 
     * @param maquina
     * @throws PersistenciaException
     */
    public void eliminar(Maquina maquina) throws PersistenciaException {
        String sql = "DELETE FROM " + TABLA + " WHERE " + CLAVE + " = " + maquina.getIdMovimiento() + ";";
        persistencia.update(sql);
    }

    /**
     * Metodo que modifica maquina de la base de datos
     * 
     * @param maquina a modificar
     * @throws PersistenciaException error controlado
     */
    public void modificar(Maquina maquina) throws PersistenciaException {
        String sql = "UPDATE " + TABLA + " SET IdObjeto = " + maquina.getIdMovimiento() + "WHERE " + CLAVE
                + " = " + maquina.getIdMovimiento() + maquina.getIdObjeto()+ ";";
        persistencia.update(sql);
    }

    /**
     * Funcion encargada de obtener maquina
     * 
     * @param IdEntrenador del maquina
     * @return maquina buscado
     * @throws PersistenciaException con error controlado
     */
    public Maquina buscar(int IdEntrenador) throws PersistenciaException {
        Maquina maquina = null;
        String sql = "SELECT * FROM " + TABLA + " WHERE " + CLAVE + " = " + IdEntrenador + ";";
        ArrayList<Maquina> lista = transformarAMaquina(sql);
        if (!lista.isEmpty()) {
            maquina = lista.get(0);
        }
        return maquina;
    }

    /**
     * Funcion que realiza una consulta sobre una sentencia sql dada
     * 
     * @param sql de la consulta
     * @return lista resultados (0..n) Usuasios
     * @throws PersistenciaException error controlado
     */
    private ArrayList<Maquina> transformarAMaquina(String sql) throws PersistenciaException {
        ArrayList<Maquina> lista = new ArrayList<>();
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Connection connection = null;
        try {
            connection = persistencia.getConnection();
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Maquina maquina = new Maquina();
                maquina.setIdMovimiento(resultSet.getInt(CLAVE));
                maquina.setIdObjeto(resultSet.getInt(CLAVE));
                lista.add(maquina);
            }
        } catch (SQLException exception) {
            throw new PersistenciaException("Se ha producido un error en la busqueda", exception);
        } finally {
            persistencia.closeConecction(connection, statement, resultSet);
        }
        return lista;
    }
}
