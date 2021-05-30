package es.iespuertolacruz.pokemon.controller;



import es.iespuertolacruz.pokemon.api.Pokeball;
import es.iespuertolacruz.pokemon.excepciones.PersistenciaException;
import es.iespuertolacruz.pokemon.excepciones.PokemonException;
import es.iespuertolacruz.pokemon.modelo.PokeballModelo;

public class PokeballController {
    // Variables de clase

    PokeballModelo pokeballModelo;

    // Constructores


    public PokeballController(PokeballModelo pokeballModelo) {
        this.pokeballModelo = pokeballModelo;
    }
    

    // Funciones y metodos

    /**
     * Metodo encargado de realizar la validacion del objeto pokeball
     * 
     * @param pokeball a validar
     * @throws PokemonException con el mensaje descriptivo de lo que sucede
     */
    public void validar(Pokeball pokeball) throws PokemonException {
        String mensaje = "";

        if (pokeball == null) {
            mensaje = "Se esta validando un objeto nulo";
            throw new PokemonException(mensaje);
        }

        if (pokeball.getIdObjeto() <= 0) {
            mensaje = "El identificador del pokeball es 0 o menor, ";
        }

        if (pokeball.getRatio() <= 0) {
            mensaje = "El Ratio del pokeball es 0 o menor, ";
        }

        if (!mensaje.isEmpty()) {
            throw new PokemonException(mensaje);
        }
    }

    /**
     * Metodo encargado de insertar
     * 
     * @param pokeball a insertar
     * @throws PokemonException      con mensaje controlado
     * @throws PersistenciaException con mensaje controlado
     */
    public void insertar(Pokeball pokeball) throws PokemonException, PersistenciaException {
        validar(pokeball);
        if (existe(pokeball)) {
            throw new PokemonException("El pokeball indicado ya existe");
        }
        pokeballModelo.insertar(pokeball);
    }

    /**
     * Metodo encargado de eliminar
     * 
     * @param pokeball a eliminar
     * @throws PokemonException      con mensaje controlado
     * @throws PersistenciaException con mensaje controlado
     */
    public void eliminar(Pokeball pokeball) throws PokemonException, PersistenciaException {
        validar(pokeball);
        if (!existe(pokeball)) {
            throw new PokemonException("El pokeball indicado no existe");
        }
        pokeballModelo.eliminar(pokeball);
    }

    /**
     * Metodo encargado de realizar la eliminacion
     * 
     * @param nombre del pokeball a eliminar
     * @throws PokemonException      con mensaje controlado
     * @throws PersistenciaException con mensaje controlado
     */
    public void eliminar(int IdObjeto) throws PokemonException, PersistenciaException {
        Pokeball pokeball;
        pokeball = buscar(IdObjeto);
        eliminar(pokeball);
    }

    /**
     * Metodo encargado de buscar por el id
     * 
     * @param id para localizar el pokeball
     * @return pokeball a traves del id
     * @throws PersistenciaException con mensaje controlado
     */
    public Pokeball buscar(int IdObjeto) throws PersistenciaException {
        Pokeball pokeball = null;
        pokeball = pokeballModelo.buscar(IdObjeto);
        return pokeball;
    }

    /**
     * Metodo encargado de realizar la modificacion de un pokeball
     * 
     * @param pokeball a modficar
     * @throws PokemonException      con mensaje controlado
     * @throws PersistenciaException con mensaje controlado
     */
    public void modificar(Pokeball pokeball) throws PokemonException, PersistenciaException {
        validar(pokeball);
        if (!existe(pokeball)) {
            throw new PokemonException("El pokeball indicado no existe");
        }
        pokeballModelo.modificar(pokeball);
    }

    /**
     * Funcion encargada de verificar si existe el pokeball
     * 
     * @param pokeball a encontrar
     * @return true si existe o false si no existe
     * @throws PersistenciaException con mensaje controlado
     */
    private boolean existe(Pokeball pokeball) throws PersistenciaException {
        boolean encontrada = false;
        Pokeball pokeballEncontrada;

        pokeballEncontrada = buscar(pokeball.getIdObjeto());
        if (pokeballEncontrada != null) {
            encontrada = true;
        }
        return encontrada;
    }

}
