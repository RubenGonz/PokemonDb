package es.iespuertolacruz.pokemon.api;

/**
 * Clase principal de los objetos del juego
 */
public class Objeto {
    
    //Variables de clase

    private static final String DELIMITADOR = "'";
    int id;
    String nombre;
    String modoObtencion;

    //Constructores

    /**
     * Constructor por defecto
     */
    public Objeto() {
    }

    /**
     * Constructor con todos los parametros
     * 
     * @param id del obbjeto
     * @param nombre del objeto
     * @param modoObtencion modo en el que se puede obtener un objeto
     */
    public Objeto(int id, String nombre, String modoObtencion) {
        this.id = id;
        this.nombre = nombre;
        this.modoObtencion = modoObtencion;
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

    public String getModoObtencion() {
        return this.modoObtencion;
    }

    public void setModoObtencion(String modoObtencion) {
        this.modoObtencion = modoObtencion;
    }

    //Metodos y funciones

    @Override
    public String toString() {
        return getId() + DELIMITADOR +
            getNombre() + DELIMITADOR +
            getModoObtencion();
    }

}