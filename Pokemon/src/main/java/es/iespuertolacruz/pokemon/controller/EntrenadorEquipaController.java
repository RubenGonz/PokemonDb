package es.iespuertolacruz.pokemon.controller;

import es.iespuertolacruz.pokemon.api.EntrenadorEquipa;
import es.iespuertolacruz.pokemon.excepciones.PersistenciaException;
import es.iespuertolacruz.pokemon.excepciones.PokemonException;
import es.iespuertolacruz.pokemon.modelo.EntrenadorEquipaModelo;

public class EntrenadorEquipaController {

    // Variables de clase

    EntrenadorController entrenadorController;
    ObjetoController objetoController;
    EntrenadorEquipaModelo entrenadorEquipaModelo;

    // Constructores

    public EntrenadorEquipaController() throws PersistenciaException {
        entrenadorController = new EntrenadorController();
        objetoController = new ObjetoController();
        entrenadorEquipaModelo = new EntrenadorEquipaModelo();
    }

    // Funciones y metodos

    /**
     * Metodo encargado de realizar la validacion del objeto entrenador
     * 
     * @param entrenador a validar
     * @throws PokemonException con el mensaje descriptivo de lo que sucede
     */
    public void validar(EntrenadorEquipa entrenadorEquipa) throws PokemonException {
        String mensaje = "";

        if (entrenadorEquipa == null) {
            mensaje = "Se esta validando un objeto nulo";
            throw new PokemonException(mensaje);
        }

        if (entrenadorEquipa.getIdEntrenador() <= 0) {
            mensaje = "El identificador del entrenadorEquipa es 0 o menor, ";
        }
        if (entrenadorEquipa.getIdObjeto() <= 0) {
            mensaje = "El identificador del entrenadorEquipa es 0 o menor, ";
        }

        if (entrenadorEquipa.getCantidad() <= 0) {
            mensaje = "La Cantidad del entrenadorEquipa es 0 o menor, ";
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
    public void insertar(EntrenadorEquipa entrenadorEquipa) throws PokemonException, PersistenciaException {
        validar(entrenadorEquipa);
        if (existe(entrenadorEquipa)) {
            throw new PokemonException("El entrenador indicado ya existe");
        }
        entrenadorEquipaModelo.insertar(entrenadorEquipa);
    }

    /**
     * Metodo encargado de eliminar
     * 
     * @param entrenador a eliminar
     * @throws PokemonException      con mensaje controlado
     * @throws PersistenciaException con mensaje controlado
     */
    public void eliminar(EntrenadorEquipa entrenadorEquipa) throws PokemonException, PersistenciaException {
        validar(entrenadorEquipa);
        if (!existe(entrenadorEquipa)) {
            throw new PokemonException("El entrenador indicado no existe");
        }
        entrenadorEquipaModelo.eliminar(entrenadorEquipa);
    }

    /**
     * Metodo encargado de realizar la eliminacion
     * 
     * @param nombre del entrenador a eliminar
     * @throws PokemonException      con mensaje controlado
     * @throws PersistenciaException con mensaje controlado
     */
    public void eliminar(int IdEntrenador, int IdObjeto) throws PokemonException, PersistenciaException {
        EntrenadorEquipa entrenadorEquipa;
        entrenadorEquipa = buscar(IdEntrenador, IdObjeto);
        eliminar(entrenadorEquipa);
    }

    /**
     * Metodo encargado de buscar por el id
     * 
     * @param id para localizar el entrenador
     * @return entrenador a traves del id
     * @throws PersistenciaException con mensaje controlado
     */
    public EntrenadorEquipa buscar(int IdEntrenador, int IdObjeto) throws PersistenciaException {
        EntrenadorEquipa entrenadorEquipa = null;
        entrenadorEquipa = entrenadorEquipaModelo.buscar(IdEntrenador, IdObjeto);
        return entrenadorEquipa;
    }

    /**
     * Metodo encargado de realizar la modificacion de un entrenador
     * 
     * @param entrenador a modficar
     * @throws PokemonException      con mensaje controlado
     * @throws PersistenciaException con mensaje controlado
     */
    public void modificar(EntrenadorEquipa entrenadorEquipa) throws PokemonException, PersistenciaException {
        validar(entrenadorEquipa);
        if (!existe(entrenadorEquipa)) {
            throw new PokemonException("El entrenador indicado no existe");
        }
        entrenadorEquipaModelo.modificar(entrenadorEquipa);
    }

    /**
     * Funcion encargada de verificar si existe el entrenador
     * 
     * @param entrenador a encontrar
     * @return true si existe o false si no existe
     * @throws PersistenciaException con mensaje controlado
     */
    private boolean existe(EntrenadorEquipa entrenadorEquipa) throws PersistenciaException {
        boolean encontrada = false;
        EntrenadorEquipa entrenadorEncontrada;

        entrenadorEncontrada = buscar(entrenadorEquipa.getIdEntrenador(), entrenadorEquipa.getIdObjeto());
        if (entrenadorEncontrada != null) {
            encontrada = true;
        }
        return encontrada;
    }

}
