package es.iespuertolacruz.pokemon.controller;

import es.iespuertolacruz.pokemon.api.Caracteristicas;
import es.iespuertolacruz.pokemon.excepciones.FicheroException;
import es.iespuertolacruz.pokemon.excepciones.PersistenciaException;
import es.iespuertolacruz.pokemon.excepciones.PokemonException;
import es.iespuertolacruz.pokemon.modelo.CaracteristicasModelo;

public class CaracteristicasController {

    // Variables de clase

    CaracteristicasModelo caracteristicasModelo;

    // Constructores

    public CaracteristicasController() throws PersistenciaException, FicheroException {
        caracteristicasModelo = new CaracteristicasModelo();
    }

    // Funciones y metodos

    /**
     * Metodo encargado de realizar la validacion del objeto caracteristicas
     * 
     * @param caracteristicas a validar
     * @throws PokemonException con el mensaje descriptivo de lo que sucede
     */
    public void validar(Caracteristicas caracteristicas) throws PokemonException {
        String mensaje = "";

        if (caracteristicas == null) {
            mensaje = "Se esta validando un objeto nulo";
            throw new PokemonException(mensaje);
        }

        if (caracteristicas.getId() <= 0) {
            mensaje = "El identificador de la caracteristica es 0 o menor, ";
        }

        if (caracteristicas.getPeso() <= 0) {
            mensaje = "el peso es 0.0 o menor, ";
        }

        if (caracteristicas.getAltura() <= 0) {
            mensaje = "la altura es 0.0 o menor, ";
        }

        if (caracteristicas.getEspecie() == null || caracteristicas.getEspecie().isEmpty()) {
            mensaje += "la especie es nula o vacia, ";
        }

        if (caracteristicas.getHabilidad() == null || caracteristicas.getHabilidad().isEmpty()) {
            mensaje += "la habilidad es nula o vacia, ";
        }

        if (caracteristicas.getCategoria() == null || caracteristicas.getCategoria().isEmpty()) {
            mensaje += "la categoria es nula o vacia.";
        }

        if (!mensaje.isEmpty()) {
            throw new PokemonException(mensaje);
        }
    }

    /**
     * Metodo encargado de insertar
     * 
     * @param caracteristicas a insertar
     * @throws PokemonException      con mensaje controlado
     * @throws PersistenciaException con mensaje controlado
     */
    public void insertar(Caracteristicas caracteristicas) throws PokemonException, PersistenciaException {
        validar(caracteristicas);
        if (existe(caracteristicas)) {
            throw new PokemonException("La caracteristica indicada ya existe");
        }
        caracteristicasModelo.insertar(caracteristicas);
    }

    /**
     * Metodo encargado de eliminar
     * 
     * @param caracteristicas a eliminar
     * @throws PokemonException      con mensaje controlado
     * @throws PersistenciaException con mensaje controlado
     */
    public void eliminar(Caracteristicas caracteristicas) throws PokemonException, PersistenciaException {
        validar(caracteristicas);
        if (!existe(caracteristicas)) {
            throw new PokemonException("La caracteristica indicada no existe");
        }
        caracteristicasModelo.eliminar(caracteristicas);
    }

    /**
     * Metodo encargado de realizar la eliminacion
     * 
     * @param nombre del caracteristicas a eliminar
     * @throws PokemonException      con mensaje controlado
     * @throws PersistenciaException con mensaje controlado
     */
    public void eliminar(int id) throws PokemonException, PersistenciaException {
        Caracteristicas caracteristicas;
        caracteristicas = buscar(id);
        eliminar(caracteristicas);
    }

    /**
     * Metodo encargado de buscar por el nombre
     * 
     * @param nombre para localizar el caracteristicas
     * @return caracteristicas a traves del nombre
     * @throws PersistenciaException con mensaje controlado
     */
    public Caracteristicas buscar(int id) throws PersistenciaException {
        Caracteristicas caracteristicas = null;
        caracteristicas = caracteristicasModelo.buscar(id);
        return caracteristicas;
    }

    /**
     * Metodo encargado de realizar la modificacion de un caracteristicas
     * 
     * @param caracteristicas a modficar
     * @throws PokemonException      con mensaje controlado
     * @throws PersistenciaException con mensaje controlado
     */
    public void modificar(Caracteristicas caracteristicas) throws PokemonException, PersistenciaException {
        validar(caracteristicas);
        if (!existe(caracteristicas)) {
            throw new PokemonException("La caracteristica indicada no existe");
        }
        caracteristicasModelo.modificar(caracteristicas);
    }

    /**
     * Funcion encargada de verificar si existe el caracteristicas
     * 
     * @param caracteristicas a encontrar
     * @return true si existe o false si no existe
     * @throws PersistenciaException con mensaje controlado
     */
    private boolean existe(Caracteristicas caracteristicas) throws PersistenciaException {
        boolean encontrada = false;
        Caracteristicas caracteristicaEncontrada;

        caracteristicaEncontrada = buscar(caracteristicas.getId());
        if (caracteristicaEncontrada != null) {
            encontrada = true;
        }
        return encontrada;
    }

}