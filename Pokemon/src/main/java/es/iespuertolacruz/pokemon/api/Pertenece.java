package es.iespuertolacruz.pokemon.api;

/**
 * Clase donde se ven los tipos de los pokemon
 */
public class Pertenece {
    
    //Variables de la clase

    private static final String DELIMITADOR = "'";
    int numeroPokedex;
    String tipo;

    //Constructores

    /**
     * Constructor por defecto
     */
    public Pertenece() {
    }

    /**
     * Constructor con todos los parametros
     * 
     * @param numeroPokedex identificador del pokemon
     * @param tipo tipo del pokemon
     */
    public Pertenece(int numeroPokedex, String tipo) {
        this.numeroPokedex = numeroPokedex;
        this.tipo = tipo;
    }

    //Getters and Setters

    public int getNumeroPokedex() {
        return this.numeroPokedex;
    }

    public void setNumeroPokedex(int numeroPokedex) {
        this.numeroPokedex = numeroPokedex;
    }

    public String getTipo() {
        return this.tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    //Metodos y funciones

    @Override
    public String toString() {
        return getNumeroPokedex() + DELIMITADOR +
            getTipo();
    }

}
