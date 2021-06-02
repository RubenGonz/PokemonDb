package es.iespuertolacruz.pokemon.modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import es.iespuertolacruz.pokemon.api.Evoluciona;
import es.iespuertolacruz.pokemon.excepciones.FicheroException;
import es.iespuertolacruz.pokemon.excepciones.PersistenciaException;

public class EvolucionaModelo {
    // Variables de clase

    DdBbSqLite persistencia;
    private static final String TABLA = "EVOLUCIONA";
    private static final String CLAVE = "numero_pokedex_origen";
    
    // Constructores
    public EvolucionaModelo() throws PersistenciaException, FicheroException {
        persistencia = new DdBbSqLite(TABLA, null, null);
    }

    // Metodos y funciones

    /**
     * Metodo que inserta evoluciona en la base de datos
     * 
     * @param evoluciona
     * @throws PersistenciaException
     */
    public void insertar(Evoluciona evoluciona) throws PersistenciaException {
        String sql = "INSERT INTO " + TABLA + " VALUES (" + evoluciona.getNumeroPokedexOrigen() + ","
                + evoluciona.getNumeroPokedexDestino() + "," +evoluciona.getModoEvoluciona() + "');";
        persistencia.update(sql);
    }

    /***
     * Metodo que elimina evoluciona de la base de datos
     * 
     * @param evoluciona
     * @throws PersistenciaException
     */
    public void eliminar(Evoluciona evoluciona) throws PersistenciaException {
        String sql = "DELETE FROM " + TABLA + " WHERE " + CLAVE + " = " + evoluciona.getNumeroPokedexOrigen() + evoluciona
                .getNumeroPokedexDestino() + ";";
        persistencia.update(sql);
    }

    /**
     * Metodo que modifica evoluciona de la base de datos
     * 
     * @param evoluciona a modificar
     * @throws PersistenciaException error controlado
     */
    public void modificar(Evoluciona evoluciona) throws PersistenciaException {
        String sql = "UPDATE " + TABLA + " SET ModoEvoluciona = " + evoluciona.getModoEvoluciona() + "WHERE " + CLAVE
                + " = " + evoluciona.getNumeroPokedexOrigen()+ evoluciona.getNumeroPokedexDestino() + ";";
        persistencia.update(sql);
    }

    /**
     * Funcion encargada de obtener evoluciona
     * 
     * @param PokedexOrigen y NumeroPokedexDestino del evoluciona
     * @return evoluciona buscado
     * @throws PersistenciaException con error controlado
     */
    public Evoluciona buscar(int PokedexOrigen , int NumeroPokedexDestino) throws PersistenciaException {
        Evoluciona evoluciona = null;
        String sql = "SELECT * FROM " + TABLA + " WHERE " + CLAVE + " = " + PokedexOrigen + NumeroPokedexDestino +  ";";
        ArrayList<Evoluciona> lista = transformarAEvoluciona(sql);
        if (!lista.isEmpty()) {
            evoluciona = lista.get(0);
        }
        return evoluciona;
    }

    /**
     * Funcion que realiza una consulta sobre una sentencia sql dada
     * 
     * @param sql de la consulta
     * @return lista resultados (0..n) Usuasios
     * @throws PersistenciaException error controlado
     */
    private ArrayList<Evoluciona> transformarAEvoluciona(String sql) throws PersistenciaException {
        ArrayList<Evoluciona> lista = new ArrayList<>();
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Connection connection = null;
        try {
            connection = persistencia.getConnection();
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Evoluciona evoluciona = new Evoluciona();
                evoluciona.setNumeroPokedexOrigen(resultSet.getInt(CLAVE));
                evoluciona.setNumeroPokedexDestino(resultSet.getInt(CLAVE));
                evoluciona.setModoEvoluciona(resultSet.getString("ModoEvoluciona"));
                lista.add(evoluciona);
            }
        } catch (SQLException exception) {
            throw new PersistenciaException("Se ha producido un error en la busqueda", exception);
        } finally {
            persistencia.closeConecction(connection, statement, resultSet);
        }
        return lista;
    }
}
