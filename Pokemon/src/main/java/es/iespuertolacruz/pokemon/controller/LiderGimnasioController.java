package es.iespuertolacruz.pokemon.controller;

import es.iespuertolacruz.pokemon.api.LiderGimnasio;
import es.iespuertolacruz.pokemon.excepciones.FicheroException;
import es.iespuertolacruz.pokemon.excepciones.PersistenciaException;
import es.iespuertolacruz.pokemon.excepciones.PokemonException;
import es.iespuertolacruz.pokemon.modelo.LiderGimnasioModelo;

public class LiderGimnasioController {

    // Variables de clase

    EntrenadorController entrenadorController;
    LiderGimnasioModelo liderGimnasioModelo;

    // Constructores

    /**
     * Constructor de LiderGimnasioController con entrenadorController inicializado por la
     * dependencia y liderGimnasioModelo iniciliazado
     * 
     * @throws PersistenciaException con error controlado
     * @throws FicheroException      con error controlado
     */
    public LiderGimnasioController() throws PersistenciaException, FicheroException {
        entrenadorController = new EntrenadorController();
        liderGimnasioModelo = new LiderGimnasioModelo();
    }

    // Funciones y metodos

    /**
     * Metodo encargado de realizar la validacion del objeto liderGimnasio
     * 
     * @param liderGimnasio a validar
     * @throws PokemonException con el mensaje descriptivo de lo que sucede
     */
    public void validar(LiderGimnasio liderGimnasio) throws PokemonException {
        String mensaje = "";

        if (liderGimnasio == null) {
            mensaje = "Se esta validando un objeto nulo";
            throw new PokemonException(mensaje);
        }

        if (liderGimnasio.getIdEntrenador() <= 0) {
            mensaje = "El identificador del liderGimnasio es 0 o menor, ";
        }

        if (liderGimnasio.getMedalla() <= 0) {
            mensaje = "La Medalla del liderGimnasio es 0 o menor, ";
        }

        if (!mensaje.isEmpty()) {
            throw new PokemonException(mensaje);
        }
    }

    /**
     * Metodo encargado de insertar
     * 
     * @param liderGimnasio a insertar
     * @throws PokemonException      con mensaje controlado
     * @throws PersistenciaException con mensaje controlado
     */
    public void insertar(LiderGimnasio liderGimnasio) throws PokemonException, PersistenciaException {
        validar(liderGimnasio);
        if (existe(liderGimnasio)) {
            throw new PokemonException("El liderGimnasio indicado ya existe");
        }
        liderGimnasioModelo.insertar(liderGimnasio);
    }

    /**
     * Metodo encargado de eliminar
     * 
     * @param liderGimnasio a eliminar
     * @throws PokemonException      con mensaje controlado
     * @throws PersistenciaException con mensaje controlado
     */
    public void eliminar(LiderGimnasio liderGimnasio) throws PokemonException, PersistenciaException {
        validar(liderGimnasio);
        if (!existe(liderGimnasio)) {
            throw new PokemonException("El liderGimnasio indicado no existe");
        }
        liderGimnasioModelo.eliminar(liderGimnasio);
    }

    /**
     * Metodo encargado de realizar la eliminacion
     * 
     * @param idEntrenador del liderGimnasio a eliminar
     * @throws PokemonException      con mensaje controlado
     * @throws PersistenciaException con mensaje controlado
     */
    public void eliminar(int idEntrenador) throws PokemonException, PersistenciaException {
        LiderGimnasio liderGimnasio;
        liderGimnasio = buscar(idEntrenador);
        eliminar(liderGimnasio);
    }

    /**
     * Metodo encargado de buscar por el idEntrenador
     * 
     * @param idEntrenador para localizar el liderGimnasio
     * @return liderGimnasio a traves del idEntrenador
     * @throws PersistenciaException con mensaje controlado
     */
    public LiderGimnasio buscar(int idEntrenador) throws PersistenciaException {
        LiderGimnasio liderGimnasio = null;
        liderGimnasio = liderGimnasioModelo.buscar(idEntrenador);
        return liderGimnasio;
    }

    /**
     * Metodo encargado de realizar la modificacion de un liderGimnasio
     * 
     * @param liderGimnasio a modficar
     * @throws PokemonException      con mensaje controlado
     * @throws PersistenciaException con mensaje controlado
     */
    public void modificar(LiderGimnasio liderGimnasio) throws PokemonException, PersistenciaException {
        validar(liderGimnasio);
        if (!existe(liderGimnasio)) {
            throw new PokemonException("El liderGimnasio indicado no existe");
        }
        liderGimnasioModelo.modificar(liderGimnasio);
    }

    /**
     * Funcion encargada de verificar si existe el liderGimnasio
     * 
     * @param liderGimnasio a encontrar
     * @return true si existe o false si no existe
     * @throws PersistenciaException con mensaje controlado
     */
    private boolean existe(LiderGimnasio liderGimnasio) throws PersistenciaException {
        boolean encontrada = false;
        LiderGimnasio liderGimnasioEncontrada;

        liderGimnasioEncontrada = buscar(liderGimnasio.getIdEntrenador());
        if (liderGimnasioEncontrada != null) {
            encontrada = true;
        }
        return encontrada;
    }

}
