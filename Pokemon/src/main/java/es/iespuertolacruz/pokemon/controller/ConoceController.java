package es.iespuertolacruz.pokemon.controller;

import es.iespuertolacruz.pokemon.api.Conoce;
import es.iespuertolacruz.pokemon.excepciones.FicheroException;
import es.iespuertolacruz.pokemon.excepciones.PersistenciaException;
import es.iespuertolacruz.pokemon.excepciones.PokemonException;
import es.iespuertolacruz.pokemon.modelo.ConoceModelo;

public class ConoceController {

    // Variables de clase

    PokemonController pokemonController;
    MovimientoController movimientoController;
    ConoceModelo conoceModelo;

    // Constructores

    /**
     * Constructor de ConoceController con pokemonController y movimientoController
     * inicializado por la dependencia y conoceModelo inicializado
     * 
     * @throws PersistenciaException con error controlado
     * @throws FicheroException      con error controlado
     */
    public ConoceController() throws PersistenciaException, FicheroException {
        pokemonController = new PokemonController();
        movimientoController = new MovimientoController();
        conoceModelo = new ConoceModelo();
    }

    // Funciones y metodos

    /**
     * Metodo encargado de realizar la validacion del objeto conoce
     * 
     * @param conoce a validar
     * @throws PokemonException con el mensaje descriptivo de lo que sucede
     */
    public void validar(Conoce conoce) throws PokemonException {
        String mensaje = "";

        if (conoce == null) {
            mensaje = "Se esta validando un objeto nulo";
            throw new PokemonException(mensaje);
        }

        if (conoce.getIdMovimiento() <= 0) {
            mensaje = "El identificador del conoce es 0 o menor, ";
        }

        if (conoce.getNumeroPokedex() <= 0) {
            mensaje = "El numeroPokedex del conoce es 0 o menor, ";
        }

        if (!mensaje.isEmpty()) {
            throw new PokemonException(mensaje);
        }
    }

    /**
     * Metodo encargado de insertar
     * 
     * @param conoce a insertar
     * @throws PokemonException      con mensaje controlado
     * @throws PersistenciaException con mensaje controlado
     */
    public void insertar(Conoce conoce) throws PokemonException, PersistenciaException {
        validar(conoce);
        if (existe(conoce)) {
            throw new PokemonException("El conoce indicado ya existe");
        }
        conoceModelo.insertar(conoce);
    }

    /**
     * Metodo encargado de eliminar
     * 
     * @param conoce a eliminar
     * @throws PokemonException      con mensaje controlado
     * @throws PersistenciaException con mensaje controlado
     */
    public void eliminar(Conoce conoce) throws PokemonException, PersistenciaException {
        validar(conoce);
        if (!existe(conoce)) {
            throw new PokemonException("El conoce indicado no existe");
        }
        conoceModelo.eliminar(conoce);
    }

    /**
     * Metodo encargado de realizar la eliminación
     * 
     * @param numeroPokedex numero de la pokedex del pokemon
     * @param idMovimiento identificador del movimiento
     * @throws PokemonException con error controlado
     * @throws PersistenciaException con error controlado
     */
    public void eliminar(int numeroPokedex, int idMovimiento) throws PokemonException, PersistenciaException {
        Conoce conoce;
        conoce = buscar(numeroPokedex, idMovimiento);
        eliminar(conoce);
    }

    /**
     * Metodo encargado de buscar por el numeroPokedex y el idMovimiento
     * 
     * @param numeroPokedex para localizar el conoce
     * @param idMovimiento para localizar el conoce
     * @return conoce a traves de los atributos
     * @throws PersistenciaException con mensaje controlado
     */
    public Conoce buscar(int numeroPokedex, int idMovimiento) throws PersistenciaException {
        Conoce conoce = null;
        conoce = conoceModelo.buscar(numeroPokedex, idMovimiento);
        return conoce;
    }

    /**
     * Metodo encargado de realizar la modificacion de un conoce
     * 
     * @param conoce a modficar
     * @throws PokemonException      con mensaje controlado
     * @throws PersistenciaException con mensaje controlado
     */
    public void modificar(Conoce conoce) throws PokemonException, PersistenciaException {
        validar(conoce);
        if (!existe(conoce)) {
            throw new PokemonException("El conoce indicado no existe");
        }
        conoceModelo.modificar(conoce);
    }

    /**
     * Funcion encargada de verificar si existe el conoce
     * 
     * @param conoce a encontrar
     * @return true si existe o false si no existe
     * @throws PersistenciaException con mensaje controlado
     */
    private boolean existe(Conoce conoce) throws PersistenciaException {
        boolean encontrada = false;
        Conoce conoceEncontrada;

        conoceEncontrada = buscar(conoce.getNumeroPokedex(), conoce.getIdMovimiento());
        if (conoceEncontrada != null) {
            encontrada = true;
        }
        return encontrada;
    }

}
