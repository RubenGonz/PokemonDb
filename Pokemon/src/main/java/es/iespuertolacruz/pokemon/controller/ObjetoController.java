package es.iespuertolacruz.pokemon.controller;

import es.iespuertolacruz.pokemon.api.Objeto;
import es.iespuertolacruz.pokemon.excepciones.PersistenciaException;
import es.iespuertolacruz.pokemon.excepciones.PokemonException;
import es.iespuertolacruz.pokemon.modelo.ObjetoModelo;

public class ObjetoController {
    
    // Variables de clase

    ObjetoModelo objetoModelo;

    // Constructores

    public ObjetoController() throws PersistenciaException {
        objetoModelo = new ObjetoModelo();
    }

    // Funciones y metodos

    /**
     * Metodo encargado de realizar la validacion del objeto objeto
     * 
     * @param objeto a validar
     * @throws PokemonException con el mensaje descriptivo de lo que sucede
     */
    public void validar(Objeto objeto) throws PokemonException {
        String mensaje = "";

        if (objeto == null) {
            mensaje = "Se esta validando un objeto nulo";
            throw new PokemonException(mensaje);
        }

        if (objeto.getId() <= 0) {
            mensaje = "El identificador del objeto es 0 o menor, ";
        }

        if (objeto.getNombre() == null || objeto.getNombre().isEmpty()) {
            mensaje = "el nombre es nulo o vacio, ";
        }

        if (objeto.getModoObtencion() == null || objeto.getModoObtencion().isEmpty()) {
            mensaje = "el modo de obtencion es nulo o vacio.";
        }

        if (!mensaje.isEmpty()) {
            throw new PokemonException(mensaje);
        }
    }

    /**
     * Metodo encargado de insertar
     * 
     * @param objeto a insertar
     * @throws PokemonException        con mensaje controlado
     * @throws PersistenciaException con mensaje controlado
     */
    public void insertar(Objeto objeto) throws PokemonException, PersistenciaException {
        validar(objeto);
        if (existe(objeto)) {
            throw new PokemonException("El objeto indicado ya existe");
        }
        objetoModelo.insertar(objeto);
    }

    /**
     * Metodo encargado de eliminar
     * 
     * @param objeto a eliminar
     * @throws PokemonException        con mensaje controlado
     * @throws PersistenciaException con mensaje controlado
     */
    public void eliminar(Objeto objeto) throws PokemonException, PersistenciaException {
        validar(objeto);
        if (!existe(objeto)) {
            throw new PokemonException("El objeto indicado no existe");
        }
        objetoModelo.eliminar(objeto);
    }

    /**
     * Metodo encargado de realizar la eliminacion
     * 
     * @param nombre del objeto a eliminar
     * @throws PokemonException        con mensaje controlado
     * @throws PersistenciaException con mensaje controlado
     */
    public void eliminar(int id) throws PokemonException, PersistenciaException {
        Objeto objeto;
        objeto = buscar(id);
        eliminar(objeto);
    }

    /**
     * Metodo encargado de buscar por el id
     * 
     * @param id para localizar el objeto
     * @return objeto a traves del id
     * @throws PersistenciaException con mensaje controlado
     */
    public Objeto buscar(int id) throws PersistenciaException {
        Objeto objeto = null;
        objeto = objetoModelo.buscar(id);
        return objeto;
    }

    /**
     * Metodo encargado de realizar la modificacion de un objeto
     * 
     * @param objeto a modficar
     * @throws PokemonException        con mensaje controlado
     * @throws PersistenciaException con mensaje controlado
     */
    public void modificar(Objeto objeto) throws PokemonException, PersistenciaException {
        validar(objeto);
        if (!existe(objeto)) {
            throw new PokemonException("El objeto indicado no existe");
        }
        objetoModelo.modificar(objeto);
    }

    /**
     * Funcion encargada de verificar si existe el objeto
     * 
     * @param objeto a encontrar
     * @return true si existe o false si no existe
     * @throws PersistenciaException con mensaje controlado
     */
    private boolean existe(Objeto objeto) throws PersistenciaException {
        boolean encontrada = false;
        Objeto objetoEncontrada;

        objetoEncontrada = buscar(objeto.getId());
        if (objetoEncontrada != null) {
            encontrada = true;
        }
        return encontrada;
    }

}