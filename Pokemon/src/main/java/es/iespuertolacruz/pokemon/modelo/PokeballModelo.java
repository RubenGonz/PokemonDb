package es.iespuertolacruz.pokemon.modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import es.iespuertolacruz.pokemon.api.Pokeball;
import es.iespuertolacruz.pokemon.excepciones.PersistenciaException;

public class PokeballModelo {
    // Variables de clase

    DdBbSqLite persistencia;
    private static final String TABLA = "POKEBALL";
    private static final String CLAVE = "id_objeto";

    // Constructores
    public PokeballModelo(DdBbSqLite persistencia) {
        this.persistencia = persistencia;
    }

    // Metodos y funciones

    /**
     * Metodo que inserta pokeball en la base de datos
     * 
     * @param pokeball
     * @throws PersistenciaException
     */
    public void insertar(Pokeball pokeball) throws PersistenciaException {
        String sql = "INSERT INTO " + TABLA + " VALUES (" + pokeball.getIdObjeto() + ","
                + pokeball.getRatio() + "');";
        persistencia.update(sql);
    }

    /***
     * Metodo que elimina pokeball de la base de datos
     * 
     * @param pokeball
     * @throws PersistenciaException
     */
    public void eliminar(Pokeball pokeball) throws PersistenciaException {
        String sql = "DELETE FROM " + TABLA + " WHERE " + CLAVE + " = " + pokeball.getIdObjeto() + ";";
        persistencia.update(sql);
    }

    /**
     * Metodo que modifica pokeball de la base de datos
     * 
     * @param pokeball a modificar
     * @throws PersistenciaException error controlado
     */
    public void modificar(Pokeball pokeball) throws PersistenciaException {
        String sql = "UPDATE " + TABLA + " SET TipoPrincipal = " + pokeball.getRatio() + "WHERE " + CLAVE
                + " = " + pokeball.getIdObjeto() + ";";
        persistencia.update(sql);
    }

    /**
     * Funcion encargada de obtener pokeball
     * 
     * @param IdObjetos del pokeball
     * @return pokeball buscado
     * @throws PersistenciaException con error controlado
     */
    public Pokeball buscar(int IdObjeto) throws PersistenciaException {
        Pokeball pokeball = null;
        String sql = "SELECT * FROM " + TABLA + " WHERE " + CLAVE + " = " + IdObjeto + ";";
        ArrayList<Pokeball> lista = transformarAPokeball(sql);
        if (!lista.isEmpty()) {
            pokeball = lista.get(0);
        }
        return pokeball;
    }

    /**
     * Funcion que realiza una consulta sobre una sentencia sql dada
     * 
     * @param sql de la consulta
     * @return lista resultados (0..n) Usuasios
     * @throws PersistenciaException error controlado
     */
    private ArrayList<Pokeball> transformarAPokeball(String sql) throws PersistenciaException {
        ArrayList<Pokeball> lista = new ArrayList<>();
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Connection connection = null;
        try {
            connection = persistencia.getConnection();
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Pokeball pokeball = new Pokeball();
                pokeball.setIdObjeto(resultSet.getInt(CLAVE));
                pokeball.setRatio(resultSet.getFloat("Ratio"));
                lista.add(pokeball);
            }
        } catch (SQLException exception) {
            throw new PersistenciaException("Se ha producido un error en la busqueda", exception);
        } finally {
            persistencia.closeConecction(connection, statement, resultSet);
        }
        return lista;
    }
}
