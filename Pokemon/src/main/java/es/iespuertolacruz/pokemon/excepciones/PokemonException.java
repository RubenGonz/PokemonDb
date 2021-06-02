package es.iespuertolacruz.pokemon.excepciones;

/**
 * Clase de las excepciones provocadas por los controladores
 */
public class PokemonException extends Exception {

    private static final long serialVersionUID = 1L;

    /**
     * Constructor con el mensaje personalizado
     * 
     * @param mensaje personalizado
     */
    public PokemonException(String mensaje) {
        super(mensaje);
    }
}
