package es.iespuertolacruz.pokemon.controller;

import es.iespuertolacruz.pokemon.api.EstadisticasBase;
import es.iespuertolacruz.pokemon.excepciones.PersistenciaException;
import es.iespuertolacruz.pokemon.excepciones.PokemonException;
import es.iespuertolacruz.pokemon.modelo.EstadisticasBaseModelo;

public class EstadisticasBaseController {

    // Variables de clase

    EstadisticasBaseModelo estadisticasBaseModelo;

    // Constructores

    public EstadisticasBaseController() throws PersistenciaException {
        estadisticasBaseModelo = new EstadisticasBaseModelo();
    }

    // Funciones y metodos

    /**
     * Metodo encargado de realizar la validacion del objeto estadisticasBase
     * 
     * @param estadisticasBase a validar
     * @throws PokemonException con el mensaje descriptivo de lo que sucede
     */
    public void validar(EstadisticasBase estadisticasBase) throws PokemonException {
        String mensaje = "";

        if (estadisticasBase == null) {
            mensaje = "Se esta validando un objeto nulo";
            throw new PokemonException(mensaje);
        }

        if (estadisticasBase.getId() <= 0) {
            mensaje = "El identificador de la estadisticasBase es 0 o menor, ";
        }

        if (estadisticasBase.getPsBase() <= 0) {
            mensaje = "los psBase son 0 o menores, ";
        }

        if (estadisticasBase.getAtaqueBase() <= 0) {
            mensaje = "el ataqueBase es 0 o menor, ";
        }

        if (estadisticasBase.getDefensaBase() <= 0) {
            mensaje = "la defensaBase es 0 o menor, ";
        }

        if (estadisticasBase.getAtaqueEspecialBase() <= 0) {
            mensaje = "el ataqueEspecialBase es 0 o menor, ";
        }

        if (estadisticasBase.getDefensaEspecialBase() <= 0) {
            mensaje = "la defensaEspecialBase es 0 o menor.";
        }

        if (estadisticasBase.getVelocidadBase() <= 0) {
            mensaje = "la velocidadBase es 0 o menor.";
        }

        if (!mensaje.isEmpty()) {
            throw new PokemonException(mensaje);
        }
    }

    /**
     * Metodo encargado de insertar
     * 
     * @param estadisticasBase a insertar
     * @throws PokemonException      con mensaje controlado
     * @throws PersistenciaException con mensaje controlado
     */
    public void insertar(EstadisticasBase estadisticasBase) throws PokemonException, PersistenciaException {
        validar(estadisticasBase);
        if (existe(estadisticasBase)) {
            throw new PokemonException("La estadisticasBase indicada ya existe");
        }
        estadisticasBaseModelo.insertar(estadisticasBase);
    }

    /**
     * Metodo encargado de eliminar
     * 
     * @param estadisticasBase a eliminar
     * @throws PokemonException      con mensaje controlado
     * @throws PersistenciaException con mensaje controlado
     */
    public void eliminar(EstadisticasBase estadisticasBase) throws PokemonException, PersistenciaException {
        validar(estadisticasBase);
        if (!existe(estadisticasBase)) {
            throw new PokemonException("La estadisticasBase indicada no existe");
        }
        estadisticasBaseModelo.eliminar(estadisticasBase);
    }

    /**
     * Metodo encargado de realizar la eliminacion
     * 
     * @param id de la estadisticasBase a eliminar
     * @throws PokemonException      con mensaje controlado
     * @throws PersistenciaException con mensaje controlado
     */
    public void eliminar(int id) throws PokemonException, PersistenciaException {
        EstadisticasBase estadisticasBase;
        estadisticasBase = buscar(id);
        eliminar(estadisticasBase);
    }

    /**
     * Metodo encargado de buscar por el id
     * 
     * @param id para localizar la estadisticasBase
     * @return estadisticasBase a traves del id
     * @throws PersistenciaException con mensaje controlado
     */
    public EstadisticasBase buscar(int id) throws PersistenciaException {
        EstadisticasBase estadisticasBase = null;
        estadisticasBase = estadisticasBaseModelo.buscar(id);
        return estadisticasBase;
    }

    /**
     * Metodo encargado de realizar la modificacion de una estadisticasBase
     * 
     * @param estadisticasBase a modficar
     * @throws PokemonException      con mensaje controlado
     * @throws PersistenciaException con mensaje controlado
     */
    public void modificar(EstadisticasBase estadisticasBase) throws PokemonException, PersistenciaException {
        validar(estadisticasBase);
        if (!existe(estadisticasBase)) {
            throw new PokemonException("La estadisticasBase indicada no existe");
        }
        estadisticasBaseModelo.modificar(estadisticasBase);
    }

    /**
     * Funcion encargada de verificar si existe la estadisticasBase
     * 
     * @param estadisticasBase a encontrar
     * @return true si existe o false si no existe
     * @throws PersistenciaException con mensaje controlado
     */
    private boolean existe(EstadisticasBase estadisticasBase) throws PersistenciaException {
        boolean encontrada = false;
        EstadisticasBase estadisticasBaseEncontrada;

        estadisticasBaseEncontrada = buscar(estadisticasBase.getId());
        if (estadisticasBaseEncontrada != null) {
            encontrada = true;
        }
        return encontrada;
    }

}