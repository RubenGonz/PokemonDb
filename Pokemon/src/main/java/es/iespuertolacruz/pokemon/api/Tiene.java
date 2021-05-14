package es.iespuertolacruz.pokemon.api;

public class Tiene {
    
    //Variables de la clase

    private static final String DELIMITADOR = "'";
    int idEntrenador;
    int numeroPokedex;
    int cantidad;

    //Constructores

    /**
     * Constructor por defecto
     */
    public Tiene() {
    }

    /**
     * Constructor con todos los parametros
     * 
     * @param idEntrenador identificador del entrenador
     * @param numeroPokedex identificador del pokemon
     * @param cantidad cantidad de pokemons que tiene repetidos
     */
    public Tiene(int idEntrenador, int numeroPokedex, int cantidad) {
        this.idEntrenador = idEntrenador;
        this.numeroPokedex = numeroPokedex;
        this.cantidad = cantidad;
    }

    //Getters and Setters
    
    public int getIdEntrenador() {
        return this.idEntrenador;
    }

    public void setIdEntrenador(int idEntrenador) {
        this.idEntrenador = idEntrenador;
    }

    public int getNumeroPokedex() {
        return this.numeroPokedex;
    }

    public void setNumeroPokedex(int numeroPokedex) {
        this.numeroPokedex = numeroPokedex;
    }

    public int getCantidad() {
        return this.cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    //Funciones y metodos

    @Override
    public String toString() {
        return getIdEntrenador() + DELIMITADOR +
            getNumeroPokedex() + DELIMITADOR +
            getCantidad();
    }

}
