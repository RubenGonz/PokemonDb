package es.iespuertolacruz.pokemon.api;

/**
 * Clase de las caracteristicas de un pokemon
 */
public class Caracteristicas {

    //Variables de clase

    private static final String DELIMITADOR = "'";
    int id;
    float peso;
    float altura;
    String especie;
    String habilidad;
    String color;
    String categoria;

    //Constructores

    /**
     * Constructor por defecto
     */
    public Caracteristicas() {
    }

    /**
     * Constructor con todos los parametros
     * 
     * @param id del pokemon al que se refiere y el identificador del objeto
     * @param peso del pokemon
     * @param altura del pokemon
     * @param especie del pokemon
     * @param habilidad del pokemon
     * @param color del pokemon
     * @param categoria del pokemon
     */
    public Caracteristicas(int id, float peso, float altura, String especie, String habilidad, String color, String categoria) {
        this.id = id;
        this.peso = peso;
        this.altura = altura;
        this.especie = especie;
        this.habilidad = habilidad;
        this.color = color;
        this.categoria = categoria;
    }

    // Getters and Setters

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getPeso() {
        return this.peso;
    }

    public void setPeso(float peso) {
        this.peso = peso;
    }

    public float getAltura() {
        return this.altura;
    }

    public void setAltura(float altura) {
        this.altura = altura;
    }

    public String getEspecie() {
        return this.especie;
    }

    public void setEspecie(String especie) {
        this.especie = especie;
    }

    public String getHabilidad() {
        return this.habilidad;
    }

    public void setHabilidad(String habilidad) {
        this.habilidad = habilidad;
    }

    public String getColor() {
        return this.color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getCategoria() {
        return this.categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    //Metodos y funciones

    @Override
    public String toString() {
        return getId() + DELIMITADOR +
            getPeso() + DELIMITADOR +
            getAltura() + DELIMITADOR +
            getEspecie() + DELIMITADOR +
            getHabilidad() + DELIMITADOR +
            getColor() + DELIMITADOR +
            getCategoria();
    }

}
   