package es.iespuertolacruz.pokemon.controller;


import es.iespuertolacruz.pokemon.api.Conoce;
import es.iespuertolacruz.pokemon.excepciones.PersistenciaException;
import es.iespuertolacruz.pokemon.excepciones.PokemonException;
import es.iespuertolacruz.pokemon.modelo.ConoceModelo;



public class ConoceController {
    
    // Variables de clase

    ConoceModelo conoceModelo;

    // Constructores


    public ConoceController(ConoceModelo conoceModelo) {
        this.conoceModelo = conoceModelo;
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
     * Metodo encargado de realizar la eliminacion
     * 
     * @param nombre del conoce a eliminar
     * @throws PokemonException      con mensaje controlado
     * @throws PersistenciaException con mensaje controlado
     */
    public void eliminar(int id) throws PokemonException, PersistenciaException {
        Conoce conoce;
        conoce = buscar(id);
        eliminar(conoce);
    }

    /**
     * Metodo encargado de buscar por el id
     * 
     * @param id para localizar el conoce
     * @return conoce a traves del id
     * @throws PersistenciaException con mensaje controlado
     */
    public Conoce buscar(int NumeroPokedex) throws PersistenciaException {
        Conoce conoce = null;
        conoce = conoceModelo.buscar(NumeroPokedex);
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

        conoceEncontrada = buscar(conoce.getNumeroPokedex());
        if (conoceEncontrada != null) {
            encontrada = true;
        }
        return encontrada;
    }

}
