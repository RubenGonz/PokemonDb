package es.iespuertolacruz.pokemon.controller;

import es.iespuertolacruz.pokemon.api.Estado;
import es.iespuertolacruz.pokemon.excepciones.FicheroException;
import es.iespuertolacruz.pokemon.excepciones.PersistenciaException;
import es.iespuertolacruz.pokemon.excepciones.PokemonException;
import es.iespuertolacruz.pokemon.modelo.EstadoModelo;

public class EstadoController {

    // Variables de clase

    EstadoModelo estadoModelo;

    // Constructores

    public EstadoController() throws PersistenciaException, FicheroException {
        estadoModelo = new EstadoModelo();
    }

    // Funciones y metodos

    /**
     * Metodo encargado de realizar la validacion del objeto estado
     * 
     * @param estado a validar
     * @throws PokemonException con el mensaje descriptivo de lo que sucede
     */
    public void validar(Estado estado) throws PokemonException {
        String mensaje = "";

        if (estado == null) {
            mensaje = "Se esta validando un objeto nulo";
            throw new PokemonException(mensaje);
        }

        if (estado.getId() <= 0) {
            mensaje = "El identificador del estado es 0 o menor, ";
        }

        if (estado.getNombre() == null || estado.getNombre().isEmpty()) {
            mensaje = "el nombre es nulo o vacio, ";
        }

        if (estado.getPersistencia() != 1 && estado.getPersistencia() != 2) {
            mensaje = "la persistencia es distinta de 1 o 2, ";
        }

        if (estado.getEfecto() == null || estado.getEfecto().isEmpty()) {
            mensaje += "el efecto es nulo o vacio.";
        }

        if (!mensaje.isEmpty()) {
            throw new PokemonException(mensaje);
        }
    }

    /**
     * Metodo encargado de insertar
     * 
     * @param estado a insertar
     * @throws PokemonException      con mensaje controlado
     * @throws PersistenciaException con mensaje controlado
     */
    public void insertar(Estado estado) throws PokemonException, PersistenciaException {
        validar(estado);
        if (existe(estado)) {
            throw new PokemonException("El estado indicado ya existe");
        }
        estadoModelo.insertar(estado);
    }

    /**
     * Metodo encargado de eliminar
     * 
     * @param estado a eliminar
     * @throws PokemonException      con mensaje controlado
     * @throws PersistenciaException con mensaje controlado
     */
    public void eliminar(Estado estado) throws PokemonException, PersistenciaException {
        validar(estado);
        if (!existe(estado)) {
            throw new PokemonException("El estado indicado no existe");
        }
        estadoModelo.eliminar(estado);
    }

    /**
     * Metodo encargado de realizar la eliminacion
     * 
     * @param nombre del estado a eliminar
     * @throws PokemonException      con mensaje controlado
     * @throws PersistenciaException con mensaje controlado
     */
    public void eliminar(int id) throws PokemonException, PersistenciaException {
        Estado estado;
        estado = buscar(id);
        eliminar(estado);
    }

    /**
     * Metodo encargado de buscar por el id
     * 
     * @param id para localizar el estado
     * @return estado a traves del id
     * @throws PersistenciaException con mensaje controlado
     */
    public Estado buscar(int id) throws PersistenciaException {
        Estado estado = null;
        estado = estadoModelo.buscar(id);
        return estado;
    }

    /**
     * Metodo encargado de realizar la modificacion de un estado
     * 
     * @param estado a modficar
     * @throws PokemonException      con mensaje controlado
     * @throws PersistenciaException con mensaje controlado
     */
    public void modificar(Estado estado) throws PokemonException, PersistenciaException {
        validar(estado);
        if (!existe(estado)) {
            throw new PokemonException("El estado indicado no existe");
        }
        estadoModelo.modificar(estado);
    }

    /**
     * Funcion encargada de verificar si existe el estado
     * 
     * @param estado a encontrar
     * @return true si existe o false si no existe
     * @throws PersistenciaException con mensaje controlado
     */
    private boolean existe(Estado estado) throws PersistenciaException {
        boolean encontrada = false;
        Estado estadoEncontrada;

        estadoEncontrada = buscar(estado.getId());
        if (estadoEncontrada != null) {
            encontrada = true;
        }
        return encontrada;
    }

}