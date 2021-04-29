package es.iespuertolacruz.pokemon.api;

import java.util.Objects;

/**
 * Clase de las caracteristicas de un pokemon
 */
public class Caracteristicas {

    //Variables de clase

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
     * @param peso del pokemon
     * @param altura del pokemon
     * @param especie del pokemon
     * @param habilidad del pokemon
     * @param color del pokemon
     * @param categoria del pokemon
     */
    public Caracteristicas(float peso, float altura, String especie, String habilidad, String color, String categoria) {
        this.peso = peso;
        this.altura = altura;
        this.especie = especie;
        this.habilidad = habilidad;
        this.color = color;
        this.categoria = categoria;
    }

    //Getters and Setters

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

    //Funciones y metodos


    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Caracteristicas)) {
            return false;
        }
        Caracteristicas caracteristicas = (Caracteristicas) o;
        return peso == caracteristicas.peso && altura == caracteristicas.altura && Objects.equals(especie, caracteristicas.especie) && Objects.equals(habilidad, caracteristicas.habilidad) && Objects.equals(color, caracteristicas.color) && Objects.equals(categoria, caracteristicas.categoria);
    }

    @Override
    public String toString() {
        return "peso = " + getPeso() +
            ", altura = " + getAltura() +
            ", especie = " + getEspecie() +
            ", habilidad = " + getHabilidad() +
            ", color = " + getColor() +
            ", categoria = " + getCategoria() + ".";
    }

}
