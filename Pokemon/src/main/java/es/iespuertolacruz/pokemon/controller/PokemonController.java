package es.iespuertolacruz.pokemon.controller;

import es.iespuertolacruz.pokemon.api.Pokemon;
import es.iespuertolacruz.pokemon.excepciones.PersistenciaException;
import es.iespuertolacruz.pokemon.excepciones.PokemonException;
import es.iespuertolacruz.pokemon.modelo.PokemonModelo;

public class PokemonController {

    // Variables de clase

    EstadisticasBaseController estadisticasBaseController;
    CaracteristicasController caracteristicasController;
    PokemonModelo pokemonModelo;

    // Constructores

    /**
     * Constructor de pokemon con estadisticasBaseController y
     * caracteristicasController para crear las tablas con si no existiesen antes
     * 
     * @throws PersistenciaException con error controlado
     */
    public PokemonController() throws PersistenciaException {
        estadisticasBaseController = new EstadisticasBaseController();
        caracteristicasController = new CaracteristicasController();
        pokemonModelo = new PokemonModelo();
    }

    // Funciones y metodos

    /**
     * Metodo encargado de realizar la validacion del objeto pokemon
     * 
     * @param pokemon a validar
     * @throws PokemonException con el mensaje descriptivo de lo que sucede
     */
    public void validar(Pokemon pokemon) throws PokemonException {
        String mensaje = "";

        if (pokemon == null) {
            mensaje = "Se esta validando un objeto nulo";
            throw new PokemonException(mensaje);
        }

        if (pokemon.getNumeroPokedex() <= 0) {
            mensaje = "El numero de la pokedex del pokemon es cero o menor, ";
        }

        if (pokemon.getNombre() == null || pokemon.getNombre().isEmpty()) {
            mensaje += "el nombre del pokemon es nulo o vacio, ";
        }

        if (pokemon.getCaracteristicas() <= 0) {
            mensaje = "El identificador de las caracteristicas del pokemon es cero o menor, ";
        }

        if (pokemon.getEstadisticasBase() <= 0) {
            mensaje = "El identificador de las estadisticas base del pokemon es cero o menor.";
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
     * @throws PersistenciaException con mensaje controlado
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
     * @throws PokemonException      con mensaje controlado
     * @throws PersistenciaException con mensaje controlado
     */
    public void eliminar(Pokemon pokemon) throws PokemonException, PersistenciaException {
        validar(pokemon);
        if (!existe(pokemon)) {
            throw new PokemonException("El pokemon indicado no existe");
        }
        pokemonModelo.eliminar(pokemon);
    }

    /**
     * Metodo encargado de realizar la eliminacion
     * 
     * @param numeroPokedex del pokemon a eliminar
     * @throws PokemonException      con mensaje controlado
     * @throws PersistenciaException con mensaje controlado
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
     * @throws PersistenciaException con mensaje controlado
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
     * @throws PokemonException      con mensaje controlado
     * @throws PersistenciaException con mensaje controlado
     */
    public void modificar(Pokemon pokemon) throws PokemonException, PersistenciaException {
        validar(pokemon);
        if (!existe(pokemon)) {
            throw new PokemonException("El pokemon indicado no existe");
        }
        pokemonModelo.modificar(pokemon);
    }

    /**
     * Funcion encargada de verificar si existe el pokemon
     * 
     * @param pokemon a encontrar
     * @return true si existe o false si no existe
     * @throws PersistenciaException con mensaje controlado
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