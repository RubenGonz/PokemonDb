package es.iespuertolacruz.pokemon.controller;

import es.iespuertolacruz.pokemon.api.Pokemon;
import es.iespuertolacruz.pokemon.api.*;
import es.iespuertolacruz.pokemon.excepciones.PersistenciaException;
import es.iespuertolacruz.pokemon.excepciones.PokemonException;
import es.iespuertolacruz.pokemon.modelo.PokemonModelo;

/**
 * Controlador de la clase Pokemon
 */
public class PokemonController {

    // Variables de clase

    private static final String EL_POKEMON_INDICADO_NO_EXISTE = "El pokemon indicado no existe";
    PokemonModelo pokemonModelo;

    // Constructores

    /**
     * Construcor por defecto con pokemon modelo inicializado
     */
    public PokemonController() {
        pokemonModelo = new PokemonModelo();
    }

    // Metodos y funciones

    public void validar(Pokemon pokemon) throws PokemonException {
        String mensaje = "";

        if (pokemon == null) {
            mensaje = "Se esta validando un objeto nulo";
            throw new PokemonException(mensaje);
        }

        if (pokemon.getNumeroPokedex() < 0) {
            mensaje = "El numero de la pokedex del pokemon es 0 o menor, ";
        }

        if (pokemon.getNombre() == null || pokemon.getNombre().isEmpty()) {
            mensaje += "El nombre del pokemon es nulo o vacio, ";
        }

        if (pokemon.getCaracteristicas() < 0) {
            mensaje += "El identificador de las caracteristicas del pokemon es 0 o menor, ";
        }

        if (pokemon.getEstadisticasBase() < 0) {
            mensaje += "El identificador de las estadisticas base del pokemon es 0 o menor.";
        }

        if (!mensaje.isEmpty()) {
            throw new PokemonException(mensaje);
        }
    }

    /**
     * Metodo encargado de insertar
     * 
     * @param pokemon a insertar
     * @throws PokemonException      con mensaje controlado
     * @throws PersistenciaException
     */
    public void insertar(Pokemon pokemon) throws PokemonException, PersistenciaException {
        validar(pokemon);
        if (existe(pokemon)) {
            throw new PokemonException("El pokemon indicado ya existe");
        }
        pokemonModelo.insertar(pokemon);
    }

    /**
     * Metodo encargado de eliminar
     * 
     * @param pokemon a eliminar
     * @throws PokemonException
     * @throws PersistenciaException
     */
    public void eliminar(Pokemon pokemon) throws PokemonException, PersistenciaException {
        validar(pokemon);
        if (!existe(pokemon)) {
            throw new PokemonException(EL_POKEMON_INDICADO_NO_EXISTE);
        }
        pokemonModelo.eliminar(pokemon);
    }

    /**
     * Metodos encargado de realizar la eliminacion de un pokemon usando el numero
     * de la pokedex
     * 
     * @param identificador del pokemon a eliminar
     * @throws PokemonException      controlada con el error
     * @throws PersistenciaException
     */
    public void eliminar(int numeroPokedex) throws PokemonException, PersistenciaException {
        Pokemon pokemon;
        pokemon = buscar(numeroPokedex);
        eliminar(pokemon);
    }

    /**
     * Metodo encargado de buscar por el numero de la pokedex
     * 
     * @param numeroPokedex para localizar el pokemon
     * @return pokemon a traves del numero de la pokedex
     * @throws PersistenciaException
     */
    public Pokemon buscar(int numeroPokedex) throws PersistenciaException {
        Pokemon pokemon = null;
        pokemon = pokemonModelo.buscar(numeroPokedex);
        return pokemon;
    }

    /**
     * Metodo encargado de realizar la modificacion de un pokemon
     * 
     * @param pokemon a modficar
     * @throws PokemonException      controlada en caso de error
     * @throws PersistenciaException
     */
    public void modificar(Pokemon pokemon) throws PokemonException, PersistenciaException {

        validar(pokemon);
        if (!existe(pokemon)) {
            throw new PokemonException(EL_POKEMON_INDICADO_NO_EXISTE);
        }
        pokemonModelo.modificar(pokemon);
    }

    /**
     * Funcion encargada de verificar si un pokemon existe
     * 
     * @param pokemon a encontrar
     * @return true si existe o false si no existe
     * @throws PersistenciaException error controlado
     */
    private boolean existe(Pokemon pokemon) throws PersistenciaException {
        boolean encontrado = false;
        Pokemon pokemonEncontrado;

        pokemonEncontrado = buscar(pokemon.getNumeroPokedex());
        if (pokemonEncontrado != null) {
            encontrado = true;
        }
        return encontrado;
    }

    
}
