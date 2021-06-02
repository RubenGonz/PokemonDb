package es.iespuertolacruz.pokemon.modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import es.iespuertolacruz.pokemon.api.PokemonEquipa;
import es.iespuertolacruz.pokemon.excepciones.FicheroException;
import es.iespuertolacruz.pokemon.excepciones.PersistenciaException;

public class PokemonEquipaModelo {
    // Variables de clase

    DdBbSqLite persistencia;
    private static final String TABLA = "POKEMON_EQUIPA";
    private static final String CLAVE = "numero_pokedex";

    // Constructores
    
    public PokemonEquipaModelo() throws PersistenciaException, FicheroException {
        persistencia = new DdBbSqLite(TABLA, null, null);
    }

    // Metodos y funciones

    /**
     * Metodo que inserta pokemonEquipa en la base de datos
     * 
     * @param pokemonEquipa
     * @throws PersistenciaException
     */
    public void insertar(PokemonEquipa pokemonEquipa) throws PersistenciaException {
        String sql = "INSERT INTO " + TABLA + " VALUES (" + pokemonEquipa.getNumeroPokedex() + ","
                + pokemonEquipa.getIdObjeto() + "');";
        persistencia.update(sql);
    }

    /***
     * Metodo que elimina pokemonEquipa de la base de datos
     * 
     * @param pokemonEquipa
     * @throws PersistenciaException
     */
    public void eliminar(PokemonEquipa pokemonEquipa) throws PersistenciaException {
        String sql = "DELETE FROM " + TABLA + " WHERE " + CLAVE + " = " + pokemonEquipa.getNumeroPokedex()
                + pokemonEquipa.getIdObjeto() + ";";
        persistencia.update(sql);
    }

    /**
     * Metodo que modifica pokemonEquipa de la base de datos
     * 
     * @param pokemonEquipa a modificar
     * @throws PersistenciaException error controlado
     */
    public void modificar(PokemonEquipa pokemonEquipa) throws PersistenciaException {
        String sql = "UPDATE " + TABLA + " SET NumeroPokedex = " + pokemonEquipa.getNumeroPokedex() + "WHERE " + CLAVE
                + " = " + pokemonEquipa.getIdObjeto() + pokemonEquipa.getNumeroPokedex() + ";";
        persistencia.update(sql);
    }

    /**
     * Funcion encargada de obtener pokemonEquipa
     * 
     * @param IdEntrenador del pokemonEquipa
     * @return pokemonEquipa buscado
     * @throws PersistenciaException con error controlado
     */
    public PokemonEquipa buscar(int NumeroPokedex ,int IdObjeto) throws PersistenciaException {
        PokemonEquipa pokemonEquipa = null;
        String sql = "SELECT * FROM " + TABLA + " WHERE " + CLAVE + " = " + NumeroPokedex + IdObjeto  + ";";
        ArrayList<PokemonEquipa> lista = transformarAPokemonEquipa(sql);
        if (!lista.isEmpty()) {
            pokemonEquipa = lista.get(0);
        }
        return pokemonEquipa;
    }

    /**
     * Funcion que realiza una consulta sobre una sentencia sql dada
     * 
     * @param sql de la consulta
     * @return lista resultados (0..n) Usuasios
     * @throws PersistenciaException error controlado
     */
    private ArrayList<PokemonEquipa> transformarAPokemonEquipa(String sql) throws PersistenciaException {
        ArrayList<PokemonEquipa> lista = new ArrayList<>();
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Connection connection = null;
        try {
            connection = persistencia.getConnection();
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                PokemonEquipa pokemonEquipa = new PokemonEquipa();
                pokemonEquipa.setNumeroPokedex(resultSet.getInt(CLAVE));
                pokemonEquipa.setIdObjeto(resultSet.getInt(CLAVE));
                lista.add(pokemonEquipa);
            }
        } catch (SQLException exception) {
            throw new PersistenciaException("Se ha producido un error en la busqueda", exception);
        } finally {
            persistencia.closeConecction(connection, statement, resultSet);
        }
        return lista;
    }
}
