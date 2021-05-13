package es.iespuertolacruz.pokemon.api;

/**
 * Clase principal de entrenador
 */
public class Entrenador {
    
    //Variables de clase

    private static final String DELIMITADOR = "'";
    int id;
    String nombre;

    //Constructores

    /**
     * Constructor por defecto
     */
    public Entrenador() {
    }

    /**
     * Constructor con todos los parametros
     * 
     * @param id identificador del entrenador
     * @param nombre del entrenador
     */
    public Entrenador(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    //Getters and Setters

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    //Funciones y metodos

    @Override
    public String toString() {
        return getId() + DELIMITADOR +
            getNombre();
    }

}
