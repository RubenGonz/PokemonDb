package es.iespuertolacruz.pokemon.api;

/**
 * Clase del entrenador villano
 */
public class Villano {
    
    //Variables de clase

    private static final String DELIMITADOR = "'";
    int idEntrenador;
    String proposito;

    //Constructores

    /**
     * Constructor por defecto
     */
    public Villano() {
    }

    /**
     * Constructor con todos los parametros
     * 
     * @param idEntrenador identificador del entrenador padre
     * @param proposito texto que describe su proposito como villano
     */
    public Villano(int idEntrenador, String proposito) {
        this.idEntrenador = idEntrenador;
        this.proposito = proposito;
    }

    //Getters and Setters

    public int getIdEntrenador() {
        return this.idEntrenador;
    }

    public void setIdEntrenador(int idEntrenador) {
        this.idEntrenador = idEntrenador;
    }

    public String getProposito() {
        return this.proposito;
    }

    public void setProposito(String proposito) {
        this.proposito = proposito;
    }

    //Funciones y metodos

    @Override
    public String toString() {
        return getIdEntrenador() + DELIMITADOR +
            getProposito();
    }

}
