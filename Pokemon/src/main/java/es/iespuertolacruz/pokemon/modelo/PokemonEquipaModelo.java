package es.iespuertolacruz.pokemon.modelo;

import es.iespuertolacruz.pokemon.api.PokemonEquipa;
import es.iespuertolacruz.pokemon.excepciones.PersistenciaException;


public class PokemonEquipaModelo {
    // Variables de clase

    DdBbSqLite persistencia;
    private static final String TABLA = "TIPO";
    private static final String CLAVE = "nombre";

    // Constructores

    public PokemonEquipaModelo() throws PersistenciaException {
        persistencia = new DdBbSqLite(TABLA, CLAVE, null, null);
    }

    public void insertar(PokemonEquipa pokemonEquipa) throws PersistenciaException {
        String sql = "INSERT INTO " + TABLA + " VALUES ('" + pokemonEquipa.getNumeroPokedex() + "','"
                + pokemonEquipa.getIdObjeto() + "');";
        persistencia.update(sql);
    }

    public void eliminar(PokemonEquipa pokemonEquipa) throws PersistenciaException {
        String sql = "DELETE FROM " + TABLA + " WHERE " + CLAVE + " = '" + pokemonEquipa.getNumeroPokedex() + "';";
        persistencia.update(sql);
    }
/**
    public PokemonEquipa buscar(int numeroPokedex) throws PersistenciaException {
        return (PokemonEquipa) persistencia.buscarElemento(numeroPokedex);
    }
*/
    public void modificar(PokemonEquipa pokemonEquipa) throws PersistenciaException {
        String sql = "UPDATE " + TABLA + " SET Tipo_Principal = '" + pokemonEquipa.getIdObjeto() + "' WHERE " + CLAVE
                + " = '" + pokemonEquipa.getNumeroPokedex() + "';";
        persistencia.update(sql);
    }

}
