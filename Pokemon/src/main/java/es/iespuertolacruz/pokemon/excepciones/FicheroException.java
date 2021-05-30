package es.iespuertolacruz.pokemon.excepciones;

/**
 * Excepcion de los errores relacionados con el fichero 
 */
public class FicheroException extends Exception {

    /**
     * Constructor con el mensaje del error
     * @param mensaje del error
     */
    public FicheroException(String mensaje) {
        super(mensaje);
    }

    /**
     * Constructor con el mensaje del error y la excepcion que ha provocado el error
     * 
     * @param mensaje del error
     * @param ex excepcion que lo ha provocado
     */
    public FicheroException(String mensaje, Exception ex) {
        super(mensaje, ex);
    }

}
