package es.iespuertolacruz.pokemon.api;

/**
 * Clase donde se ven los posibles estados que puede provocar un movimiento
 */
public class Estado {
    
    //Variables de clase

    private static final String DELIMITADOR = "'";
    int id;
    String nombre;
    int persistencia;
    String efecto;

    //Constructores

    /**
     * Constructor por defecto
     */
    public Estado() {
    }

    /**
     * Constructor con todos los parametros
     * 
     * @param id identificador del estado
     * @param nombre del estado
     * @param persistencia 1 si el estado es duradero o 2 si no lo es
     * @param efecto descripcion del estado
     */
    public Estado(int id, String nombre, int persistencia, String efecto) {
        this.id = id;
        this.nombre = nombre;
        this.persistencia = persistencia;
        this.efecto = efecto;
    }

    // Getters and Setters

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

    public int getPersistencia() {
        return this.persistencia;
    }

    public void setPersistencia(int persistencia) {
        this.persistencia = persistencia;
    }

    public String getEfecto() {
        return this.efecto;
    }

    public void setEfecto(String efecto) {
        this.efecto = efecto;
    }

    //Metodos y funciones

    @Override
    public String toString() {
        return getId() + DELIMITADOR +
            getNombre() + DELIMITADOR +
            getPersistencia() + DELIMITADOR +
            getEfecto();
    }

}
