package es.iespuertolacruz.pokemon.controller;

import es.iespuertolacruz.pokemon.api.EntrenadorCasual;
import es.iespuertolacruz.pokemon.excepciones.FicheroException;
import es.iespuertolacruz.pokemon.excepciones.PersistenciaException;
import es.iespuertolacruz.pokemon.excepciones.PokemonException;
import es.iespuertolacruz.pokemon.modelo.EntrenadorCasualModelo;

/**
 * Clase principal del modelo de EntrenadorCasual
 */
public class EntrenadorCasualController {

    // Variables de clase

    EntrenadorController entrenadorController;
    EntrenadorCasualModelo entrenadorCasualModelo;

    // Constructores

    public EntrenadorCasualController() throws PersistenciaException, FicheroException {
        entrenadorController = new EntrenadorController();
        entrenadorCasualModelo = new EntrenadorCasualModelo();
    }

    // Funciones y metodos

    /**
     * Metodo encargado de realizar la validacion del objeto entrenadorCasual
     * 
     * @param entrenadorCasual a validar
     * @throws PokemonException con el mensaje descriptivo de lo que sucede
     */
    public void validar(EntrenadorCasual entrenadorCasual) throws PokemonException {
        String mensaje = "";

        if (entrenadorCasual == null) {
            mensaje = "Se esta validando un objeto nulo";
            throw new PokemonException(mensaje);
        }

        if (entrenadorCasual.getIdEntrenador() <= 0) {
            mensaje = "El identificador del entrenadorCasual es 0 o menor, ";
        }

        if (entrenadorCasual.getCantidadMedallas() <= 0) {
            mensaje = "LAs Cantidad Medallas del entrenadorCasual es 0 o menor, ";
        }

        if (!mensaje.isEmpty()) {
            throw new PokemonException(mensaje);
        }
    }

    /**
     * Metodo encargado de insertar
     * 
     * @param entrenadorCasual a insertar
     * @throws PokemonException      con mensaje controlado
     * @throws PersistenciaException con mensaje controlado
     */
    public void insertar(EntrenadorCasual entrenadorCasual) throws PokemonException, PersistenciaException {
        validar(entrenadorCasual);
        if (existe(entrenadorCasual)) {
            throw new PokemonException("El entrenadorCasual indicado ya existe");
        }
        entrenadorCasualModelo.insertar(entrenadorCasual);
    }

    /**
     * Metodo encargado de eliminar
     * 
     * @param entrenador a eliminar
     * @throws PokemonException      con mensaje controlado
     * @throws PersistenciaException con mensaje controlado
     */
    public void eliminar(EntrenadorCasual entrenador) throws PokemonException, PersistenciaException {
        validar(entrenador);
        if (!existe(entrenador)) {
            throw new PokemonException("El entrenadorCasual indicado no existe");
        }
        entrenadorCasualModelo.eliminar(entrenador);
    }

    /**
     * Metodo encargado de realizar la eliminacion
     * 
     * @param nombre del entrenadorCasual a eliminar
     * @throws PokemonException      con mensaje controlado
     * @throws PersistenciaException con mensaje controlado
     */
    public void eliminar(int IdEntrenador) throws PokemonException, PersistenciaException {
        EntrenadorCasual entrenadorCasual;
        entrenadorCasual = buscar(IdEntrenador);
        eliminar(entrenadorCasual);
    }

    /**
     * Metodo encargado de buscar por el id
     * 
     * @param id para localizar el entrenadorCasual
     * @return entrenadorCasual a traves del id
     * @throws PersistenciaException con mensaje controlado
     */
    public EntrenadorCasual buscar(int IdEntrenador) throws PersistenciaException {
        EntrenadorCasual entrenadorCasual = null;
        entrenadorCasual = entrenadorCasualModelo.buscar(IdEntrenador);
        return entrenadorCasual;
    }

    /**
     * Metodo encargado de realizar la modificacion de un entrenadorCasual
     * 
     * @param entrenadorCasual a modficar
     * @throws PokemonException      con mensaje controlado
     * @throws PersistenciaException con mensaje controlado
     */
    public void modificar(EntrenadorCasual entrenadorCasual) throws PokemonException, PersistenciaException {
        validar(entrenadorCasual);
        if (!existe(entrenadorCasual)) {
            throw new PokemonException("El entrenador indicado no existe");
        }
        entrenadorCasualModelo.modificar(entrenadorCasual);
    }

    /**
     * Funcion encargada de verificar si existe el entrenador
     * 
     * @param entrenador a encontrar
     * @return true si existe o false si no existe
     * @throws PersistenciaException con mensaje controlado
     */
    private boolean existe(EntrenadorCasual entrenadorCasual) throws PersistenciaException {
        boolean encontrada = false;
        EntrenadorCasual entrenadorEncontrada;

        entrenadorEncontrada = buscar(entrenadorCasual.getIdEntrenador());
        if (entrenadorEncontrada != null) {
            encontrada = true;
        }
        return encontrada;
    }
}
