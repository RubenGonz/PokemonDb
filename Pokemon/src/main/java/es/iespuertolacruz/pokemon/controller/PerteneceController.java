package es.iespuertolacruz.pokemon.controller;

import es.iespuertolacruz.pokemon.api.Pertenece;
import es.iespuertolacruz.pokemon.excepciones.FicheroException;
import es.iespuertolacruz.pokemon.excepciones.PersistenciaException;
import es.iespuertolacruz.pokemon.excepciones.PokemonException;
import es.iespuertolacruz.pokemon.modelo.PerteneceModelo;

/**
 * Clase controller de Pertenece
 */
public class PerteneceController {

    // Variables de clase

    TipoController tipoController;
    PokemonController pokemonController;
    PerteneceModelo perteneceModelo;

    // Constructores

    /**
     * Constructor de PerteneceController con tipoController y pokemonController por
     * la dependencia y perteneceModelo iniciliazado
     * 
     * @throws PersistenciaException con error controlado
     * @throws FicheroException      con error controlado
     */
    public PerteneceController() throws PersistenciaException, FicheroException {
        tipoController = new TipoController();
        pokemonController = new PokemonController();
        perteneceModelo = new PerteneceModelo();
    }

    // Funciones y metodos

    /**
     * Metodo encargado de realizar la validacion del objeto pertenece
     * 
     * @param pertenece a validar
     * @throws PokemonException con el mensaje descriptivo de lo que sucede
     */
    public void validar(Pertenece pertenece) throws PokemonException {
        String mensaje = "";

        if (pertenece == null) {
            mensaje = "Se esta validando un objeto nulo";
            throw new PokemonException(mensaje);
        }

        if (pertenece.getNumeroPokedex() <= 0) {
            mensaje = "El identificador del pertenece es 0 o menor, ";
        }

        if (pertenece.getTipo() == null || pertenece.getTipo().isEmpty()) {
            mensaje = "el Tipo es nulo o vacio.";
        }

        if (!mensaje.isEmpty()) {
            throw new PokemonException(mensaje);
        }
    }

    /**
     * Metodo encargado de insertar
     * 
     * @param pertenece a insertar
     * @throws PokemonException      con mensaje controlado
     * @throws PersistenciaException con mensaje controlado
     */
    public void insertar(Pertenece pertenece) throws PokemonException, PersistenciaException {
        validar(pertenece);
        if (existe(pertenece)) {
            throw new PokemonException("El pertenece indicado ya existe");
        }
        perteneceModelo.insertar(pertenece);
    }

    /**
     * Metodo encargado de eliminar
     * 
     * @param pertenece a eliminar
     * @throws PokemonException      con mensaje controlado
     * @throws PersistenciaException con mensaje controlado
     */
    public void eliminar(Pertenece pertenece) throws PokemonException, PersistenciaException {
        validar(pertenece);
        if (!existe(pertenece)) {
            throw new PokemonException("El pertenece indicado no existe");
        }
        perteneceModelo.eliminar(pertenece);
    }

    /**
     * Metodo encargado de realizar la eliminacion
     * 
     * @param numeroPokedex del pertenece a eliminar
     * @param tipo          del pertenece a eliminar
     * @throws PokemonException      con mensaje controlado
     * @throws PersistenciaException con mensaje controlado
     */
    public void eliminar(int numeroPokedex, String tipo) throws PokemonException, PersistenciaException {
        Pertenece pertenece;
        pertenece = buscar(numeroPokedex, tipo);
        eliminar(pertenece);
    }

    /**
     * Metodo encargado de buscar por las claves
     * 
     * @param numeroPokedex para localizar el pertenece
     * @param tipo          para localizar el pertenece
     * @return pertenece a traves del NumeroPokedex
     * @throws PersistenciaException con mensaje controlado
     */
    public Pertenece buscar(int numeroPokedex, String tipo) throws PersistenciaException {
        Pertenece pertenece = null;
        pertenece = perteneceModelo.buscar(numeroPokedex, tipo);
        return pertenece;
    }

    /**
     * Metodo encargado de realizar la modificacion de un pertenece
     * 
     * @param pertenece a modficar
     * @throws PokemonException      con mensaje controlado
     * @throws PersistenciaException con mensaje controlado
     */
    public void modificar(Pertenece pertenece) throws PokemonException, PersistenciaException {
        validar(pertenece);
        if (!existe(pertenece)) {
            throw new PokemonException("El pertenece indicado no existe");
        }
        perteneceModelo.modificar(pertenece);
    }

    /**
     * Funcion encargada de verificar si existe el pertenece
     * 
     * @param pertenece a encontrar
     * @return true si existe o false si no existe
     * @throws PersistenciaException con mensaje controlado
     */
    private boolean existe(Pertenece pertenece) throws PersistenciaException {
        boolean encontrada = false;
        Pertenece perteneceEncontrada;

        perteneceEncontrada = buscar(pertenece.getNumeroPokedex(), pertenece.getTipo());
        if (perteneceEncontrada != null) {
            encontrada = true;
        }
        return encontrada;
    }

}
