package es.iespuertolacruz.pokemon.controller;

import es.iespuertolacruz.pokemon.api.Tipo;
import es.iespuertolacruz.pokemon.excepciones.FicheroException;
import es.iespuertolacruz.pokemon.excepciones.PersistenciaException;
import es.iespuertolacruz.pokemon.excepciones.PokemonException;
import es.iespuertolacruz.pokemon.modelo.TipoModelo;

public class TipoController {

    // Variables de clase

    TipoModelo tipoModelo;

    // Constructores

    public TipoController() throws PersistenciaException, FicheroException {
        tipoModelo = new TipoModelo();
    }

    // Funciones y metodos

    /**
     * Metodo encargado de realizar la validacion del objeto tipo
     * 
     * @param tipo a validar
     * @throws PokemonException con el mensaje descriptivo de lo que sucede
     */
    public void validar(Tipo tipo) throws PokemonException {
        String mensaje = "";

        if (tipo == null) {
            mensaje = "Se esta validando un objeto nulo";
            throw new PokemonException(mensaje);
        }

        if (tipo.getNombre() == null || tipo.getNombre().isEmpty()) {
            mensaje = "El nombre del tipo es nulo o vacio, ";
        }

        if (tipo.getColor() == null || tipo.getColor().isEmpty()) {
            mensaje += "el color del tipo es nulo o vacio.";
        }

        if (!mensaje.isEmpty()) {
            throw new PokemonException(mensaje);
        }
    }

    /**
     * Metodo encargado de insertar
     * 
     * @param tipo a insertar
     * @throws PokemonException      con mensaje controlado
     * @throws PersistenciaException con mensaje controlado
     */
    public void insertar(Tipo tipo) throws PokemonException, PersistenciaException {
        validar(tipo);
        if (existe(tipo)) {
            throw new PokemonException("El tipo indicado ya existe");
        }
        tipoModelo.insertar(tipo);
    }

    /**
     * Metodo encargado de eliminar
     * 
     * @param tipo a eliminar
     * @throws PokemonException      con mensaje controlado
     * @throws PersistenciaException con mensaje controlado
     */
    public void eliminar(Tipo tipo) throws PokemonException, PersistenciaException {
        validar(tipo);
        if (!existe(tipo)) {
            throw new PokemonException("El tipo indicado no existe");
        }
        tipoModelo.eliminar(tipo);
    }

    /**
     * Metodo encargado de realizar la eliminacion
     * 
     * @param nombre del tipo a eliminar
     * @throws PokemonException      con mensaje controlado
     * @throws PersistenciaException con mensaje controlado
     */
    public void eliminar(String nombre) throws PokemonException, PersistenciaException {
        Tipo tipo;
        tipo = buscar(nombre);
        eliminar(tipo);
    }

    /**
     * Metodo encargado de buscar por el nombre
     * 
     * @param nombre para localizar el tipo
     * @return tipo a traves del nombre
     * @throws PersistenciaException con mensaje controlado
     */
    public Tipo buscar(String nombre) throws PersistenciaException {
        Tipo tipo = null;
        tipo = tipoModelo.buscar(nombre);
        return tipo;
    }

    /**
     * Metodo encargado de realizar la modificacion de un tipo
     * 
     * @param tipo a modficar
     * @throws PokemonException      con mensaje controlado
     * @throws PersistenciaException con mensaje controlado
     */
    public void modificar(Tipo tipo) throws PokemonException, PersistenciaException {
        validar(tipo);
        if (!existe(tipo)) {
            throw new PokemonException("El tipo indicado no existe");
        }
        tipoModelo.modificar(tipo);
    }

    /**
     * Funcion encargada de verificar si existe el tipo
     * 
     * @param tipo a encontrar
     * @return true si existe o false si no existe
     * @throws PersistenciaException con mensaje controlado
     */
    private boolean existe(Tipo tipo) throws PersistenciaException {
        boolean encontrado = false;
        Tipo tipoEncontrado;

        tipoEncontrado = buscar(tipo.getNombre());
        if (tipoEncontrado != null) {
            encontrado = true;
        }
        return encontrado;
    }

}