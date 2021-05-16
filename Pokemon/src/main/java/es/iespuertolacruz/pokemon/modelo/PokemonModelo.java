package es.iespuertolacruz.pokemon.modelo;

import es.iespuertolacruz.pokemon.api.*;
import es.iespuertolacruz.pokemon.excepciones.PersistenciaException;

/**
 * Clase modelo que tranaja con la clase pokemon
 */
public class PokemonModelo {

    // Persistencia en MySql

    MySqlBdDd persistencia;

    // Constructores

    public PokemonModelo() {
        try {
            persistencia = new MySqlBdDd("com.mysql.jdbc.Driver", "jdbc:mysql://localhost/test", "minty", "greatsqldb");
        } catch (PersistenciaException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void insertar(Pokemon pokemon) throws PersistenciaException {
        persistencia.insertar(pokemon);
    }

    public void eliminar(Pokemon pokemon) throws PersistenciaException {
        persistencia.eliminar(pokemon);
    }

    public Pokemon buscar(int numeroPokedex) throws PersistenciaException {
        return persistencia.obtenerPokemon(numeroPokedex);
    }

    public void modificar(Pokemon pokemon) throws PersistenciaException {
        persistencia.modificar(pokemon);
    }
    
   
}
