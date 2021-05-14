package es.iespuertolacruz.pokemon.api;

/**
 * Clase del entrenador entrenado casual
 */
public class EntrenadorCasual {
    
    //Variables de la clase

    private static final String DELIMITADOR = "'";
    int idEntrenador;
    int cantidadMedallas;

    //Constructores

    /**
     * Constructor por defecto
     */
    public EntrenadorCasual() {
    }

    /**
     * Constructor con todos los parametros
     * 
     * @param idEntrenador identificador del entrenador padre
     * @param cantidadMedallas cantidad de medallas que ha conseguido el entrenador
     */
    public EntrenadorCasual(int idEntrenador, int cantidadMedallas) {
        this.idEntrenador = idEntrenador;
        this.cantidadMedallas = cantidadMedallas;
    }

    //Getters and Setters

    public int getIdEntrenador() {
        return this.idEntrenador;
    }

    public void setIdEntrenador(int idEntrenador) {
        this.idEntrenador = idEntrenador;
    }

    public int getCantidadMedallas() {
        return this.cantidadMedallas;
    }

    public void setCantidadMedallas(int cantidadMedallas) {
        this.cantidadMedallas = cantidadMedallas;
    }

    //Funciones y metodos

    @Override
    public String toString() {
        return getIdEntrenador() + DELIMITADOR +
            getCantidadMedallas();
    }

}
