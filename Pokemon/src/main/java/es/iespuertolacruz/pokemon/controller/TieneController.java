package es.iespuertolacruz.pokemon.controller;

import es.iespuertolacruz.pokemon.api.Tiene;
import es.iespuertolacruz.pokemon.excepciones.FicheroException;
import es.iespuertolacruz.pokemon.excepciones.PersistenciaException;
import es.iespuertolacruz.pokemon.excepciones.PokemonException;
import es.iespuertolacruz.pokemon.modelo.TieneModelo;

public class TieneController {

    // Variables de clase

    EntrenadorController entrenadorController;
    PokemonController pokemonController;
    TieneModelo tieneModelo;

    // Constructores

    /**
     * Constructor de TieneController con entrenadorController y pokemonController
     * por la dependencia y tieneModelo iniciliazado
     * 
     * @throws PersistenciaException con error controlado
     * @throws FicheroException      con error controlado
     */
    public TieneController() throws PersistenciaException, FicheroException {
        entrenadorController = new EntrenadorController();
        pokemonController = new PokemonController();
        tieneModelo = new TieneModelo();
    }

    // Funciones y metodos

    /**
     * Metodo encargado de realizar la validacion del objeto tiene
     * 
     * @param tiene a validar
     * @throws PokemonException con el mensaje descriptivo de lo que sucede
     */
    public void validar(Tiene tiene) throws PokemonException {
        String mensaje = "";

        if (tiene == null) {
            mensaje = "Se esta validando un objeto nulo";
            throw new PokemonException(mensaje);
        }

        if (tiene.getNumeroPokedex() <= 0) {
            mensaje = "El NumeroPokedex del tiene es 0 o menor, ";
        }

        if (tiene.getIdEntrenador() <= 0) {
            mensaje = "El IdEntrenador del tiene es 0 o menor, ";
        }

        if (!mensaje.isEmpty()) {
            throw new PokemonException(mensaje);
        }
    }

    /**
     * Metodo encargado de insertar
     * 
     * @param tiene a insertar
     * @throws PokemonException      con mensaje controlado
     * @throws PersistenciaException con mensaje controlado
     */
    public void insertar(Tiene tiene) throws PokemonException, PersistenciaException {
        validar(tiene);
        if (existe(tiene)) {
            throw new PokemonException("El tiene indicado ya existe");
        }
        tieneModelo.insertar(tiene);
    }

    /**
     * Metodo encargado de eliminar
     * 
     * @param tiene a eliminar
     * @throws PokemonException      con mensaje controlado
     * @throws PersistenciaException con mensaje controlado
     */
    public void eliminar(Tiene tiene) throws PokemonException, PersistenciaException {
        validar(tiene);
        if (!existe(tiene)) {
            throw new PokemonException("El tiene indicado no existe");
        }
        tieneModelo.eliminar(tiene);
    }

    /**
     * Metodo encargado de realizar la eliminacion
     * 
     * @param idEntrenador del tiene a eliminar
     * @param numeroPokedex del tiene a eliminar
     * @throws PokemonException      con mensaje controlado
     * @throws PersistenciaException con mensaje controlado
     */
    public void eliminar(int idEntrenador, int numeroPokedex) throws PokemonException, PersistenciaException {
        Tiene tiene;
        tiene = buscar(idEntrenador, numeroPokedex);
        eliminar(tiene);
    }

    /**
     * Metodo encargado de buscar por las claves
     * 
     * @param idEntrenador del tiene a eliminar
     * @param numeroPokedex del tiene a eliminar
     * @return tiene a traves de las claves
     * @throws PersistenciaException con mensaje controlado
     */
    public Tiene buscar(int idEntrenador, int numeroPokedex) throws PersistenciaException {
        Tiene tiene = null;
        tiene = tieneModelo.buscar(idEntrenador, numeroPokedex);
        return tiene;
    }

    /**
     * Metodo encargado de realizar la modificacion de un tiene
     * 
     * @param tiene a modficar
     * @throws PokemonException      con mensaje controlado
     * @throws PersistenciaException con mensaje controlado
     */
    public void modificar(Tiene tiene) throws PokemonException, PersistenciaException {
        validar(tiene);
        if (!existe(tiene)) {
            throw new PokemonException("El tiene indicado no existe");
        }
        tieneModelo.modificar(tiene);
    }

    /**
     * Funcion encargada de verificar si existe el tiene
     * 
     * @param tiene a encontrar
     * @return true si existe o false si no existe
     * @throws PersistenciaException con mensaje controlado
     */
    private boolean existe(Tiene tiene) throws PersistenciaException {
        boolean encontrada = false;
        Tiene tieneEncontrada;

        tieneEncontrada = buscar(tiene.getIdEntrenador(), tiene.getNumeroPokedex());
        if (tieneEncontrada != null) {
            encontrada = true;
        }
        return encontrada;
    }

}
