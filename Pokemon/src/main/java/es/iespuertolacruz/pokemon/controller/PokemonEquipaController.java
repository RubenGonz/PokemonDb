package es.iespuertolacruz.pokemon.controller;

import es.iespuertolacruz.pokemon.api.PokemonEquipa;
import es.iespuertolacruz.pokemon.excepciones.FicheroException;
import es.iespuertolacruz.pokemon.excepciones.PersistenciaException;
import es.iespuertolacruz.pokemon.excepciones.PokemonException;
import es.iespuertolacruz.pokemon.modelo.PokemonEquipaModelo;

public class PokemonEquipaController {

    PokemonController pokemonController;
    ObjetoController objetoController;
    PokemonEquipaModelo pokemonEquipaModelo;

    // Constructores

    public PokemonEquipaController() throws PersistenciaException, FicheroException {
        pokemonController = new PokemonController();
        objetoController = new ObjetoController();
        pokemonEquipaModelo = new PokemonEquipaModelo();
    }

    // Funciones y metodos

    /**
     * Metodo encargado de realizar la validacion del objeto pokemonEquipa
     * 
     * @param pokemonEquipa a validar
     * @throws PokemonException con el mensaje descriptivo de lo que sucede
     */
    public void validar(PokemonEquipa pokemonEquipa) throws PokemonException {
        String mensaje = "";

        if (pokemonEquipa == null) {
            mensaje = "Se esta validando un objeto nulo";
            throw new PokemonException(mensaje);
        }

        if (pokemonEquipa.getNumeroPokedex() <= 0) {
            mensaje = "El identificador del pokemonEquipa es 0 o menor, ";
        }

        if (pokemonEquipa.getIdObjeto() <= 0) {
            mensaje = "El identificador del pokemonEquipa es 0 o menor, ";
        }

        if (!mensaje.isEmpty()) {
            throw new PokemonException(mensaje);
        }
    }

    /**
     * Metodo encargado de insertar
     * 
     * @param pokemonEquipa a insertar
     * @throws PokemonException      con mensaje controlado
     * @throws PersistenciaException con mensaje controlado
     */
    public void insertar(PokemonEquipa pokemonEquipa) throws PokemonException, PersistenciaException {
        validar(pokemonEquipa);
        if (existe(pokemonEquipa)) {
            throw new PokemonException("El pokemonEquipa indicado ya existe");
        }
        pokemonEquipaModelo.insertar(pokemonEquipa);
    }

    /**
     * Metodo encargado de eliminar
     * 
     * @param pokemonEquipa a eliminar
     * @throws PokemonException      con mensaje controlado
     * @throws PersistenciaException con mensaje controlado
     */
    public void eliminar(PokemonEquipa pokemonEquipa) throws PokemonException, PersistenciaException {
        validar(pokemonEquipa);
        if (!existe(pokemonEquipa)) {
            throw new PokemonException("El pokemonEquipa indicado no existe");
        }
        pokemonEquipaModelo.eliminar(pokemonEquipa);
    }

    /**
     * Metodo encargado de realizar la eliminacion
     * 
     * @param nombre del pokemonEquipa a eliminar
     * @throws PokemonException      con mensaje controlado
     * @throws PersistenciaException con mensaje controlado
     */
    public void eliminar(int NumeroPokedex, int IdObjeto) throws PokemonException, PersistenciaException {
        PokemonEquipa pokemonEquipa;
        pokemonEquipa = buscar(NumeroPokedex, IdObjeto);
        eliminar(pokemonEquipa);
    }

    /**
     * Metodo encargado de buscar por el NumeroPokedex ,IdObjeto
     * 
     * @param NumeroPokedex ,IdObjeto para localizar el pokemonEquipa
     * @return pokemonEquipa a traves del NumeroPokedex ,IdObjeto
     * @throws PersistenciaException con mensaje controlado
     */
    public PokemonEquipa buscar(int NumeroPokedex, int IdObjeto) throws PersistenciaException {
        PokemonEquipa pokemonEquipa = null;
        pokemonEquipa = pokemonEquipaModelo.buscar(NumeroPokedex, IdObjeto);
        return pokemonEquipa;
    }

    /**
     * Metodo encargado de realizar la modificacion de un pokemonEquipa
     * 
     * @param pokemonEquipa a modficar
     * @throws PokemonException      con mensaje controlado
     * @throws PersistenciaException con mensaje controlado
     */
    public void modificar(PokemonEquipa pokemonEquipa) throws PokemonException, PersistenciaException {
        validar(pokemonEquipa);
        if (!existe(pokemonEquipa)) {
            throw new PokemonException("El pokemonEquipa indicado no existe");
        }
        pokemonEquipaModelo.modificar(pokemonEquipa);
    }

    /**
     * Funcion encargada de verificar si existe el pokemonEquipa
     * 
     * @param pokemonEquipa a encontrar
     * @return true si existe o false si no existe
     * @throws PersistenciaException con mensaje controlado
     */
    private boolean existe(PokemonEquipa pokemonEquipa) throws PersistenciaException {
        boolean encontrada = false;
        PokemonEquipa pokemonEquipaEncontrada;

        pokemonEquipaEncontrada = buscar(pokemonEquipa.getNumeroPokedex(), pokemonEquipa.getIdObjeto());
        if (pokemonEquipaEncontrada != null) {
            encontrada = true;
        }
        return encontrada;
    }

}
