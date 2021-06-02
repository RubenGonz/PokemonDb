package es.iespuertolacruz.pokemon.controller;

import es.iespuertolacruz.pokemon.api.CampeonLiga;
import es.iespuertolacruz.pokemon.excepciones.FicheroException;
import es.iespuertolacruz.pokemon.excepciones.PersistenciaException;
import es.iespuertolacruz.pokemon.excepciones.PokemonException;
import es.iespuertolacruz.pokemon.modelo.CampeonLigaModelo;

/**
 * Clase principal del modelo de CampeonLigaController
 */
public class CampeonLigaController {

    // Variables de clase

    EntrenadorController entrenadorController;
    CampeonLigaModelo campeonLigaModelo;

    // Constructores

    /**
     * Constructor de CampeonLigaController con entrenadorController iniciliazado
     * por la dependencia de las tablas y campeonLigaModelo iniciliazado
     * 
     * @throws PersistenciaException con error controlado
     * @throws FicheroException      con error controlado
     */
    public CampeonLigaController() throws PersistenciaException, FicheroException {
        entrenadorController = new EntrenadorController();
        campeonLigaModelo = new CampeonLigaModelo();
    }

    // Funciones y metodos

    /**
     * Metodo encargado de realizar la validacion del objeto campeonLiga
     * 
     * @param campeonLiga a validar
     * @throws PokemonException con el mensaje descriptivo de lo que sucede
     */
    public void validar(CampeonLiga campeonLiga) throws PokemonException {
        String mensaje = "";

        if (campeonLiga == null) {
            mensaje = "Se esta validando un objeto nulo";
            throw new PokemonException(mensaje);
        }

        if (campeonLiga.getIdEntrenador() <= 0) {
            mensaje = "El identificador del campeonLiga es 0 o menor, ";
        }

        if (campeonLiga.getRegion() == null || campeonLiga.getRegion().isEmpty()) {
            mensaje = "el nombre es nulo o vacio.";
        }

        if (!mensaje.isEmpty()) {
            throw new PokemonException(mensaje);
        }
    }

    /**
     * Metodo encargado de insertar
     * 
     * @param campeonLiga a insertar
     * @throws PokemonException      con mensaje controlado
     * @throws PersistenciaException con mensaje controlado
     */
    public void insertar(CampeonLiga campeonLiga) throws PokemonException, PersistenciaException {
        validar(campeonLiga);
        if (existe(campeonLiga)) {
            throw new PokemonException("El campeonLiga indicado ya existe");
        }
        campeonLigaModelo.insertar(campeonLiga);
    }

    /**
     * Metodo encargado de eliminar
     * 
     * @param campeonLiga a eliminar
     * @throws PokemonException      con mensaje controlado
     * @throws PersistenciaException con mensaje controlado
     */
    public void eliminar(CampeonLiga campeonLiga) throws PokemonException, PersistenciaException {
        validar(campeonLiga);
        if (!existe(campeonLiga)) {
            throw new PokemonException("El campeonLiga indicado no existe");
        }
        campeonLigaModelo.eliminar(campeonLiga);
    }

    /**
     * Metodo encargado de realizar la eliminacion
     * 
     * @param idEntrenador del campeonLiga a eliminar
     * @throws PokemonException      con mensaje controlado
     * @throws PersistenciaException con mensaje controlado
     */
    public void eliminar(int idEntrenador) throws PokemonException, PersistenciaException {
        CampeonLiga campeonLiga;
        campeonLiga = buscar(idEntrenador);
        eliminar(campeonLiga);
    }

    /**
     * Metodo encargado de buscar por el idEntrenador
     * 
     * @param idEntrenador para localizar el campeonLiga
     * @return campeonLiga a traves del idEntrenador
     * @throws PersistenciaException con mensaje controlado
     */
    public CampeonLiga buscar(int idEntrenador) throws PersistenciaException {
        CampeonLiga campeonLiga = null;
        campeonLiga = campeonLigaModelo.buscar(idEntrenador);
        return campeonLiga;
    }

    /**
     * Metodo encargado de realizar la modificacion de un campeonLiga
     * 
     * @param campeonLiga a modficar
     * @throws PokemonException      con mensaje controlado
     * @throws PersistenciaException con mensaje controlado
     */
    public void modificar(CampeonLiga campeonLiga) throws PokemonException, PersistenciaException {
        validar(campeonLiga);
        if (!existe(campeonLiga)) {
            throw new PokemonException("El campeonLiga indicado no existe");
        }
        campeonLigaModelo.modificar(campeonLiga);
    }

    /**
     * Funcion encargada de verificar si existe el campeonLiga
     * 
     * @param campeonLiga a encontrar
     * @return true si existe o false si no existe
     * @throws PersistenciaException con mensaje controlado
     */
    private boolean existe(CampeonLiga campeonLiga) throws PersistenciaException {
        boolean encontrada = false;
        CampeonLiga campeonLigaEncontrada;

        campeonLigaEncontrada = buscar(campeonLiga.getIdEntrenador());
        if (campeonLigaEncontrada != null) {
            encontrada = true;
        }
        return encontrada;
    }

}
