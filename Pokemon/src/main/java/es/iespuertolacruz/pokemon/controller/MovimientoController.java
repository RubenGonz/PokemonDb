package es.iespuertolacruz.pokemon.controller;

import es.iespuertolacruz.pokemon.api.Movimiento;
import es.iespuertolacruz.pokemon.excepciones.FicheroException;
import es.iespuertolacruz.pokemon.excepciones.PersistenciaException;
import es.iespuertolacruz.pokemon.excepciones.PokemonException;
import es.iespuertolacruz.pokemon.modelo.MovimientoModelo;

public class MovimientoController {

    // Variables de clase

    TipoController tipoController;
    MovimientoModelo movimientoModelo;

    // Constructores

    /**
     * Constructor de MovimientoController con tipoController por la
     * dependencia y movimientoModelo iniciliazado
     * 
     * @throws PersistenciaException con error controlado
     * @throws FicheroException      con error controlado
     */
    public MovimientoController() throws PersistenciaException, FicheroException {
        tipoController = new TipoController();
        movimientoModelo = new MovimientoModelo();
    }

    // Funciones y metodos

    /**
     * Metodo encargado de realizar la validacion del objeto movimiento
     * 
     * @param movimiento a validar
     * @throws PokemonException con el mensaje descriptivo de lo que sucede
     */
    public void validar(Movimiento movimiento) throws PokemonException {
        String mensaje = "";

        if (movimiento == null) {
            mensaje = "Se esta validando un objeto nulo";
            throw new PokemonException(mensaje);
        }

        if (movimiento.getId() <= 0) {
            mensaje = "El identificador del movimiento es 0 o menor, ";
        }

        if (movimiento.getNombre() == null || movimiento.getNombre().isEmpty()) {
            mensaje = "el nombre es nulo o vacio.";
        }

        if (movimiento.getTipo() == null || movimiento.getTipo().isEmpty()) {
            mensaje = "el nombre es nulo o vacio.";
        }

        if (movimiento.getCategoria() == null || movimiento.getCategoria().isEmpty()) {
            mensaje = "Categoria es nulo o vacio.";
        }

        if (movimiento.getPp() <= 0) {
            mensaje = "Pp es nulo o vacio.";
        }

        if (movimiento.getPotencia() < 0) {
            mensaje = "Potencia es nulo o vacio.";
        }

        if (movimiento.getCerteza() <= 0) {
            mensaje = "Certeza es nulo o vacio.";
        }

        if (!mensaje.isEmpty()) {
            throw new PokemonException(mensaje);
        }
    }

    /**
     * Metodo encargado de insertar
     * 
     * @param movimiento a insertar
     * @throws PokemonException      con mensaje controlado
     * @throws PersistenciaException con mensaje controlado
     */
    public void insertar(Movimiento movimiento) throws PokemonException, PersistenciaException {
        validar(movimiento);
        if (existe(movimiento)) {
            throw new PokemonException("El movimiento indicado ya existe");
        }
        movimientoModelo.insertar(movimiento);
    }

    /**
     * Metodo encargado de eliminar
     * 
     * @param movimiento a eliminar
     * @throws PokemonException      con mensaje controlado
     * @throws PersistenciaException con mensaje controlado
     */
    public void eliminar(Movimiento movimiento) throws PokemonException, PersistenciaException {
        validar(movimiento);
        if (!existe(movimiento)) {
            throw new PokemonException("El movimiento indicado no existe");
        }
        movimientoModelo.eliminar(movimiento);
    }

    /**
     * Metodo encargado de realizar la eliminacion
     * 
     * @param nombre del movimiento a eliminar
     * @throws PokemonException      con mensaje controlado
     * @throws PersistenciaException con mensaje controlado
     */
    public void eliminar(int id) throws PokemonException, PersistenciaException {
        Movimiento movimiento;
        movimiento = buscar(id);
        eliminar(movimiento);
    }

    /**
     * Metodo encargado de buscar por el id
     * 
     * @param id para localizar el movimiento
     * @return movimiento a traves del id
     * @throws PersistenciaException con mensaje controlado
     */
    public Movimiento buscar(int id) throws PersistenciaException {
        Movimiento movimiento = null;
        movimiento = movimientoModelo.buscar(id);
        return movimiento;
    }

    /**
     * Metodo encargado de realizar la modificacion de un movimiento
     * 
     * @param movimiento a modficar
     * @throws PokemonException      con mensaje controlado
     * @throws PersistenciaException con mensaje controlado
     */
    public void modificar(Movimiento movimiento) throws PokemonException, PersistenciaException {
        validar(movimiento);
        if (!existe(movimiento)) {
            throw new PokemonException("El movimiento indicado no existe");
        }
        movimientoModelo.modificar(movimiento);
    }

    /**
     * Funcion encargada de verificar si existe el movimiento
     * 
     * @param movimiento a encontrar
     * @return true si existe o false si no existe
     * @throws PersistenciaException con mensaje controlado
     */
    private boolean existe(Movimiento movimiento) throws PersistenciaException {
        boolean encontrada = false;
        Movimiento movimientoEncontrada;

        movimientoEncontrada = buscar(movimiento.getId());
        if (movimientoEncontrada != null) {
            encontrada = true;
        }
        return encontrada;
    }

}
