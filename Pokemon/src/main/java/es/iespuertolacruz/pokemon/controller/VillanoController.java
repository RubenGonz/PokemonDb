package es.iespuertolacruz.pokemon.controller;

import es.iespuertolacruz.pokemon.api.Villano;
import es.iespuertolacruz.pokemon.excepciones.PersistenciaException;
import es.iespuertolacruz.pokemon.excepciones.PokemonException;
import es.iespuertolacruz.pokemon.modelo.VillanoModelo;

public class VillanoController {

    // Variables de clase

    EntrenadorController entrenadorController;
    VillanoModelo villanoModelo;

    // Constructores

    public VillanoController() throws PersistenciaException {
        entrenadorController = new EntrenadorController();
        villanoModelo = new VillanoModelo();
    }

    // Funciones y metodos

    /**
     * Metodo encargado de realizar la validacion del objeto villano
     * 
     * @param villano a validar
     * @throws PokemonException con el mensaje descriptivo de lo que sucede
     */
    public void validar(Villano villano) throws PokemonException {
        String mensaje = "";

        if (villano == null) {
            mensaje = "Se esta validando un objeto nulo";
            throw new PokemonException(mensaje);
        }

        if (villano.getIdEntrenador() <= 0) {
            mensaje = "El identificador del villano es 0 o menor, ";
        }

        if (villano.getProposito() == null || villano.getProposito().isEmpty()) {
            mensaje = "el Proposito es nulo o vacio.";
        }

        if (!mensaje.isEmpty()) {
            throw new PokemonException(mensaje);
        }
    }

    /**
     * Metodo encargado de insertar
     * 
     * @param villano a insertar
     * @throws PokemonException      con mensaje controlado
     * @throws PersistenciaException con mensaje controlado
     */
    public void insertar(Villano villano) throws PokemonException, PersistenciaException {
        validar(villano);
        if (existe(villano)) {
            throw new PokemonException("El villano indicado ya existe");
        }
        villanoModelo.insertar(villano);
    }

    /**
     * Metodo encargado de eliminar
     * 
     * @param villano a eliminar
     * @throws PokemonException      con mensaje controlado
     * @throws PersistenciaException con mensaje controlado
     */
    public void eliminar(Villano villano) throws PokemonException, PersistenciaException {
        validar(villano);
        if (!existe(villano)) {
            throw new PokemonException("El villano indicado no existe");
        }
        villanoModelo.eliminar(villano);
    }

    /**
     * Metodo encargado de realizar la eliminacion
     * 
     * @param nombre del villano a eliminar
     * @throws PokemonException      con mensaje controlado
     * @throws PersistenciaException con mensaje controlado
     */
    public void eliminar(int IdEntrenador) throws PokemonException, PersistenciaException {
        Villano villano;
        villano = buscar(IdEntrenador);
        eliminar(villano);
    }

    /**
     * Metodo encargado de buscar por el id
     * 
     * @param id para localizar el villano
     * @return villano a traves del id
     * @throws PersistenciaException con mensaje controlado
     */
    public Villano buscar(int IdEntrenador) throws PersistenciaException {
        Villano villano = null;
        villano = villanoModelo.buscar(IdEntrenador);
        return villano;
    }

    /**
     * Metodo encargado de realizar la modificacion de un villano
     * 
     * @param villano a modficar
     * @throws PokemonException      con mensaje controlado
     * @throws PersistenciaException con mensaje controlado
     */
    public void modificar(Villano villano) throws PokemonException, PersistenciaException {
        validar(villano);
        if (!existe(villano)) {
            throw new PokemonException("El villano indicado no existe");
        }
        villanoModelo.modificar(villano);
    }

    /**
     * Funcion encargada de verificar si existe el villano
     * 
     * @param villano a encontrar
     * @return true si existe o false si no existe
     * @throws PersistenciaException con mensaje controlado
     */
    private boolean existe(Villano villano) throws PersistenciaException {
        boolean encontrada = false;
        Villano villanoEncontrada;

        villanoEncontrada = buscar(villano.getIdEntrenador());
        if (villanoEncontrada != null) {
            encontrada = true;
        }
        return encontrada;
    }

}
