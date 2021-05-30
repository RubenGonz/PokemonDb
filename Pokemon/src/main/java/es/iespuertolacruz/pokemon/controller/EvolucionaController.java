package es.iespuertolacruz.pokemon.controller;



import es.iespuertolacruz.pokemon.api.Evoluciona;
import es.iespuertolacruz.pokemon.excepciones.PersistenciaException;
import es.iespuertolacruz.pokemon.excepciones.PokemonException;
import es.iespuertolacruz.pokemon.modelo.EvolucionaModelo;

public class EvolucionaController {
    // Variables de clase

    EvolucionaModelo evolucionaModelo;

    // Constructores

    

    public EvolucionaController(EvolucionaModelo evolucionaModelo) {
        this.evolucionaModelo = evolucionaModelo;
    }

    // Funciones y metodos

    /**
     * Metodo encargado de realizar la validacion del objeto evoluciona
     * 
     * @param evoluciona a validar
     * @throws PokemonException con el mensaje descriptivo de lo que sucede
     */
    public void validar(Evoluciona evoluciona) throws PokemonException {
        String mensaje = "";

        if (evoluciona == null) {
            mensaje = "Se esta validando un objeto nulo";
            throw new PokemonException(mensaje);
        }

        if (evoluciona.getNumeroPokedexOrigen() <= 0) {
            mensaje = "El identificador del evoluciona es 0 o menor, ";
        }
        if (evoluciona.getNumeroPokedexDestino() <= 0) {
            mensaje = "El identificador del evoluciona es 0 o menor, ";
        }

        if (evoluciona.getModoEvoluciona() == null || evoluciona.getModoEvoluciona().isEmpty()) {
            mensaje = "el ModoEvoluciona es nulo o vacio.";
        }

        if (!mensaje.isEmpty()) {
            throw new PokemonException(mensaje);
        }
    }

    /**
     * Metodo encargado de insertar
     * 
     * @param evoluciona a insertar
     * @throws PokemonException      con mensaje controlado
     * @throws PersistenciaException con mensaje controlado
     */
    public void insertar(Evoluciona evoluciona) throws PokemonException, PersistenciaException {
        validar(evoluciona);
        if (existe(evoluciona)) {
            throw new PokemonException("El evoluciona indicado ya existe");
        }
        evolucionaModelo.insertar(evoluciona);
    }

    /**
     * Metodo encargado de eliminar
     * 
     * @param evoluciona a eliminar
     * @throws PokemonException      con mensaje controlado
     * @throws PersistenciaException con mensaje controlado
     */
    public void eliminar(Evoluciona evoluciona) throws PokemonException, PersistenciaException {
        validar(evoluciona);
        if (!existe(evoluciona)) {
            throw new PokemonException("El evoluciona indicado no existe");
        }
        evolucionaModelo.eliminar(evoluciona);
    }

    /**
     * Metodo encargado de realizar la eliminacion
     * 
     * @param nombre del evoluciona a eliminar
     * @throws PokemonException      con mensaje controlado
     * @throws PersistenciaException con mensaje controlado
     */
    public void eliminar(int PokedexOrigen, int PokedexDestino) throws PokemonException, PersistenciaException {
        Evoluciona evoluciona;
        evoluciona = buscar(PokedexOrigen, PokedexDestino);
        eliminar(evoluciona);
    }

    /**
     * Metodo encargado de buscar por el id
     * 
     * @param id para localizar el evoluciona
     * @return evoluciona a traves del id
     * @throws PersistenciaException con mensaje controlado
     */
    public Evoluciona buscar(int PokedexOrigen, int PokedexDestino) throws PersistenciaException {
        Evoluciona evoluciona = null;
        evoluciona = evolucionaModelo.buscar(PokedexOrigen , PokedexDestino);
        return evoluciona;
    }

    /**
     * Metodo encargado de realizar la modificacion de un evoluciona
     * 
     * @param evoluciona a modficar
     * @throws PokemonException      con mensaje controlado
     * @throws PersistenciaException con mensaje controlado
     */
    public void modificar(Evoluciona evoluciona) throws PokemonException, PersistenciaException {
        validar(evoluciona);
        if (!existe(evoluciona)) {
            throw new PokemonException("El evoluciona indicado no existe");
        }
        evolucionaModelo.modificar(evoluciona);
    }

    /**
     * Funcion encargada de verificar si existe el evoluciona
     * 
     * @param evoluciona a encontrar
     * @return true si existe o false si no existe
     * @throws PersistenciaException con mensaje controlado
     */
    private boolean existe(Evoluciona evoluciona) throws PersistenciaException {
        boolean encontrada = false;
        Evoluciona evolucionaEncontrada;

        evolucionaEncontrada = buscar(evoluciona.getNumeroPokedexOrigen() ,evoluciona.getNumeroPokedexDestino());
        if (evolucionaEncontrada != null) {
            encontrada = true;
        }
        return encontrada;
    }

}
