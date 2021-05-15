package es.iespuertolacruz.pokemon.api;

/**
 * Clase principal de pokemon
 */
public class Pokemon {

    // Variables de clase

    private static final String DELIMITADOR = "'";
    int numeroPokedex;
    String nombre;
    int caracteristicas;
    int estadisticasBase;

    //Constructores

    /**
     * Construcor por defecto
     */
    public Pokemon() {
    }

    /**
     * Constructor con todos los parametros
     * 
     * @param numeroPokedex identificador del pokemon
     * @param nombre del pokemon
     * @param caracteristicas numero que relaciona al pokemon con sus caracteristicas
     * @param estadisticasBase numero que relaciona al pokemon con sus estadisticas
     */
    public Pokemon(int numeroPokedex, String nombre, int caracteristicas, int estadisticasBase) {
        this.numeroPokedex = numeroPokedex;
        this.nombre = nombre;
        this.caracteristicas = caracteristicas;
        this.estadisticasBase = estadisticasBase;
    }

    //Getters and Setters

    public int getNumeroPokedex() {
        return this.numeroPokedex;
    }

    public void setNumeroPokedex(int numeroPokedex) {
        this.numeroPokedex = numeroPokedex;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCaracteristicas() {
        return this.caracteristicas;
    }

    public void setCaracteristicas(int caracteristicas) {
        this.caracteristicas = caracteristicas;
    }

    public int getEstadisticasBase() {
        return this.estadisticasBase;
    }

    public void setEstadisticasBase(int estadisticasBase) {
        this.estadisticasBase = estadisticasBase;
    }

    //Metosos y funciones

    @Override
    public String toString() {
        return getNumeroPokedex() + DELIMITADOR +
            getNombre() + DELIMITADOR +
            getCaracteristicas() + DELIMITADOR +
            getEstadisticasBase();
    }

}