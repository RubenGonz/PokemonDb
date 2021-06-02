package es.iespuertolacruz.pokemon.controller;

import es.iespuertolacruz.pokemon.api.ObjetoComun;
import es.iespuertolacruz.pokemon.excepciones.FicheroException;
import es.iespuertolacruz.pokemon.excepciones.PersistenciaException;
import es.iespuertolacruz.pokemon.excepciones.PokemonException;
import es.iespuertolacruz.pokemon.modelo.ObjetoComunModelo;

public class ObjetoComunController {

    // Variables de clase

    ObjetoController objetoController;
    ObjetoComunModelo objetoComunModelo;

    // Constructores

    /**
     * Constructor de ObjetoComunController con objetoController por la
     * dependencia y objetoComunModelo iniciliazado
     * 
     * @throws PersistenciaException con error controlado
     * @throws FicheroException      con error controlado
     */
    public ObjetoComunController() throws PersistenciaException, FicheroException {
        objetoController = new ObjetoController();
        objetoComunModelo = new ObjetoComunModelo();
    }

    // Funciones y metodos

    /**
     * Metodo encargado de realizar la validacion del objeto objetoComun
     * 
     * @param objetoComun a validar
     * @throws PokemonException con el mensaje descriptivo de lo que sucede
     */
    public void validar(ObjetoComun objetoComun) throws PokemonException {
        String mensaje = "";

        if (objetoComun == null) {
            mensaje = "Se esta validando un objeto nulo";
            throw new PokemonException(mensaje);
        }

        if (objetoComun.getIdObjeto() <= 0) {
            mensaje = "El identificador del objetoComun es 0 o menor, ";
        }

        if (objetoComun.getEfecto() == null || objetoComun.getEfecto().isEmpty()) {
            mensaje = "el Efecto es nulo o vacio.";
        }

        if (!mensaje.isEmpty()) {
            throw new PokemonException(mensaje);
        }
    }

    /**
     * Metodo encargado de insertar
     * 
     * @param objetoComun a insertar
     * @throws PokemonException      con mensaje controlado
     * @throws PersistenciaException con mensaje controlado
     */
    public void insertar(ObjetoComun objetoComun) throws PokemonException, PersistenciaException {
        validar(objetoComun);
        if (existe(objetoComun)) {
            throw new PokemonException("El objetoComun indicado ya existe");
        }
        objetoComunModelo.insertar(objetoComun);
    }

    /**
     * Metodo encargado de eliminar
     * 
     * @param objetoComun a eliminar
     * @throws PokemonException      con mensaje controlado
     * @throws PersistenciaException con mensaje controlado
     */
    public void eliminar(ObjetoComun objetoComun) throws PokemonException, PersistenciaException {
        validar(objetoComun);
        if (!existe(objetoComun)) {
            throw new PokemonException("El objetoComun indicado no existe");
        }
        objetoComunModelo.eliminar(objetoComun);
    }

    /**
     * Metodo encargado de realizar la eliminacion
     * 
     * @param idObjeto del objetoComun a eliminar
     * @throws PokemonException      con mensaje controlado
     * @throws PersistenciaException con mensaje controlado
     */
    public void eliminar(int idObjeto) throws PokemonException, PersistenciaException {
        ObjetoComun objetoComun;
        objetoComun = buscar(idObjeto);
        eliminar(objetoComun);
    }

    /**
     * Metodo encargado de buscar por el idObjeto
     * 
     * @param idObjeto para localizar el objetoComun
     * @return objetoComun a traves del id
     * @throws PersistenciaException con mensaje controlado
     */
    public ObjetoComun buscar(int idObjeto) throws PersistenciaException {
        ObjetoComun objetoComun = null;
        objetoComun = objetoComunModelo.buscar(idObjeto);
        return objetoComun;
    }

    /**
     * Metodo encargado de realizar la modificacion de un objetoComun
     * 
     * @param objetoComun a modficar
     * @throws PokemonException      con mensaje controlado
     * @throws PersistenciaException con mensaje controlado
     */
    public void modificar(ObjetoComun objetoComun) throws PokemonException, PersistenciaException {
        validar(objetoComun);
        if (!existe(objetoComun)) {
            throw new PokemonException("El objetoComun indicado no existe");
        }
        objetoComunModelo.modificar(objetoComun);
    }

    /**
     * Funcion encargada de verificar si existe el objetoComun
     * 
     * @param objetoComun a encontrar
     * @return true si existe o false si no existe
     * @throws PersistenciaException con mensaje controlado
     */
    private boolean existe(ObjetoComun objetoComun) throws PersistenciaException {
        boolean encontrada = false;
        ObjetoComun objetoComunEncontrada;

        objetoComunEncontrada = buscar(objetoComun.getIdObjeto());
        if (objetoComunEncontrada != null) {
            encontrada = true;
        }
        return encontrada;
    }

}
