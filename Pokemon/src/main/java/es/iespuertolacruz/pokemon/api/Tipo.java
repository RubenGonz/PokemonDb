package es.iespuertolacruz.pokemon.api;

/**
 * Clase donde se ven los tipos que puede tener un pokemon/movimiento/entrenador
 */
public class Tipo {
    
    //Variables de clase

    private static final String DELIMITADOR = "'";
    String nombre;
    String color;

    //Constructores

    /**
     * Constructor por defecto
     */
    public Tipo() {
    }

    /**
     * Constructor con todos los parametros
     * 
     * @param nombre del tipo
     * @param color representativo del tipo
     */
    public Tipo(String nombre, String color) {
        this.nombre = nombre;
        this.color = color;
    }

    //Getters and Setters

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getColor() {
        return this.color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    //Metodos y funciones

    @Override
    public String toString() {
        return getNombre() + DELIMITADOR +
            getColor();
    }

}
