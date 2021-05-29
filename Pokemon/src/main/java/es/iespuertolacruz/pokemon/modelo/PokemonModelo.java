package es.iespuertolacruz.pokemon.modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import es.iespuertolacruz.pokemon.api.Pokemon;
import es.iespuertolacruz.pokemon.excepciones.PersistenciaException;

/**
 * Clase principal del modelo de pokemon
 */
public class PokemonModelo {

    // Variables de clase

    DdBbSqLite persistencia;
    private static final String TABLA = "POKEMON";
    private static final String CLAVE = "numero_pokedex";
    private static final String SQLCREARTABLA = "CREATE TABLE IF NOT EXISTS POKEMON ( " +
        "numero_pokedex INT CHECK (numero_pokedex > 0), " +
        "nombre VARCHAR (15), " +
        "id_pokemon INT CHECK (id_pokemon > 0), " +
        "id_estadisticas_base INT CHECK (id_estadisticas_base > 0), " +
        "PRIMARY KEY (numero_pokedex), " +
        "FOREIGN KEY (id_pokemon) REFERENCES CARACTERISTICAS (id_pokemon), " +
        "FOREIGN KEY (id_estadisticas_base) REFERENCES ESTADISTICAS_BASE (id_estadisticas_base));";

    // Constructores

    public PokemonModelo() throws PersistenciaException {
        persistencia = new DdBbSqLite(TABLA, null, null, SQLCREARTABLA);
    }

    // Metodos y funciones

    /**
     * Metodo que inserta pokemon en la base de datos
     * 
     * @param pokemon a insertar
     * @throws PersistenciaException error controlado
     */
    public void insertar(Pokemon pokemon) throws PersistenciaException {
        String sql = "INSERT INTO " + TABLA + " VALUES (" + 
        pokemon.getNumeroPokedex() + ",'" + 
        pokemon.getNombre() + "'," + 
        pokemon.getCaracteristicas() + "," + 
        pokemon.getEstadisticasBase() + ");";
        persistencia.update(sql);
    }

    /**
     * Metodo que elimina una Pokemon de la base de datos
     * 
     * @param pokemon a eliminar
     * @throws PersistenciaException error controlado
     */
    public void eliminar(Pokemon pokemon) throws PersistenciaException {
        String sql = "DELETE FROM " + TABLA + " WHERE " + CLAVE + " = " + pokemon.getNumeroPokedex() + ";";
        persistencia.update(sql);
    }

    /**
     * Metodo que modifica una Pokemon de la base de datos
     * 
     * @param pokemon a modificar
     * @throws PersistenciaException error controlado
     */
    public void modificar(Pokemon pokemon) throws PersistenciaException {
        String sql = "UPDATE " + TABLA + " SET nombre = '" + pokemon.getNombre() + "'," 
         + "id_caracteristica = " + pokemon.getCaracteristicas() + ","
         + "id_estadisticas_base = " + pokemon.getEstadisticasBase() + ","
         + "WHERE " + CLAVE + " = " + pokemon.getNumeroPokedex() + ";";
        persistencia.update(sql);
    }

    /**
     * Funcion encargada de obtener una pokemon
     * 
     * @param id_pokemon del pokemon
     * @return Pokemon buscado
     * @throws PersistenciaException con error controlado
     */
    public Pokemon buscar(int numeroPokedex) throws PersistenciaException {
        Pokemon pokemon = null;
        String sql = "SELECT * FROM " + TABLA + " WHERE " + CLAVE + " = " + numeroPokedex + ";";
        ArrayList<Pokemon> lista = transformarAPokemon(sql);
        if (!lista.isEmpty()) {
            pokemon = lista.get(0);
        }
        return pokemon;
    }

    /**
     * Funcion que realiza una consulta sobre una sentencia sql dada
     * 
     * @param sql de la consulta
     * @return lista resultados (0..n) Usuasios
     * @throws PersistenciaException error controlado
     */
    private ArrayList<Pokemon> transformarAPokemon(String sql) throws PersistenciaException {
        ArrayList<Pokemon> lista = new ArrayList<>();
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Connection connection = null;
        try {
            connection = persistencia.getConnection();
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Pokemon pokemon = new Pokemon();
                pokemon.setNumeroPokedex(resultSet.getInt(CLAVE));
                pokemon.setNombre(resultSet.getString("nombre"));
                pokemon.setCaracteristicas(resultSet.getInt("id_caracteristica"));
                pokemon.setEstadisticasBase(resultSet.getInt("id_estadisticas_base"));
                lista.add(pokemon);
            }
        } catch (SQLException exception) {
            throw new PersistenciaException("Se ha producido un error en la busqueda", exception);
        } finally {
            persistencia.closeConecction(connection, statement, resultSet);
        }
        return lista;
    }
}
