package es.iespuertolacruz.pokemon.modelo;

import es.iespuertolacruz.pokemon.api.Pokemon;
import es.iespuertolacruz.pokemon.excepciones.PersistenciaException;

public class PokemonModelo {

    // Variables de clase

    DdBbSqLite persistencia;
    private static final String TABLA = "POKEMON";
    private static final String CLAVE = "numero_pokedex";

    // Constructores

    public PokemonModelo() throws PersistenciaException {
        persistencia = new DdBbSqLite(null, null);
    }

    public void insertar(Pokemon pokemon) throws PersistenciaException {
        String sql = "INSERT INTO " + TABLA + " VALUES ('" + 
            pokemon.getNumeroPokedex() + ",'" + 
            pokemon.getNombre() + "'," + 
            pokemon.getCaracteristicas() + "," + 
            pokemon.getEstadisticasBase() + ");";
        persistencia.update(sql);
    }

    public void eliminar(Pokemon pokemon) throws PersistenciaException {
        String sql = "DELETE FROM " + TABLA + " WHERE " + CLAVE + " = " + 
            pokemon.getNumeroPokedex() + ";";
        persistencia.update(sql);
    }

    public void modificar(Pokemon pokemon) throws PersistenciaException {
        String sql = "UPDATE " + TABLA +  
            " SET nombre = '" + pokemon.getNombre() + "'," +
            " id_caracteristica = '" + pokemon.getCaracteristicas() + "," +
            " id_estadisticas_base = '" + pokemon.getEstadisticasBase() + "," +
            " WHERE " + CLAVE + " = " + 
            pokemon.getNumeroPokedex() + ";";
        persistencia.update(sql);
    }

}
