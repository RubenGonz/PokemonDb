package es.iespuertolacruz.pokemon.controller;

import es.iespuertolacruz.pokemon.api.AltoMando;
import es.iespuertolacruz.pokemon.excepciones.PersistenciaException;
import es.iespuertolacruz.pokemon.excepciones.PokemonException;
import es.iespuertolacruz.pokemon.modelo.AltoMandoModelo;

public class AltoMandoController {
   
    // Variables de clase

    AltoMandoModelo altoMandoModelo;

    // Constructores


    public AltoMandoController(AltoMandoModelo altoMandoModelo) {
        this.altoMandoModelo = altoMandoModelo;
    }
    

    // Funciones y metodos

    public AltoMandoController() {
    }


    /**
     * Metodo encargado de realizar la validacion del objeto altoMando
     * 
     * @param altoMando a validar
     * @throws PokemonException con el mensaje descriptivo de lo que sucede
     */
    public void validar(AltoMando altoMando) throws PokemonException {
        String mensaje = "";

        if (altoMando == null) {
            mensaje = "Se esta validando un objeto nulo";
            throw new PokemonException(mensaje);
        }

        if (altoMando.getIdEntrenador() <= 0) {
            mensaje = "El identificador del altoMando es 0 o menor, ";
        }

        if (altoMando.getTipoPrincipal() == null || altoMando.getTipoPrincipal().isEmpty()) {
            mensaje = "el TipoPrincipal es nulo o vacio.";
        }

        if (!mensaje.isEmpty()) {
            throw new PokemonException(mensaje);
        }
    }

    /**
     * Metodo encargado de insertar
     * 
     * @param altoMando a insertar
     * @throws PokemonException      con mensaje controlado
     * @throws PersistenciaException con mensaje controlado
     */
    public void insertar(AltoMando altoMando) throws PokemonException, PersistenciaException {
        validar(altoMando);
        if (existe(altoMando)) {
            throw new PokemonException("El altoMando indicado ya existe");
        }
        altoMandoModelo.insertar(altoMando);
    }

    /**
     * Metodo encargado de eliminar
     * 
     * @param altoMando a eliminar
     * @throws PokemonException      con mensaje controlado
     * @throws PersistenciaException con mensaje controlado
     */
    public void eliminar(AltoMando altoMando) throws PokemonException, PersistenciaException {
        validar(altoMando);
        if (!existe(altoMando)) {
            throw new PokemonException("El altoMando indicado no existe");
        }
        altoMandoModelo.eliminar(altoMando);
    }

    /**
     * Metodo encargado de realizar la eliminacion
     * 
     * @param nombre del altoMando a eliminar
     * @throws PokemonException      con mensaje controlado
     * @throws PersistenciaException con mensaje controlado
     */
    public void eliminar(int IdEntrenador) throws PokemonException, PersistenciaException {
        AltoMando altoMando;
        altoMando = buscar(IdEntrenador);
        eliminar(altoMando);
    }

    /**
     * Metodo encargado de buscar por el IdEntrenador
     * 
     * @param IdEntrenador para localizar el altoMando
     * @return altoMando a traves del IdEntrenador
     * @throws PersistenciaException con mensaje controlado
     */
    public AltoMando buscar(int IdEntrenador) throws PersistenciaException {
        AltoMando altoMando = null;
        altoMando = altoMandoModelo.buscar(IdEntrenador);
        return altoMando;
    }

    /**
     * Metodo encargado de realizar la modificacion de un altoMando
     * 
     * @param altoMando a modficar
     * @throws PokemonException      con mensaje controlado
     * @throws PersistenciaException con mensaje controlado
     */
    public void modificar(AltoMando altoMando) throws PokemonException, PersistenciaException {
        validar(altoMando);
        if (!existe(altoMando)) {
            throw new PokemonException("El altoMando indicado no existe");
        }
        altoMandoModelo.modificar(altoMando);
    }

    /**
     * Funcion encargada de verificar si existe el altoMando
     * 
     * @param altoMando a encontrar
     * @return true si existe o false si no existe
     * @throws PersistenciaException con mensaje controlado
     */
    private boolean existe(AltoMando altoMando) throws PersistenciaException {
        boolean encontrada = false;
        AltoMando altoMandoEncontrada;

        altoMandoEncontrada = buscar(altoMando.getIdEntrenador());
        if (altoMandoEncontrada != null) {
            encontrada = true;
        }
        return encontrada;
    }
}
