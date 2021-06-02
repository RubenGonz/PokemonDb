package es.iespuertolacruz.pokemon.excepciones;

/**
 * Clase de las excepciones provocadas por la base de datos
 */
public class PersistenciaException extends Exception {

    /**
     * Constructor con el mensaje personalizado
     * 
     * @param mensaje personalizado
     */
    public PersistenciaException(String mensaje) {
        super(mensaje);
    }

    /**
     * Constructor con el mensaje y la exception que se produce
     * 
     * @param mensaje de la excepcion
     * @param exception que produce el error
     */
    public PersistenciaException(String mensaje, Exception exception) {
        super(mensaje, exception);
    }
}
