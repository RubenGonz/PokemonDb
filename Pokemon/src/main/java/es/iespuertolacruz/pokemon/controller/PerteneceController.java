package es.iespuertolacruz.pokemon.controller;



import es.iespuertolacruz.pokemon.api.Pertenece;
import es.iespuertolacruz.pokemon.excepciones.PersistenciaException;
import es.iespuertolacruz.pokemon.excepciones.PokemonException;
import es.iespuertolacruz.pokemon.modelo.PerteneceModelo;

public class PerteneceController {

    // Variables de clase

    PerteneceModelo perteneceModelo;

    // Constructores


    public PerteneceController(PerteneceModelo perteneceModelo) {
        this.perteneceModelo = perteneceModelo;
    }
    

    // Funciones y metodos

    /**
     * Metodo encargado de realizar la validacion del objeto pertenece
     * 
     * @param pertenece a validar
     * @throws PokemonException con el mensaje descriptivo de lo que sucede
     */
    public void validar(Pertenece pertenece) throws PokemonException {
        String mensaje = "";

        if (pertenece == null) {
            mensaje = "Se esta validando un objeto nulo";
            throw new PokemonException(mensaje);
        }

        if (pertenece.getNumeroPokedex() <= 0) {
            mensaje = "El identificador del pertenece es 0 o menor, ";
        }

        if (pertenece.getTipo() == null || pertenece.getTipo().isEmpty()) {
            mensaje = "el Tipo es nulo o vacio.";
        }

        if (!mensaje.isEmpty()) {
            throw new PokemonException(mensaje);
        }
    }

    /**
     * Metodo encargado de insertar
     * 
     * @param pertenece a insertar
     * @throws PokemonException      con mensaje controlado
     * @throws PersistenciaException con mensaje controlado
     */
    public void insertar(Pertenece pertenece) throws PokemonException, PersistenciaException {
        validar(pertenece);
        if (existe(pertenece)) {
            throw new PokemonException("El pertenece indicado ya existe");
        }
        perteneceModelo.insertar(pertenece);
    }

    /**
     * Metodo encargado de eliminar
     * 
     * @param pertenece a eliminar
     * @throws PokemonException      con mensaje controlado
     * @throws PersistenciaException con mensaje controlado
     */
    public void eliminar(Pertenece pertenece) throws PokemonException, PersistenciaException {
        validar(pertenece);
        if (!existe(pertenece)) {
            throw new PokemonException("El pertenece indicado no existe");
        }
        perteneceModelo.eliminar(pertenece);
    }

    /**
     * Metodo encargado de realizar la eliminacion
     * 
     * @param nombre del pertenece a eliminar
     * @throws PokemonException      con mensaje controlado
     * @throws PersistenciaException con mensaje controlado
     */
    public void eliminar(int NumeroPokedex) throws PokemonException, PersistenciaException {
        Pertenece pertenece;
        pertenece = buscar(NumeroPokedex);
        eliminar(pertenece);
    }

    /**
     * Metodo encargado de buscar por NumeroPokedex
     * 
     * @param NumeroPokedex para localizar el pertenece
     * @return pertenece a traves del NumeroPokedex
     * @throws PersistenciaException con mensaje controlado
     */
    public Pertenece buscar(int NumeroPokedex) throws PersistenciaException {
        Pertenece pertenece = null;
        pertenece = perteneceModelo.buscar(NumeroPokedex);
        return pertenece;
    }

    /**
     * Metodo encargado de realizar la modificacion de un pertenece
     * 
     * @param pertenece a modficar
     * @throws PokemonException      con mensaje controlado
     * @throws PersistenciaException con mensaje controlado
     */
    public void modificar(Pertenece pertenece) throws PokemonException, PersistenciaException {
        validar(pertenece);
        if (!existe(pertenece)) {
            throw new PokemonException("El pertenece indicado no existe");
        }
        perteneceModelo.modificar(pertenece);
    }

    /**
     * Funcion encargada de verificar si existe el pertenece
     * 
     * @param pertenece a encontrar
     * @return true si existe o false si no existe
     * @throws PersistenciaException con mensaje controlado
     */
    private boolean existe(Pertenece pertenece) throws PersistenciaException {
        boolean encontrada = false;
        Pertenece perteneceEncontrada;

        perteneceEncontrada = buscar(pertenece.getNumeroPokedex());
        if (perteneceEncontrada != null) {
            encontrada = true;
        }
        return encontrada;
    }

}
