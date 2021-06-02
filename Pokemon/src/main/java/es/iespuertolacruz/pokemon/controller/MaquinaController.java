package es.iespuertolacruz.pokemon.controller;

import es.iespuertolacruz.pokemon.api.Maquina;
import es.iespuertolacruz.pokemon.excepciones.FicheroException;
import es.iespuertolacruz.pokemon.excepciones.PersistenciaException;
import es.iespuertolacruz.pokemon.excepciones.PokemonException;
import es.iespuertolacruz.pokemon.modelo.MaquinaModelo;

/**
 * Clase controller de las maquinas
 */
public class MaquinaController {

    // Variables de clase

    ObjetoController objetoController;
    MovimientoController movimientoController;
    MaquinaModelo maquinaModelo;

    // Constructores

    /**
     * Constructor de MaquinaController con objetoController y movimientoController
     * inicializado por la dependencia y maquinaModelo iniciliazado
     * 
     * @throws PersistenciaException con error controlado
     * @throws FicheroException      con error controlado
     */
    public MaquinaController() throws PersistenciaException, FicheroException {
        objetoController = new ObjetoController();
        movimientoController = new MovimientoController();
        maquinaModelo = new MaquinaModelo();
    }

    // Funciones y metodos

    /**
     * Metodo encargado de realizar la validacion del objeto maquina
     * 
     * @param maquina a validar
     * @throws PokemonException con el mensaje descriptivo de lo que sucede
     */
    public void validar(Maquina maquina) throws PokemonException {
        String mensaje = "";

        if (maquina == null) {
            mensaje = "Se esta validando un objeto nulo";
            throw new PokemonException(mensaje);
        }

        if (maquina.getIdObjeto() <= 0) {
            mensaje = "El dObjeto del maquina es 0 o menor, ";
        }

        if (maquina.getIdMovimiento() <= 0) {
            mensaje = "El IdMovimientor del maquina es 0 o menor, ";
        }

        if (!mensaje.isEmpty()) {
            throw new PokemonException(mensaje);
        }
    }

    /**
     * Metodo encargado de insertar
     * 
     * @param maquina a insertar
     * @throws PokemonException      con mensaje controlado
     * @throws PersistenciaException con mensaje controlado
     */
    public void insertar(Maquina maquina) throws PokemonException, PersistenciaException {
        validar(maquina);
        if (existe(maquina)) {
            throw new PokemonException("El maquina indicado ya existe");
        }
        maquinaModelo.insertar(maquina);
    }

    /**
     * Metodo encargado de eliminar
     * 
     * @param maquina a eliminar
     * @throws PokemonException      con mensaje controlado
     * @throws PersistenciaException con mensaje controlado
     */
    public void eliminar(Maquina maquina) throws PokemonException, PersistenciaException {
        validar(maquina);
        if (!existe(maquina)) {
            throw new PokemonException("El maquina indicado no existe");
        }
        maquinaModelo.eliminar(maquina);
    }

    /**
     * Metodo encargado de realizar la eliminacion
     * 
     * @param idObjeto del maquina a eliminar
     * @throws PokemonException      con mensaje controlado
     * @throws PersistenciaException con mensaje controlado
     */
    public void eliminar(int idObjeto) throws PokemonException, PersistenciaException {
        Maquina maquina;
        maquina = buscar(idObjeto);
        eliminar(maquina);
    }

    /**
     * Metodo encargado de buscar por el idObjeto
     * 
     * @param idObjeto para localizar el maquina
     * @return maquina a traves del idObjeto
     * @throws PersistenciaException con mensaje controlado
     */
    public Maquina buscar(int idObjeto) throws PersistenciaException {
        Maquina maquina = null;
        maquina = maquinaModelo.buscar(idObjeto);
        return maquina;
    }

    /**
     * Metodo encargado de realizar la modificacion de un maquina
     * 
     * @param maquina a modficar
     * @throws PokemonException      con mensaje controlado
     * @throws PersistenciaException con mensaje controlado
     */
    public void modificar(Maquina maquina) throws PokemonException, PersistenciaException {
        validar(maquina);
        if (!existe(maquina)) {
            throw new PokemonException("El maquina indicado no existe");
        }
        maquinaModelo.modificar(maquina);
    }

    /**
     * Funcion encargada de verificar si existe el maquina
     * 
     * @param maquina a encontrar
     * @return true si existe o false si no existe
     * @throws PersistenciaException con mensaje controlado
     */
    private boolean existe(Maquina maquina) throws PersistenciaException {
        boolean encontrada = false;
        Maquina maquinaEncontrada;

        maquinaEncontrada = buscar(maquina.getIdObjeto());
        if (maquinaEncontrada != null) {
            encontrada = true;
        }
        return encontrada;
    }

}
