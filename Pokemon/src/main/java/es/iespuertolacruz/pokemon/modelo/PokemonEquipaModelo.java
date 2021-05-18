package es.iespuertolacruz.pokemon.modelo;

import es.iespuertolacruz.pokemon.api.PokemonEquipa;
import es.iespuertolacruz.pokemon.excepciones.PersistenciaException;

public class PokemonEquipaModelo {
    // Persistencia en MySql

    MySqlBdDd persistencia;

    // Constructores

    public PokemonEquipaModelo() {
        try {
            persistencia = new MySqlBdDd("com.mysql.jdbc.Driver", "jdbc:mysql://localhost/test", "minty", "greatsqldb");
        } catch (PersistenciaException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void insertar(PokemonEquipa pokemonEquipa) throws PersistenciaException {
        persistencia.insertarPokemonEquipa(pokemonEquipa);
    }

    public void eliminar(PokemonEquipa pokemonEquipa) throws PersistenciaException {
        persistencia.eliminarPokemonEquipa(pokemonEquipa);
    }

    public PokemonEquipa buscar(int numeroPokedex) throws PersistenciaException {
        return persistencia.obtenerPokemonEquipa(numeroPokedex);
    }

    public void modificar(PokemonEquipa pokemonEquipa) throws PersistenciaException {
        persistencia.modificarPokemonEquipa(pokemonEquipa);
    }

}
