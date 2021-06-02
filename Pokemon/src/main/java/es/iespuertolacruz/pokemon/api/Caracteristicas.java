package es.iespuertolacruz.pokemon.api;

import java.util.ArrayList;
import java.util.Objects;
import java.util.StringTokenizer;

/**
 * Clase de las caracteristicas de un pokemon
 */
public class Caracteristicas {

    // Variables de clase

    private static final String DELIMITADOR = "'";
    int id;
    float peso;
    float altura;
    String especie;
    String habilidad;
    String categoria;

    // Constructores

    /**
     * Constructor por defecto
     */
    public Caracteristicas() {
    }

    /**
     * Constructor con todos los parametros
     * 
     * @param id        del pokemon al que se refiere y el identificador del objeto
     * @param peso      del pokemon
     * @param altura    del pokemon
     * @param especie   del pokemon
     * @param habilidad del pokemon
     * @param categoria del pokemon
     */
    public Caracteristicas(int id, float peso, float altura, String especie, String habilidad, String categoria) {
        this.id = id;
        this.peso = peso;
        this.altura = altura;
        this.especie = especie;
        this.habilidad = habilidad;
        this.categoria = categoria;
    }

    /**
     * Constructor que recibe una cadena de texto
     * 
     * @param cadena con la informacion
     */
    public Caracteristicas(String cadena) {
        ArrayList<Object> elementos = new ArrayList<>();
        StringTokenizer tokenizer = new StringTokenizer(cadena, DELIMITADOR);
        while (tokenizer.hasMoreElements()) {
            elementos.add(tokenizer.nextToken());
        }
        this.id = Integer.parseInt((String) elementos.get(0));
        this.peso = Float.parseFloat((String) elementos.get(1));
        this.altura = Float.parseFloat((String) elementos.get(2));
        this.especie = (String) elementos.get(3);
        this.habilidad = (String) elementos.get(4);
        this.categoria = (String) elementos.get(5);
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

    public String getCategoria() {
        return this.categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    // Metodos y funciones

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Caracteristicas)) {
            return false;
        }
        Caracteristicas caracteristicas = (Caracteristicas) o;
        return id == caracteristicas.id && peso == caracteristicas.peso && altura == caracteristicas.altura
                && Objects.equals(especie, caracteristicas.especie)
                && Objects.equals(habilidad, caracteristicas.habilidad)
                && Objects.equals(categoria, caracteristicas.categoria);
    }

    @Override
    public String toString() {
        return getId() + DELIMITADOR + getPeso() + DELIMITADOR + getAltura() + DELIMITADOR + getEspecie() + DELIMITADOR
                + getHabilidad() + DELIMITADOR + getCategoria();
    }

}
