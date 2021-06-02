package es.iespuertolacruz.pokemon.controller;

import es.iespuertolacruz.pokemon.api.Entrenador;
import es.iespuertolacruz.pokemon.excepciones.FicheroException;
import es.iespuertolacruz.pokemon.excepciones.PersistenciaException;
import es.iespuertolacruz.pokemon.excepciones.PokemonException;
import es.iespuertolacruz.pokemon.modelo.EntrenadorModelo;

/**
 * Clase controller de los entrenadores
 */
public class EntrenadorController {

    // Variables de clase

    EntrenadorModelo entrenadorModelo;

    // Constructores

    /**
     * Constructor con entrenadorModelo inicializado
     * 
     * @throws PersistenciaException con error controlado
     * @throws FicheroException      con error controlado
     */
    public EntrenadorController() throws PersistenciaException, FicheroException {
        entrenadorModelo = new EntrenadorModelo();
    }

    // Funciones y metodos

    /**
     * Metodo encargado de realizar la validacion del objeto entrenador
     * 
     * @param entrenador a validar
     * @throws PokemonException con el mensaje descriptivo de lo que sucede
     */
    public void validar(Entrenador entrenador) throws PokemonException {
        String mensaje = "";

        if (entrenador == null) {
            mensaje = "Se esta validando un objeto nulo";
            throw new PokemonException(mensaje);
        }

        if (entrenador.getId() <= 0) {
            mensaje = "El identificador del entrenador es 0 o menor, ";
        }

        if (entrenador.getNombre() == null || entrenador.getNombre().isEmpty()) {
            mensaje = "el nombre es nulo o vacio.";
        }

        if (!mensaje.isEmpty()) {
            throw new PokemonException(mensaje);
        }
    }

    /**
     * Metodo encargado de insertar
     * 
     * @param entrenador a insertar
     * @throws PokemonException      con mensaje controlado
     * @throws PersistenciaException con mensaje controlado
     */
    public void insertar(Entrenador entrenador) throws PokemonException, PersistenciaException {
        validar(entrenador);
        if (existe(entrenador)) {
            throw new PokemonException("El entrenador indicado ya existe");
        }
        entrenadorModelo.insertar(entrenador);
    }

    /**
     * Metodo encargado de eliminar
     * 
     * @param entrenador a eliminar
     * @throws PokemonException      con mensaje controlado
     * @throws PersistenciaException con mensaje controlado
     */
    public void eliminar(Entrenador entrenador) throws PokemonException, PersistenciaException {
        validar(entrenador);
        if (!existe(entrenador)) {
            throw new PokemonException("El entrenador indicado no existe");
        }
        entrenadorModelo.eliminar(entrenador);
    }

    /**
     * Metodo encargado de realizar la eliminacion
     * 
     * @param nombre del entrenador a eliminar
     * @throws PokemonException      con mensaje controlado
     * @throws PersistenciaException con mensaje controlado
     */
    public void eliminar(int id) throws PokemonException, PersistenciaException {
        Entrenador entrenador;
        entrenador = buscar(id);
        eliminar(entrenador);
    }

    /**
     * Metodo encargado de buscar por el id
     * 
     * @param id para localizar el entrenador
     * @return entrenador a traves del id
     * @throws PersistenciaException con mensaje controlado
     */
    public Entrenador buscar(int id) throws PersistenciaException {
        Entrenador entrenador = null;
        entrenador = entrenadorModelo.buscar(id);
        return entrenador;
    }

    /**
     * Metodo encargado de realizar la modificacion de un entrenador
     * 
     * @param entrenador a modficar
     * @throws PokemonException      con mensaje controlado
     * @throws PersistenciaException con mensaje controlado
     */
    public void modificar(Entrenador entrenador) throws PokemonException, PersistenciaException {
        validar(entrenador);
        if (!existe(entrenador)) {
            throw new PokemonException("El entrenador indicado no existe");
        }
        entrenadorModelo.modificar(entrenador);
    }

    /**
     * Funcion encargada de verificar si existe el entrenador
     * 
     * @param entrenador a encontrar
     * @return true si existe o false si no existe
     * @throws PersistenciaException con mensaje controlado
     */
    private boolean existe(Entrenador entrenador) throws PersistenciaException {
        boolean encontrada = false;
        Entrenador entrenadorEncontrada;

        entrenadorEncontrada = buscar(entrenador.getId());
        if (entrenadorEncontrada != null) {
            encontrada = true;
        }
        return encontrada;
    }

}