package es.iespuertolacruz.pokemon.controller;

import es.iespuertolacruz.pokemon.api.Provoca;
import es.iespuertolacruz.pokemon.excepciones.FicheroException;
import es.iespuertolacruz.pokemon.excepciones.PersistenciaException;
import es.iespuertolacruz.pokemon.excepciones.PokemonException;
import es.iespuertolacruz.pokemon.modelo.ProvocaModelo;

public class ProvocaController {

    // Variables de clase

    EstadoController estadoController;
    MovimientoController movimientoController;
    ProvocaModelo provocaModelo;

    // Constructores

    /**
     * Constructor de ProvocaController con estadoController y movimientoController
     * por la dependencia y provocaModelo iniciliazado
     * 
     * @throws PersistenciaException con error controlado
     * @throws FicheroException      con error controlado
     */
    public ProvocaController() throws PersistenciaException, FicheroException {
        estadoController = new EstadoController();
        movimientoController = new MovimientoController();
        provocaModelo = new ProvocaModelo();
    }

    // Funciones y metodos

    /**
     * Metodo encargado de realizar la validacion del objeto provoca
     * 
     * @param provoca a validar
     * @throws PokemonException con el mensaje descriptivo de lo que sucede
     */
    public void validar(Provoca provoca) throws PokemonException {
        String mensaje = "";

        if (provoca == null) {
            mensaje = "Se esta validando un objeto nulo";
            throw new PokemonException(mensaje);
        }

        if (provoca.getIdMovimiento() <= 0) {
            mensaje = "El identificador del provoca es 0 o menor, ";
        }

        if (provoca.getIdEstado() <= 0) {
            mensaje = "El identificador del provoca es 0 o menor, ";
        }

        if (!mensaje.isEmpty()) {
            throw new PokemonException(mensaje);
        }
    }

    /**
     * Metodo encargado de insertar
     * 
     * @param provoca a insertar
     * @throws PokemonException      con mensaje controlado
     * @throws PersistenciaException con mensaje controlado
     */
    public void insertar(Provoca provoca) throws PokemonException, PersistenciaException {
        validar(provoca);
        if (existe(provoca)) {
            throw new PokemonException("El provoca indicado ya existe");
        }
        provocaModelo.insertar(provoca);
    }

    /**
     * Metodo encargado de eliminar
     * 
     * @param provoca a eliminar
     * @throws PokemonException      con mensaje controlado
     * @throws PersistenciaException con mensaje controlado
     */
    public void eliminar(Provoca provoca) throws PokemonException, PersistenciaException {
        validar(provoca);
        if (!existe(provoca)) {
            throw new PokemonException("El provoca indicado no existe");
        }
        provocaModelo.eliminar(provoca);
    }

    /**
     * Metodo encargado de realizar la eliminacion
     * 
     * @param idMovimiento del provoca a eliminar
     * @param idEstado del provoca a eliminar
     * @throws PokemonException      con mensaje controlado
     * @throws PersistenciaException con mensaje controlado
     */
    public void eliminar(int idMovimiento, int idEstado) throws PokemonException, PersistenciaException {
        Provoca provoca;
        provoca = buscar(idMovimiento, idEstado);
        eliminar(provoca);
    }

    /**
     * Metodo encargado de buscar por las claves
     * 
     * @param idMovimiento del provoca a eliminar
     * @param idEstado del provoca a eliminar
     * @return provoca a traves del id
     * @throws PersistenciaException con mensaje controlado
     */
    public Provoca buscar(int idMovimiento, int idEstado) throws PersistenciaException {
        Provoca provoca = null;
        provoca = provocaModelo.buscar(idMovimiento, idEstado);
        return provoca;
    }

    /**
     * Metodo encargado de realizar la modificacion de un provoca
     * 
     * @param provoca a modficar
     * @throws PokemonException      con mensaje controlado
     * @throws PersistenciaException con mensaje controlado
     */
    public void modificar(Provoca provoca) throws PokemonException, PersistenciaException {
        validar(provoca);
        if (!existe(provoca)) {
            throw new PokemonException("El provoca indicado no existe");
        }
        provocaModelo.modificar(provoca);
    }

    /**
     * Funcion encargada de verificar si existe el provoca
     * 
     * @param provoca a encontrar
     * @return true si existe o false si no existe
     * @throws PersistenciaException con mensaje controlado
     */
    private boolean existe(Provoca provoca) throws PersistenciaException {
        boolean encontrada = false;
        Provoca provocaEncontrada;

        provocaEncontrada = buscar(provoca.getIdMovimiento(), provoca.getIdEstado());
        if (provocaEncontrada != null) {
            encontrada = true;
        }
        return encontrada;
    }

}
